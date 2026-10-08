param(
    [string]$JavaHome = 'C:\Program Files\Java\jdk-21',
    [string]$MySqlHome = 'C:\Program Files\MySQL\MySQL Server 8.0'
)
$ErrorActionPreference = 'Stop'
$project = Split-Path -Parent $PSScriptRoot
$local = Join-Path $project '.local'
$data = Join-Path $local 'mysql-dev'
$mysql = Join-Path $MySqlHome 'bin\mysql.exe'
$mysqld = Join-Path $MySqlHome 'bin\mysqld.exe'
if (!(Test-Path -LiteralPath (Join-Path $JavaHome 'bin\java.exe'))) { throw 'Set -JavaHome to your JDK folder.' }
if (!(Test-Path -LiteralPath $mysqld)) { throw 'Set -MySqlHome to your MySQL Server folder.' }
$env:JAVA_HOME = $JavaHome
New-Item -ItemType Directory -Path $local -Force | Out-Null
$config = Join-Path $local 'local.properties'
if (Test-Path -LiteralPath $config) {
    Write-Host 'Local configuration already exists. Use scripts\run-local.ps1.'
    exit 0
}
$client = New-Object System.Net.Sockets.TcpClient
try { $client.Connect('127.0.0.1', 3307); $occupied = $true } catch { $occupied = $false } finally { $client.Dispose() }
if ($occupied) { throw 'Port 3307 is already in use. No existing database was changed.' }
if (Test-Path -LiteralPath $data) { throw 'An earlier local database exists. Check .local before retrying setup; no files were removed.' }
New-Item -ItemType Directory -Path $data | Out-Null
Write-Host 'Preparing the separate Task 3 database on port 3307...'
$initialize = Start-Process -FilePath $mysqld -ArgumentList "--no-defaults --initialize-insecure --datadir=`"$data`"" -WindowStyle Hidden -PassThru -Wait -RedirectStandardError (Join-Path $local 'mysql-init.log')
if ($initialize.ExitCode -ne 0) { throw 'MySQL initialization failed. Read .local\mysql-init.log.' }
$dbProcess = Start-Process -FilePath $mysqld -ArgumentList "--no-defaults --standalone --console --datadir=`"$data`" --port=3307 --bind-address=127.0.0.1 --mysqlx=0 --default-time-zone=+05:30" -WindowStyle Hidden -PassThru -RedirectStandardError (Join-Path $local 'mysql.log') -RedirectStandardOutput (Join-Path $local 'mysql-out.log')
Set-Content -LiteralPath (Join-Path $local 'mysql-process.txt') -Value $dbProcess.Id
$ready = $false
for ($attempt = 0; $attempt -lt 30; $attempt++) {
    $connection = New-Object System.Net.Sockets.TcpClient
    try { $connection.Connect('127.0.0.1', 3307); $ready = $true } catch {} finally { $connection.Dispose() }
    if ($ready) { break }
    Start-Sleep -Seconds 1
}
if (!$ready) { throw 'MySQL did not start. Read .local\mysql.log.' }
# Random local credentials are saved only inside the ignored .local folder.
$rootPassword = [Guid]::NewGuid().ToString('N') + 'R!'
$dbPassword = [Guid]::NewGuid().ToString('N') + 'D!'
$adminPassword = [Guid]::NewGuid().ToString('N') + 'A!'
$samplePassword = [Guid]::NewGuid().ToString('N') + 'S!'
$sql = @"
ALTER USER 'root'@'localhost' IDENTIFIED BY '$rootPassword';
CREATE DATABASE nexaanova_task3 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'nexaa_app'@'localhost' IDENTIFIED BY '$dbPassword';
CREATE USER 'nexaa_app'@'127.0.0.1' IDENTIFIED BY '$dbPassword';
GRANT ALL PRIVILEGES ON nexaanova_task3.* TO 'nexaa_app'@'localhost';
GRANT ALL PRIVILEGES ON nexaanova_task3.* TO 'nexaa_app'@'127.0.0.1';
"@
$sql | & $mysql '--no-defaults' '--protocol=tcp' '--host=127.0.0.1' '--port=3307' '--user=root'
if ($LASTEXITCODE -ne 0) { throw 'Database provisioning failed. No existing databases were changed.' }
@"
[client]
host=127.0.0.1
port=3307
protocol=tcp
user=root
password=$rootPassword
"@ | Set-Content -LiteralPath (Join-Path $local 'mysql-admin.cnf') -Encoding ASCII
@"
spring.datasource.url=jdbc:mysql://127.0.0.1:3307/nexaanova_task3?serverTimezone=Asia/Kolkata
spring.datasource.username=nexaa_app
spring.datasource.password=$dbPassword
app.admin-email=admin@nexaanova.local
app.admin-password=$adminPassword
app.sample-data=true
app.sample-password=$samplePassword
"@ | Set-Content -LiteralPath $config -Encoding ASCII
@"
Nexaanova local practice accounts
URL: http://127.0.0.1:8080/login.html
Admin: admin@nexaanova.local
Admin password: $adminPassword
Counselor: counselor@nexaanova.local
Second counselor: second.counselor@nexaanova.local
Manager: manager@nexaanova.local
Password for these three sample accounts: $samplePassword

These credentials are personal local configuration. Do not upload .local.
Sample records are created only when the database is empty.
"@ | Set-Content -LiteralPath (Join-Path $local 'DEMO-ACCOUNTS.txt') -Encoding UTF8
Write-Host "Setup complete. Account details: $local\DEMO-ACCOUNTS.txt"
