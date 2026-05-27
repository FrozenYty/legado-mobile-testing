# AI Prompt — Legado Mobile Testing

Copy this entire message and send it to your AI assistant (Claude, ChatGPT, etc.).

---

I need to complete my assigned test cases for the Legado mobile testing course project.

## Context

- **Project**: Legado Mobile Testing — testing the Legado open-source Android e-book reader
- **My role**: Member [2/3/4], assigned module and TC range per `test-docs/test-case-plan.md`
- **Language**: Everything (code, comments, docs, commits) must be in English

## Before Writing Any Code

Read these files in order:

1. `CONTRIBUTING.md` — branch naming `tc/<name>/<TC-range>`, commit format `type: description + Author: Name`, file organization, what NOT to do
2. `AI-GUIDE.md` — complete workflow (Plan → Code → Compile → Run → Document → Commit → PR), code patterns, pitfalls, TestHelper API
3. `test-docs/test-case-plan.md` — find my member section and exact TC list with methods
4. `test-docs/test-cases.md` — existing TC specs (copy the table format for my new TCs)
5. `app/.../utils/TestHelper.kt` — `insertTestBook()`, `cleanupTestData()`, `dismissStartupDialogs()`, `saveScreenshot()`
6. The reference example for each method I need (see table below)

## Reference Examples

| Method | Copy this file |
|--------|---------------|
| Espresso | `app/src/androidTest/java/io/legado/app/espresso/TC001_AppLaunchTest.kt` |
| UIAutomator | `app/src/androidTest/java/io/legado/app/uiautomator/TC007_ImportBookSAFTest.kt` |
| Unit Test | `app/src/test/java/io/legado/app/unit/TC008_BookEntityTest.kt` |
| Integration | `app/src/androidTest/java/io/legado/app/integration/TC009_DatabaseMigrationTest.kt` |

For Performance tests: use `System.nanoTime()` or `androidx.benchmark`, place in `performance/` package.
For Manual tests: no code — document results in `test-results/manual-test-result.md`.

## What I Need You to Do

For EACH TC in my range:

1. **Plan** — confirm which method and which app feature it targets
2. **Code** — create the test class in the correct directory, with `@author [My English Name]` in KDoc. Follow the reference pattern exactly. Use TestHelper for data setup/cleanup
3. **Compile** — run `./gradlew :app:compileAppDebugAndroidTestSources` (or unit test variant) and fix ALL errors
4. **Run** — execute the test on my device/emulator and report Passed / Partial / Failed
5. **Document** — update these files:
   - `test-docs/test-cases.md` — append my TC specs
   - `test-results/manual-test-result.md` — add result rows
   - `test-docs/test-summary-report.md` — add key findings
6. **Commit** — one commit per logical batch, format: `<type>: <description>\n\nAuthor: <Name>`
7. **Push & PR** — branch `tc/<my-name>/<TC-range>`, open PR on GitHub

## Hard Rules

- NEVER modify `app/src/main/` — app source is read-only
- NEVER use `git add -A` or `git add .` — stage files individually
- NEVER create new directories — all folders are pre-created
- NEVER write Chinese in code, comments, docs, or commit messages
- Only add screenshots that have clear evidence value (see `screenshots/README.md`)
- Every test class must have `@author [My English Name]` in its KDoc

## Package Conventions

| Where | Package |
|-------|---------|
| Espresso | `io.legado.app.espresso` |
| UIAutomator | `io.legado.app.uiautomator` |
| Integration | `io.legado.app.integration` |
| Performance | `io.legado.app.performance` |
| Unit | `io.legado.app.unit` |

## Environment

```bash
export JAVA_HOME=<path-to-jdk-17>
export ANDROID_HOME=<path-to-android-sdk>
adb devices  # verify device is connected
```

Disable animations before running Espresso tests:
```bash
adb shell settings put global window_animation_scale 0.0
adb shell settings put global transition_animation_scale 0.0
adb shell settings put global animator_duration_scale 0.0
```
