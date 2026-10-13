$ErrorActionPreference = "Stop"
Set-StrictMode -Version Latest

$syncScript = (Resolve-Path (Join-Path $PSScriptRoot "sync-repo.ps1")).Path
$launcherDirectory = Join-Path $env:LOCALAPPDATA "Microsoft\WindowsApps"
$launcherPath = Join-Path $launcherDirectory "gsync.cmd"

New-Item -ItemType Directory -Force -Path $launcherDirectory | Out-Null

$launcherContent = @"
@echo off
powershell -NoProfile -ExecutionPolicy Bypass -File "$syncScript" %*
"@
Set-Content -Path $launcherPath -Value $launcherContent -Encoding Ascii

$userPath = [Environment]::GetEnvironmentVariable("Path", "User")
$userPathEntries = @($userPath -split ";" | Where-Object { $_ })
if ($userPathEntries -notcontains $launcherDirectory) {
    $newUserPath = ($userPathEntries + $launcherDirectory) -join ";"
    [Environment]::SetEnvironmentVariable("Path", $newUserPath, "User")
}

Write-Host "Installed gsync command: $launcherPath"
Write-Host "Run gsync from any terminal to commit, pull, and push the repository."
Write-Host "Optional: gsync `"your commit message`""
Write-Host "If gsync is not found immediately, sign out and sign in once."
