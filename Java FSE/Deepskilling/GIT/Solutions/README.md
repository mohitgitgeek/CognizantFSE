# Git Hands-on Solutions

## 1. Configure Git and make the first commit

```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
mkdir GitDemo && cd GitDemo
git init
printf 'Welcome to Git!\n' > welcome.txt
git add welcome.txt
git commit -m "Add welcome file"
git branch -M main
git remote add origin https://github.com/<user>/GitDemo.git
git push -u origin main
```

## 2. Ignore logs

Create `.gitignore`:

```gitignore
*.log
log/
```

Verify with `git status --ignored`, then commit the file.

## 3. Branch and merge

```bash
git switch -c GitNewBranch
printf 'Branch work\n' > branch-note.txt
git add branch-note.txt && git commit -m "Add branch work"
git switch main
git diff main..GitNewBranch
git merge --no-ff GitNewBranch -m "Merge GitNewBranch"
git log --oneline --graph --decorate
git branch -d GitNewBranch
```

## 4. Resolve a merge conflict

```bash
git switch -c GitWork
printf '<message>Branch version</message>\n' > hello.xml
git add hello.xml && git commit -m "Add hello from GitWork"
git switch main
printf '<message>Main version</message>\n' > hello.xml
git add hello.xml && git commit -m "Add hello from main"
git merge GitWork
# Edit hello.xml; remove conflict markers and retain the intended content.
git add hello.xml && git commit -m "Resolve hello.xml merge conflict"
printf '*.bak\n' >> .gitignore
git add .gitignore && git commit -m "Ignore backup files"
git branch -d GitWork
```

## 5. Clean up and push

```bash
git status
git branch -a
git pull --rebase origin main
git push origin main
git status
```
