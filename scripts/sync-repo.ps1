param(
    [string]$Message
)

$ErrorActionPreference = "Stop"
Set-StrictMode -Version Latest

function Invoke-Git {
    param(
        [Parameter(Mandatory = $true)]
        [string[]]$Arguments
    )

    & git @Arguments
    if ($LASTEXITCODE -ne 0) {
        throw "Git command failed: git $($Arguments -join ' ')"
    }
}

$repoRoot = (Resolve-Path (Join-Path $PSScriptRoot "..")).Path
Set-Location $repoRoot

$branch = (& git branch --show-current).Trim()
if ($LASTEXITCODE -ne 0) {
    throw "This directory is not a Git repository."
}
if ($branch -ne "main") {
    throw "gsync only syncs the main branch. Current branch: $branch"
}

$gitDirectory = (& git rev-parse --git-dir).Trim()
$rebaseMerge = Join-Path $gitDirectory "rebase-merge"
$rebaseApply = Join-Path $gitDirectory "rebase-apply"
$mergeHead = Join-Path $gitDirectory "MERGE_HEAD"
if ((Test-Path $rebaseMerge) -or (Test-Path $rebaseApply) -or (Test-Path $mergeHead)) {
    throw "A merge or rebase is already in progress. Finish it before running gsync."
}

Write-Host "Collecting changes from $repoRoot"
Invoke-Git -Arguments @("add", "-A")

& git diff --cached --quiet
$hasChanges = $LASTEXITCODE -eq 1
if ($LASTEXITCODE -notin @(0, 1)) {
    throw "Git could not inspect the staged changes."
}

if ($hasChanges) {
    if ([string]::IsNullOrWhiteSpace($Message)) {
        $Message = "Sync from VS Code $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
    }
    Invoke-Git -Arguments @("commit", "-m", $Message)
} else {
    Write-Host "No local changes to commit."
}

Write-Host "Downloading and integrating GitHub changes..."
Invoke-Git -Arguments @("pull", "--rebase", "origin", "main")

Write-Host "Uploading local commits to GitHub..."
Invoke-Git -Arguments @("push", "origin", "main")

Write-Host "Sync complete."
Invoke-Git -Arguments @("status", "--short", "--branch")
