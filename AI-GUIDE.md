# AI Collaboration Guide — Legado Mobile Testing

> **ARCHIVED** — App source and test code have been removed. Package names, file paths,
> and architecture details reference the historical Legado project. See [NOTICE.md](NOTICE.md).
> This guide serves as a methodology reference for the replacement project.

This document is written for AI assistants (Claude, ChatGPT, Copilot, etc.) to quickly
understand the project and start contributing test cases.

## Quick Context

This is a mobile testing course project for the [Legado](https://github.com/gedoor/legado)
Android e-book reader. The project is hosted at `legado-mobile-testing` on GitHub and contains:

- The app source under `app-under-test/legado-master/`
- Test documentation under `test-docs/`
- Espresso & UIAutomator tests under the app's `androidTest` source set
- Unit tests under the app's `test` source set

**All code, comments, documentation, and commit messages must be in English.**

## Required Reading (do this first)

Before writing any code, open these files in order:

| # | File | Why |
|---|------|-----|
| 1 | `CONTRIBUTING.md` | Branch naming, commit format, PR rules, what NOT to do |
| 2 | `test-docs/test-case-plan.md` | Find your TC-ID range, module, and required testing methods |
| 3 | `test-docs/test-cases.md` | See existing TC specs for format reference |
| 4 | `app/.../utils/TestHelper.kt` | Understand shared utilities before using them |
| 5 | `app/.../espresso/TC001_AppLaunchTest.kt` | Reference Espresso pattern (simplest working example) |
| 6 | `app/.../espresso/TC003_OpenBookReadTest.kt` | Reference pattern for tests that need DB setup |

Then scan the relevant app source files for your feature (see [Key App Architecture](#key-app-architecture) below).

## Complete Workflow (follow this order)

When you receive a TC assignment, execute these steps in order. Do NOT skip steps.

### 1. Plan

- Read your TC range from `test-docs/test-case-plan.md`
- Identify which testing methods you must use (Espresso / UIAutomator / Unit / Integration / Manual / Perf)
- Read the existing TC specs in `test-docs/test-cases.md` for format reference

### 2. Code

- Create test class(es) in the correct source directory (see [Where Files Live](#where-files-live))
- Copy the pattern from the relevant template in [Test Writing Patterns](#test-writing-patterns)
- Use `TestHelper.insertTestBook()` for DB setup, `TestHelper.cleanupTestData()` for cleanup
- Every class must have `@author Your English Name` in its KDoc

### 3. Compile

```bash
cd app-under-test/legado-master

# For instrumented tests (Espresso, UIAutomator, Integration):
./gradlew :app:compileAppDebugAndroidTestSources

# For unit tests (JUnit):
./gradlew :app:compileAppDebugUnitTestSources
```

Fix all compilation errors. Do NOT proceed until this passes.

### 4. Run

```bash
./gradlew :app:connectedAppDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class=<full.package.ClassName>
```

Re-run if flaky. Log the result (Passed / Partial / Failed).

### 5. Document

Update these files with your results. Follow the existing format — don't invent new columns or layouts.

| File | What to update |
|------|---------------|
| `test-docs/test-cases.md` | Append your TC specs. Copy the table format from TC-001. Set Status. |
| `test-results/manual-test-result.md` | Add your rows to the Results table. Update Summary counters. |
| `test-docs/test-summary-report.md` | Add key findings for your tests. Update the Pass/Fail/Blocked counters. |
| `automation/README.md` | If you added new test files, consider whether to mention them. Not required every time. |

### 6. Commit

```bash
# NEVER use git add -A or git add .
# Stage only your files individually:
git add path/to/your/test.kt
git add test-docs/test-cases.md
# ... etc.

# Commit with the required format:
git commit -m "$(cat <<'EOF'
<type>: <short description>

<optional body>

Author: <Your English Name>
EOF
)"
```

**Commit types**: `test` (new test), `fix` (bug fix), `docs` (documentation only)

Good example:
```
test: add chapter list navigation tests (TC-007 ~ TC-012)

Espresso: TC-007, UIAutomator: TC-008, Unit: TC-009

Author: Jane Smith
```

### 7. Push & PR

```bash
# Branch naming: tc/<your-name>/<TC-range>
git checkout -b tc/jane-smith/TC007-012
git push -u origin tc/jane-smith/TC007-012
```

Then open a PR on GitHub. The PR template (`.github/pull_request_template.md`) loads automatically — fill in all sections.

### Red Flags

| Level | Rule |
|-------|------|
| 🔴 Hard no | Changing `app/src/main/` — never modify app source code |
| 🔴 Hard no | Committing generated files (`.class`, `.dex`, build outputs) |
| 🔴 Hard no | Using `git add -A` — stage files individually |
| 🟡 Needs PR note | Modifying `build.gradle` or `libs.versions.toml` — explain why in PR description |
| 🟡 Needs fix | Compilation fails — fix before committing |
| 🟡 Needs fix | Tests fail consistently — don't force-commit, investigate first |
| 🟢 Always OK | Adding test files, updating docs, adding test-only dependencies |

For 🟡 items, the team lead can approve the change. Document the reason.

## Environment Setup

```bash
# Required
export JAVA_HOME=<path-to-jdk-17>
export ANDROID_HOME=<path-to-android-sdk>

# Verify device is connected
adb devices

# Disable animations for stable Espresso tests
adb shell settings put global window_animation_scale 0.0
adb shell settings put global transition_animation_scale 0.0
adb shell settings put global animator_duration_scale 0.0
```

Build and run tests:
```bash
cd app-under-test/legado-master

# Compile only (fast check)
./gradlew :app:compileAppDebugAndroidTestSources

# Run specific test class
./gradlew :app:connectedAppDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class=io.legado.app.espresso.TC001_AppLaunchTest
```

## Emulator Compatibility

These tests were developed on **MuMu Emulator (Android 12, API 32, x86_64)** but work on any
Android emulator or physical device running API 21+.

| Aspect | MuMu | Android Studio Emulator | Affects Tests? |
|--------|------|------------------------|----------------|
| Architecture | x86_64 | x86_64 (default) | No |
| adb access | `adb devices` → `emulator-5554` | `adb devices` → `emulator-5554` | No |
| Animation disable | Same `adb shell settings` commands | Same commands | No |
| Privacy/password dialogs | App-level, shown on first launch | Same behavior | No |
| SAF file picker | Android `DocumentsUI` | Same `DocumentsUI` | UIAutomator selectors may need tweak |
| TTS engine | May not be pre-installed | Google APIs image includes Google TTS | TC-005, TC-014 (TTS tests) |
| Screen resolution | Varies (e.g., 1080×1920) | Varies (configurable in AVD) | UIAutomator coordinate-based clicks may need adjustment |
| Google Play Services | Often missing | Available in "Google APIs" image | Firebase warnings only (harmless) |

**Bottom line**: Espresso and Unit tests are emulator-agnostic. UIAutomator tests that interact
with system UI (file picker, TTS settings) should be verified on the target emulator once.

**Quick setup for Android Studio Emulator:**
1. AVD Manager → Create Virtual Device → Pixel 6 → System Image: **API 32+ with Google APIs**
2. Cold boot the emulator before running tests
3. Run the same `adb shell settings put global ...` commands from Environment Setup above

## Where Files Live

```
app-under-test/legado-master/app/src/
├── androidTest/java/io/legado/app/
│   ├── espresso/        ← Espresso UI tests (in-app clicks, ViewActions)
│   ├── uiautomator/     ← UIAutomator tests (cross-app, system UI)
│   ├── integration/     ← Room DB, ContentProvider integration tests
│   ├── performance/     ← Benchmark tests (startup time, memory)
│   ├── utils/           ← Shared test utilities (TestHelper.kt)
│   └── *.kt             ← Legacy app tests (do NOT modify)
├── test/java/io/legado/app/
│   ├── unit/            ← JUnit unit tests (no Android dependency)
│   ├── manual/          ← Manually executed test code
│   └── *.kt             ← Legacy app tests (do NOT modify)
└── main/java/io/legado/app/  ← App source (read-only reference)

test-docs/
├── test-plan.md              ← Overall test strategy & scope
├── test-case-plan.md         ← Assignment plan (authoritative for TC ranges)
├── test-cases.md             ← Detailed test case specs
├── bug-report-template.md    ← Template for filing bugs
└── test-summary-report.md    ← Final report template

bug-reports/
└── bug-XXX.md                ← Filed bug reports (use bug-report-template.md)

automation/
├── run-tests.sh              ← CLI test runner script
└── README.md                 ← Automation overview
```

## Key App Architecture

The app uses a bottom-navigation structure with 4 tabs:

| Tab | Fragment | Key Activity |
|-----|----------|-------------|
| Bookshelf | `BookshelfFragment1/2` | `MainActivity` |
| Discovery | `ExploreFragment` | `ExploreShowActivity` |
| RSS | `RssFragment` | `ReadRssActivity` |
| My | `MyFragment` | `ConfigActivity` |

The reading experience is in `ReadBookActivity` with custom `ReadView` and `ReadMenu` overlays.

## Test Writing Patterns

### Pattern 1: Espresso UI Test (in-app interaction)

```kotlin
package io.legado.app.espresso

@RunWith(AndroidJUnit4::class)
class TCXXX_TitleTest {
    private lateinit var scenario: ActivityScenario<SomeActivity>

    @Before
    fun setUp() {
        // Insert test data via TestHelper if needed
        scenario = ActivityScenario.launch(SomeActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
        // Clean up test data
    }

    @Test
    fun descriptiveName_expectedBehavior() {
        TestHelper.dismissStartupDialogs() // if launching MainActivity
        onView(withId(R.id.some_view)).check(matches(isDisplayed()))
    }
}
```

### Pattern 2: UIAutomator Test (cross-app / system UI)

```kotlin
@RunWith(AndroidJUnit4::class)
class TCXXX_TitleTest {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun crossAppInteraction() {
        // Wait for system UI to appear
        device.wait(Until.hasObject(By.pkg("com.android.documentsui")), 5000)
        // Find and click a system button
        device.findObject(By.text("Open")).click()
    }
}
```

### Pattern 3: Unit Test (no Android dependency)

```kotlin
// File: app/src/test/java/io/legado/app/unit/XXXTest.kt
class XXXTest {
    @Test
    fun methodName_scenario_expectedBehavior() {
        val result = SomeHelper.process(input)
        assertEquals(expected, result)
    }
}
```

### Pattern 4: Room Integration Test

```kotlin
@RunWith(AndroidJUnit4::class)
class XXXTest {
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @After
    fun tearDown() { db.close() }

    @Test
    fun daoMethod_condition_expectedBehavior() {
        val result = db.someDao().query()
        assertNotNull(result)
    }
}
```

## Shared Utilities: TestHelper

Located at `app/src/androidTest/java/io/legado/app/utils/TestHelper.kt`

| Method | What it does |
|--------|-------------|
| `insertTestBook(name, author, chapterCount, urlSuffix)` | Inserts a test book + chapters into Room DB. Returns bookUrl. |
| `cleanupTestData(vararg bookUrls)` | Deletes test books and their chapters. Call in `@After`. |
| `dismissStartupDialogs()` | Clicks through privacy consent ("同意") and password setup ("取消") dialogs. Idempotent — safe to call anytime. |

## Common Pitfalls & Workarounds

1. **Privacy/Password Dialogs on First Launch**
   → Always call `TestHelper.dismissStartupDialogs()` at the start of tests that launch `MainActivity`.

2. **SAF File Picker Not Automatable with Espresso**
   → Use UIAutomator (`UiDevice`) for system-level dialogs. Espresso can only see in-app views.

3. **ReadBookActivity Shows "没有书籍" (No Book)**
   → Set `origin = "https://example.com"` (not the default `loc_book`) in test `Book` objects.
   Default `origin = BookType.localTag` triggers `HandleFileActivity` which hijacks the intent.

4. **ReadMenu Not Showing After Clicking read_view**
   → The menu activation depends on user-configured tap zones. Use `pressKey(KeyEvent.KEYCODE_MENU)` as a reliable alternative.

5. **Flaky Tests After `pm clear`**
   → `pm clear io.legado.app.debug` resets the app to fresh-install state. The first test run after this may fail because WelcomeActivity takes focus. Run tests twice — the second run will pass.

6. **Animations Cause Espresso Failures**
   → Always disable the three animation scales on the test device before running Espresso tests.

7. **SearchActivity Not Exported**
   → `SearchActivity` has no `android:exported="true"` and cannot be launched via `am start` or direct intent from outside the app. Use `MainActivity` → overflow menu → search instead.

## Capturing Screenshots

Screenshots are test evidence. Store them in `screenshots/` at the project root and reference them
in bug reports, manual test results, or TC documentation.

### Quality Principle — Better None Than Junk

**Do not screenshot every test step.** A screenshot is only worth committing when it
captures a meaningful, non-duplicate UI state that serves as evidence. Before adding a
`savescreenshot()` call, ask: "Would a reviewer look at this image and learn something
they couldn't get from the test assertion alone?"

Review every screenshot before you commit. If you can't justify it in one sentence, delete it.

#### Good reasons to capture

| Scenario | Example |
|----------|---------|
| Bug evidence | Crash dialog, layout glitch, incorrect text |
| Unique UI state | Bookshelf, reading view, search menu — one per distinct screen |
| Cross-app interaction | SAF file picker, system permission dialog |
| Visual regression | Before/after a theme or layout change |

#### Do NOT capture

- Duplicate views already covered by another TC screenshot
- Transient states (loading spinners, fade-in animations)
- Test code output or logcat (use `bug-reports/` for that)
- Blank or partially-loaded screens
- Every step of a multi-step test — pick the final state or the most meaningful one

### Recommended: TestHelper.saveScreenshot() (instrumented tests only)

```kotlin
// Call AFTER verifying the UI state you want to capture
TestHelper.saveScreenshot("tc001-launch-bookshelf")
```

Uses `UiAutomation.takeScreenshot()` internally. Saves to the app's external files directory.
Pull files after test run:

```bash
# Windows (Git Bash):
MSYS2_ARG_CONV_EXCL="*" adb pull /sdcard/Android/data/io.legado.app.debug/files/screenshots/ ./screenshots/

# macOS / Linux:
adb pull /sdcard/Android/data/io.legado.app.debug/files/screenshots/ ./screenshots/
```

### UIAutomator (cross-app, includes system UI)

```kotlin
val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
val file = File("/sdcard/Pictures/screenshot.png")
device.takeScreenshot(file)
```

### adb (script-based, no test code needed)

```bash
adb exec-out screencap -p > screenshots/manual-tc010-first-launch.png
```

### Naming convention

`<tc-id>-<short-description>.png` — e.g., `tc001-launch-bookshelf.png`, `tc007-saf-file-picker.png`

## Extending the Plan

The `test-case-plan.md` uses member-based ranges (e.g., "Member 2 — TC-011~TC-020"). To add more:

- **New member**: Add a new section with their TC range and module name. Use the next available TC-IDs.
- **More cases per member**: Extend the TC range for that member. No need to renumber.
- **New testing method**: Add it to the "Testing Methods Overview" table. Reference it in any TC's "Method" column.

The plan is a living document — numbers are not fixed. Just keep TC-IDs unique.
