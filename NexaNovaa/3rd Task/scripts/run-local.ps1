param(
    [string]$JavaHome = 'C:\Program Files\Java\jdk-21',
    [string]$MySqlHome = 'C:\Program Files\MySQL\MySQL Server 8.0'
)
$ErrorActionPreference = 'Stop'
$project = Split-Path -Parent $PSScriptRoot
$local = Join-Path $project '.local'
if (!(Test-Path -LiteralPath (Join-Path $local 'local.properties'))) {
    & (Join-Path $PSScriptRoot 'setup-local.ps1') -JavaHome $JavaHome -MySqlHome $MySqlHome
}
$java = Join-Path $JavaHome 'bin\java.exe'
if (!(Test-Path -LiteralPath $java)) { throw 'Set -JavaHome to your JDK folder.' }
$env:JAVA_HOME = $JavaHome
$probe = New-Object System.Net.Sockets.TcpClient
try { $probe.Connect('127.0.0.1', 3307); $databaseRunning = $true } catch { $databaseRunning = $false } finally { $probe.Dispose() }
if (!$databaseRunning) {
    $data = Join-Path $local 'mysql-dev'
    $db = Start-Process -FilePath (Join-Path $MySqlHome 'bin\mysqld.exe') -ArgumentList "--no-defaults --standalone --console --datadir=`"$data`" --port=3307 --bind-address=127.0.0.1 --mysqlx=0 --default-time-zone=+05:30" -WindowStyle Hidden -PassThru -RedirectStandardError (Join-Path $local 'mysql.log') -RedirectStandardOutput (Join-Path $local 'mysql-out.log')
    Set-Content -LiteralPath (Join-Path $local 'mysql-process.txt') -Value $db.Id
    Start-Sleep -Seconds 3
}
$probe = New-Object System.Net.Sockets.TcpClient
try { $probe.Connect('127.0.0.1', 8080); $appRunning = $true } catch { $appRunning = $false } finally { $probe.Dispose() }
if ($appRunning) { throw 'Port 8080 is already in use. Stop the current app before rebuilding.' }
Push-Location $project
try {
    & (Join-Path $project 'mvnw.cmd') -q package
    if ($LASTEXITCODE -ne 0) { throw 'Maven build failed.' }
    $jar = Join-Path $project 'target\admission-crm-1.0.0.jar'
    $app = Start-Process -FilePath $java -ArgumentList "-Duser.timezone=Asia/Kolkata -jar `"$jar`"" -WorkingDirectory $project -WindowStyle Hidden -PassThru -RedirectStandardOutput (Join-Path $local 'app.log') -RedirectStandardError (Join-Path $local 'app-error.log')
    Set-Content -LiteralPath (Join-Path $local 'app-process.txt') -Value $app.Id
    $ready = $false
    for ($attempt = 0; $attempt -lt 45; $attempt++) {
        try {
            $response = Invoke-WebRequest -Uri 'http://127.0.0.1:8080/api/auth/session' -UseBasicParsing -TimeoutSec 2
            if ($response.StatusCode -eq 200) { $ready = $true; break }
        } catch {}
        if ($app.HasExited) { break }
        Start-Sleep -Seconds 1
    }
    if (!$ready) { throw 'The app did not become ready. Read .local\app.log and app-error.log.' }
    Write-Host 'Nexaanova is ready: http://127.0.0.1:8080'
    Write-Host "Practice accounts: $local\DEMO-ACCOUNTS.txt"
} finally { Pop-Location }
