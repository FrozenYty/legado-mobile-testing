# Task Brief — Legado Mobile Testing

> **ARCHIVED** — App source and test code removed. See [NOTICE.md](NOTICE.md).
> This document serves as a workflow template for the replacement project.

**To**: Members 2, 3, 4  
**From**: Tianyu Yao (Team Lead)  
**Date**: 2026-05-27

---

## Your Assignment

| Member | Module | TC Range |
|--------|--------|-----------|
| **Member 2** | Reading Experience | TC-011 ~ TC-020 |
| **Member 3** | Data & Backend | TC-021 ~ TC-030 |
| **Member 4** | System & Platform | TC-031 ~ TC-040 |

Full details in `test-docs/test-case-plan.md`.

---

## Before You Start (Read These)

| # | File | Time |
|---|------|------|
| 1 | `CONTRIBUTING.md` | 5 min |
| 2 | `test-docs/test-case-plan.md` (your section) | 5 min |
| 3 | `AI-GUIDE.md` | 10 min |

---

## Workflow

```
Plan → Code → Compile → Run → Document → Commit → PR
```

### 1. Plan
- Read your TC assignments in `test-docs/test-case-plan.md`
- Understand which testing methods you need to use
- Read the reference examples (see table below)

### 2. Code
- Create test files in the **correct pre-existing directory** (do NOT create new folders)
- Copy the pattern from the reference example that matches your method
- Every class must have `@author Your English Name` in its KDoc
- Use `TestHelper.insertTestBook()` and `TestHelper.cleanupTestData()` for DB setup

### 3. Compile
```bash
cd app-under-test/legado-master
./gradlew :app:compileAppDebugAndroidTestSources   # instrumented tests
./gradlew :app:compileAppDebugUnitTestSources       # unit tests
```
Fix all compilation errors before proceeding.

### 4. Run
```bash
./gradlew :app:connectedAppDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class=<full.package.ClassName>
```
Or use the script: `./automation/run-tests.sh TC011`

### 5. Document
Update these files with your results:

| File | What |
|------|------|
| `test-docs/test-cases.md` | Append your TC specs (copy format from TC-001) |
| `test-results/manual-test-result.md` | Add your rows, update Summary counters |
| `test-docs/test-summary-report.md` | Add key findings, update Pass/Fail/Blocked |

### 6. Commit
```bash
git checkout -b tc/<your-name>/<TC-range>
git add path/to/your/test.kt test-docs/test-cases.md
git commit -m "$(cat <<'EOF'
<type>: <short description>

Author: <Your English Name>
EOF
)"
```

### 7. Push & PR
```bash
git push -u origin tc/<your-name>/<TC-range>
```
Open a PR on GitHub. The template loads automatically. **Get a review before merging.**

---

## Reference Examples (Copy These Patterns)

| Method | File to Copy | Package |
|--------|-------------|---------|
| Espresso | `espresso/TC001_AppLaunchTest.kt` | `io.legado.app.espresso` |
| UIAutomator | `uiautomator/TC007_ImportBookSAFTest.kt` | `io.legado.app.uiautomator` |
| Unit Test | `test/unit/TC008_BookEntityTest.kt` | `io.legado.app.unit` |
| Integration | `integration/TC009_DatabaseMigrationTest.kt` | `io.legado.app.integration` |
| Performance | `performance/` (create new) | `io.legado.app.performance` |
| Manual | Document in `test-results/manual-test-result.md` | N/A |

---

## Key Rules

- **Language**: English only — code, comments, docs, commits
- **Never modify** `app/src/main/` — app source is read-only
- **Do NOT create new directories** — all folders are pre-created
- **Do NOT use `git add -A`** — stage files individually
- @author in every test class KDoc
- Screenshots: quality over quantity (see `screenshots/README.md`)

---

## Need Help?

- How to write tests → `AI-GUIDE.md`
- How to set up the environment → `AI-GUIDE.md` section "Environment Setup"
- Which TC IDs are yours → `test-docs/test-case-plan.md`
- Found a bug? → File it in `bug-reports/` using `test-docs/bug-report-template.md`
