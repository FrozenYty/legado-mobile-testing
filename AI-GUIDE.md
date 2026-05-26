# AI Collaboration Guide — Legado Mobile Testing

This document is written for AI assistants (Claude, ChatGPT, Copilot, etc.) to quickly
understand the project and start contributing test cases.

## Quick Context

This is a mobile testing course project for the [Legado](https://github.com/gedoor/legado)
Android e-book reader. The project is hosted at `legado-mobile-testing` on GitHub and contains:

- The app source under `app-under-test/legado-master/`
- Test documentation under `test-docs/`
- Espresso & UIAutomator tests under the app's `androidTest` source set
- Unit tests under the app's `test` source set

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

## Where Files Live

```
app-under-test/legado-master/app/src/
├── androidTest/java/io/legado/app/
│   ├── espresso/        ← Espresso & UIAutomator tests
│   └── *.kt             ← Integration tests (Room, ContentProvider)
├── test/java/io/legado/app/  ← Unit tests (JUnit, Mockito)
└── main/java/io/legado/app/  ← App source (read-only reference)

test-docs/
├── test-case-plan.md         ← Assignment plan (authoritative for TC ranges)
├── test-cases.md             ← Detailed test case specs
├── bug-report-template.md    ← Bug report format
└── test-summary-report.md    ← Final report template

automation/
├── run-tests.sh              ← CLI test runner script
└── README.md                 ← Automation overview

Claude_Code_Files/            ← AI-generated intermediate files (gitignored)
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
// File: app/src/test/java/io/legado/app/XXXTest.kt
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

Located at `app/src/androidTest/java/io/legado/app/espresso/TestHelper.kt`

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

## How to Add New Test Cases

1. Find the next available TC-ID range in `test-docs/test-case-plan.md`
2. Assign the range to a team member with their English name
3. Write the test spec in `test-docs/test-cases.md` (follow existing format)
4. Create the test class in the appropriate source directory
5. Run `./gradlew :app:compileAppDebugAndroidTestSources` to verify compilation
6. Run the test via `./gradlew :app:connectedAppDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=<full.package.ClassName>`
7. Update `test-results/manual-test-result.md` and `test-docs/test-summary-report.md` with results

## Extending the Plan

The `test-case-plan.md` uses member-based ranges (e.g., "Member 2 — TC-011~TC-020"). To add more:

- **New member**: Add a new section with their TC range and module name. Use the next available TC-IDs.
- **More cases per member**: Extend the TC range for that member. No need to renumber.
- **New testing method**: Add it to the "Testing Methods Overview" table. Reference it in any TC's "Method" column.

The plan is a living document — numbers are not fixed. Just keep TC-IDs unique.
