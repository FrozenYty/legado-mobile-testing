# Automation

Test code for the Legado Mobile Testing project.

> **Language**: All code, comments, and documentation in **English**.

## Test Source Locations

```
app-under-test/legado-master/app/src/
├── androidTest/java/io/legado/app/
│   ├── espresso/         ← Espresso UI tests
│   ├── uiautomator/      ← UIAutomator tests
│   ├── integration/      ← Room, ContentProvider tests
│   ├── performance/      ← Benchmark tests
│   └── utils/            ← Shared utilities (TestHelper.kt)
└── test/java/io/legado/app/
    └── unit/              ← JUnit unit tests
```

## Test Methods at a Glance

| Method | Source Directory | When to Use |
|--------|-----------------|-------------|
| Espresso | `.../espresso/` | In-app UI: click views, type text, check visibility |
| UIAutomator | `.../uiautomator/` | System UI: SAF picker, notifications, cross-app flows |
| Unit Test | `.../test/unit/` | No-Android logic: ViewModels, parsers, config |
| Integration Test | `.../integration/` | DB migrations, DAO queries, ContentProvider |

## Running Tests

### All Espresso & Integration Tests

```bash
cd app-under-test/legado-master
./gradlew :app:connectedAppDebugAndroidTest
```

### Specific Test Class

```bash
./gradlew :app:connectedAppDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class=io.legado.app.espresso.TC001_AppLaunchTest
```

### All Unit Tests

```bash
./gradlew :app:testAppDebugUnitTest
```

### Via Script

```bash
./automation/run-tests.sh        # All instrumented tests
./automation/run-tests.sh --unit # Run unit tests
./automation/run-tests.sh TC001  # Single test class
```

## Android Studio

1. Open `app-under-test/legado-master/` as an Android project
2. Right-click a test class or package → **Run**
3. For unit tests, right-click `app/src/test/` → **Run**

## Test Environment

| Item | Value |
|------|-------|
| Build | `./gradlew :app:assembleAppDebug :app:assembleAppDebugAndroidTest` |
| Test Runner | `androidx.test.runner.AndroidJUnitRunner` |
| Min SDK | 21 |
| Target SDK | 36 |

## Current Test Files

> See `test-docs/test-case-plan.md` for the authoritative TC-to-file mapping.

Key existing files (reference examples):

| File | Package | What It Demonstrates |
|------|---------|---------------------|
| `utils/TestHelper.kt` | `utils/` | Shared test data setup/cleanup, dialog dismissal |
| `TC001_AppLaunchTest.kt` | `espresso/` | Basic Espresso: launch Activity, check views |
| `TC003_OpenBookReadTest.kt` | `espresso/` | DB setup: insert test book, launch with Intent |
| `TC007_ImportBookSAFTest.kt` | `uiautomator/` | UIAutomator: cross-app system UI interaction |
| `TC008_BookEntityTest.kt` | `test/unit/` | Unit test: entity fields & constants (no Android) |
| `TC009_DatabaseMigrationTest.kt` | `integration/` | Room: migration testing with MigrationTestHelper |
