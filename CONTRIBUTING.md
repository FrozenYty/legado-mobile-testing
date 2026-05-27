# Contributing to Legado Mobile Testing

> **Read this before making any commits or pull requests.**
> AI assistants: feed this file into your context before writing code.

## First-Time Setup

```bash
git clone git@github.com:FrozenYty/legado-mobile-testing.git
cd legado-mobile-testing

# Never commit these:
echo ".idea/" >> .gitignore   # already in .gitignore, just verify
```

## Branch Rules

| Rule | Detail |
|------|--------|
| **Never commit directly to `main`** | All work happens on feature branches |
| Branch naming | `tc/<your-name>/<tc-range>` — e.g., `tc/tianyu-yao/TC001-006` |
| One branch per person per module | Don't spread your work across multiple branches |

```bash
git checkout -b tc/your-name/TC007-012
```

## Commit Rules

### Commit Message Format

```
<type>: <short description>

<optional body — why, not what>

Author: <Your English Name>
```

**Types**: `test` (new test), `fix` (bug fix), `docs` (documentation), `refactor` (cleanup)

### Examples

```
# Good
test: add chapter list navigation Espresso test (TC-007)

Author: Jane Smith

# Good
docs: update test plan with UIAutomator examples

# Bad — no type, vague
added some tests

# Bad — too vague
fix stuff
```

### What to Commit

| ✅ Commit These | ❌ Never Commit These |
|----------------|---------------------|
| Test code (`.kt`) | `.idea/`, `*.iml` |
| Test docs (`.md`) | `local.properties` |
| Test runner scripts | `.gradle/`, `build/` |
| `libs.versions.toml` changes | `.apk`, `.aab`, `.jks` |
| | Screenshots (`.png` — gitignored) |

### Before Committing

```bash
# 1. Verify your tests compile
./gradlew :app:compileAppDebugAndroidTestSources   # instrumented tests
./gradlew :app:compileAppDebugUnitTestSources       # unit tests

# 2. Check what you're about to commit
git status
git diff --stat

# 3. If you touched unexpected files, unstage them
git reset HEAD <file>

# 4. Never use git add -A or git add . — stage files individually
git add path/to/your/test.kt
git add path/to/your/docs.md
```

## File Organization

### Test Code

```
# Your Espresso tests go here:
app/.../androidTest/java/io/legado/app/espresso/

# Your UIAutomator tests go here:
app/.../androidTest/java/io/legado/app/uiautomator/

# Your Integration (Room/ContentProvider) tests go here:
app/.../androidTest/java/io/legado/app/integration/

# Your Performance tests go here:
app/.../androidTest/java/io/legado/app/performance/

# Shared test utilities (TestHelper) are here:
app/.../androidTest/java/io/legado/app/utils/

# Your Unit tests (JUnit, Mockito — no Android) go here:
app/.../test/java/io/legado/app/unit/
```

**Do NOT create new directories.** All folders are pre-created. Drop your files into the correct existing folder.

### Test Documentation

```
# New test case specs append to:
test-docs/test-cases.md

# New bug reports:
bug-reports/bug-XXX.md        # XXX = next available number

# Update these with your results:
test-results/manual-test-result.md
test-docs/test-summary-report.md
```

### Naming Conventions

| What | Pattern | Example |
|------|---------|---------|
| Test class | `TC<NNN>_<ShortTitle>Test.kt` | `TC007_ChapterListNavigationTest.kt` |
| Test method | `descriptiveName_expectedBehavior` | `tapChapter_shouldJumpToChapterContent` |
| Bug report | `bug-<NNN>.md` | `bug-002.md` |
| Package (Espresso) | `io.legado.app.espresso` | |
| Package (UIAutomator) | `io.legado.app.uiautomator` | |
| Package (Integration) | `io.legado.app.integration` | |
| Package (Unit) | `io.legado.app.unit` | |
| Package (Performance) | `io.legado.app.performance` | |
| Package (Utils) | `io.legado.app.utils` | TestHelper only |

## Code Quality

- **Language**: English only — code, comments, docs, commit messages
- **Attribution**: Every test class must have `@author Your English Name` in its KDoc
- **No dead code**: Remove unused imports before committing
- **No commented-out code**: Delete it, don't comment it out
- **Follow existing patterns**: Open `TC001_AppLaunchTest.kt` as your reference
- **Don't modify app source**: `app-under-test/legado-master/app/src/main/` is read-only. Tests only go in `androidTest/` or `test/`.
- **Test dependencies OK**: Adding test-only deps to `libs.versions.toml` or `build.gradle` is allowed. Explain the reason in your PR description.

## Pull Request Process

1. Push your branch: `git push -u origin tc/your-name/TC007-012`
2. Create a PR on GitHub from your branch to `main`
3. PR title: `<Module Name> — <Your Name>` (e.g., "Reading Experience — Jane Smith")
4. PR description: list each TC-ID with status (Passed / Partial / Failed)
5. **At least one other team member must review before merge**
6. Squash-merge into `main` (use GitHub's "Squash and merge" button)

## What NOT to Do

- ❌ Don't `git push --force` to `main`
- ❌ Don't commit directly to `main` (always use a branch + PR)
- ❌ Don't modify other people's test files without asking
- ❌ Don't change `settings.gradle` without team discussion
- ❌ Test-only changes to `build.gradle` or `libs.versions.toml` are OK — just document the reason in PR
- ❌ Don't commit generated files (build outputs, `.class`, `.dex`)
- ❌ Don't change the test method distribution in `test-case-plan.md` — add new cases instead

## Questions?

- How to write tests → Read `AI-GUIDE.md`
- Which TC IDs are yours → Read `test-docs/test-case-plan.md`
- How to set up the environment → Read `AI-GUIDE.md` section "Environment Setup"
