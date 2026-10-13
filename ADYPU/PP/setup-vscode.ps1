$ErrorActionPreference = "Stop"
Set-StrictMode -Version Latest

$repoRoot = (Resolve-Path (Join-Path $PSScriptRoot "..\..")).Path
$workspaceFile = Join-Path $repoRoot "ADYPU-Python.code-workspace"
$launcherDirectory = Join-Path $env:LOCALAPPDATA "Microsoft\WindowsApps"
$pythonLauncher = Join-Path $launcherDirectory "cpython.cmd"

if (-not (Test-Path $workspaceFile)) {
    throw "Python workspace not found: $workspaceFile"
}

$codeCommand = Get-Command code -ErrorAction SilentlyContinue
$codeExeCandidates = @(
    (Join-Path $env:LOCALAPPDATA "Programs\Microsoft VS Code\Code.exe"),
    (Join-Path $env:ProgramFiles "Microsoft VS Code\Code.exe")
)

if (${env:ProgramFiles(x86)}) {
    $codeExeCandidates += Join-Path ${env:ProgramFiles(x86)} "Microsoft VS Code\Code.exe"
}

$codeExe = $codeExeCandidates |
    Where-Object { Test-Path $_ } |
    Select-Object -First 1

if (-not $codeCommand -and -not $codeExe) {
    throw "VS Code was not found. Install VS Code and enable its Add to PATH option."
}

New-Item -ItemType Directory -Force -Path $launcherDirectory | Out-Null

if (-not $codeCommand) {
    $defaultLauncher = Join-Path $launcherDirectory "code.cmd"
    $defaultLauncherContent = "@echo off`r`nstart `"`" `"$codeExe`" %*`r`n"
    Set-Content -Path $defaultLauncher -Value $defaultLauncherContent -Encoding Ascii
}

if ($codeExe) {
    $pythonLauncherContent = "@echo off`r`nstart `"`" `"$codeExe`" `"$workspaceFile`"`r`n"
} else {
    $pythonLauncherContent = "@echo off`r`ncall code `"$workspaceFile`"`r`n"
}

Set-Content -Path $pythonLauncher -Value $pythonLauncherContent -Encoding Ascii

$userPath = [Environment]::GetEnvironmentVariable("Path", "User")
$userPathEntries = @($userPath -split ";" | Where-Object { $_ })
if ($userPathEntries -notcontains $launcherDirectory) {
    $newUserPath = ($userPathEntries + $launcherDirectory) -join ";"
    [Environment]::SetEnvironmentVariable("Path", $newUserPath, "User")
}

$pythonCommand = Get-Command py -ErrorAction SilentlyContinue
if (-not $pythonCommand) {
    $pythonCommand = Get-Command python -ErrorAction SilentlyContinue
}

Write-Host "VS Code launch commands are ready:"
Write-Host "  code     - opens normal VS Code"
Write-Host "  cpython  - opens $workspaceFile"

if (-not $pythonCommand) {
    Write-Warning "Python was not found. Install Python before creating the .venv environment."
}

Write-Host "If Windows Run does not find cpython immediately, sign out and sign in once."
