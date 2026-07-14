# Workflow

Chapter-wise branches over a single cumulative trunk.

## Layout

- **Trunk:** `second-edition` — holds all completed chapters ("all code so far"). Plays the role of `main`.
- **Chapter branches:** `ch08`, `ch09`, ... — one per chapter, forked from trunk. Your work for that chapter only.
- **Upstream mirror:** `upstream/second-edition` remote-tracking ref — auto-current via `git fetch upstream`, never committed to.

Later-chapter stub files (`exercises/*` with `???`) exist on every branch — it's one sbt project. Isolation is about *your work* per chapter, not the file set.

## Toolchain

JDK 21 (`21.0.5-tem`, pinned in `.sdkmanrc`) + sbt 1.10.7 + Scala 3.3.4. In IntelliJ, set the project SDK to `~/.sdkman/candidates/java/21.0.5-tem`.

## The three operations

### 1. Work a chapter

You're on `chNN`; edit under `src/main/scala/fpinscala/exercises/...`, commit freely:

```sh
git add -A && git commit -m "ch8: Gen.choose / listOfN"
```

Check against `answerkey/<topic>/NN.hint.md` then `NN.answer.md`; full solutions in `src/main/scala/fpinscala/answers/`.

### 2. Finish a chapter

Fast-forward trunk to include it (no merge commit), then branch the next:

```sh
git switch second-edition
git merge --ff-only ch08            # trunk now contains all ch8 work
git switch -c ch09 second-edition   # next chapter off updated trunk
git push -u origin ch09
```

If `--ff-only` refuses, trunk moved (you synced upstream mid-chapter). Rebase first:

```sh
git switch ch08 && git rebase second-edition
```

then retry the fast-forward.

### 3. Pull upstream (rare)

```sh
git switch second-edition
git sync-upstream                          # fetch upstream + rebase onto upstream/second-edition
git switch ch08 && git rebase second-edition   # only if a chapter is in progress
```

`git sync-upstream` is a local alias:

```sh
git config alias.sync-upstream '!git fetch upstream && git rebase upstream/second-edition'
```

## Invariant

Only trunk gets fast-forwarded; chapter branches only ever rebase onto trunk, never merge. No merge commits anywhere.
