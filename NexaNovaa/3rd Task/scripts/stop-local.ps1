param([switch]$Database)
$ErrorActionPreference = 'Stop'
$project = Split-Path -Parent $PSScriptRoot
$local = Join-Path $project '.local'
$names = @('app')
if ($Database) { $names += 'mysql' }
foreach ($name in $names) {
    $pidFile = Join-Path $local "$name-process.txt"
    if (!(Test-Path -LiteralPath $pidFile)) { continue }
    $processNumber = [int](Get-Content -LiteralPath $pidFile)
    $processInfo = Get-CimInstance Win32_Process -Filter "ProcessId=$processNumber"
    if (!$processInfo) { continue }
    if (!$processInfo.CommandLine.Contains($project)) {
        throw "Process $processNumber does not belong to this project; it was left running."
    }
    if ($name -eq 'mysql') {
        $adminTool = Join-Path (Split-Path -Parent $processInfo.ExecutablePath) 'mysqladmin.exe'
        $clientConfig = Join-Path $local 'mysql-admin.cnf'
        & $adminTool "--defaults-file=$clientConfig" shutdown
        if ($LASTEXITCODE -ne 0) { throw 'Local database shutdown failed; inspect .local\mysql.log.' }
    } else {
        Stop-Process -Id $processNumber
    }
    Wait-Process -Id $processNumber -Timeout 20 -ErrorAction SilentlyContinue
    Write-Host "Stopped the project's $name process."
}
