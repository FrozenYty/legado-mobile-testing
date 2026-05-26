# Automation

Test code for the Legado Mobile Testing project.

> **Language**: All code, comments, and documentation in **English**.

## Test Source Locations

```
app-under-test/legado-master/app/src/
├── androidTest/java/io/legado/app/
│   ├── espresso/         ← Espresso & UIAutomator tests
│   └── *.kt              ← Integration tests (Room, ContentProvider)
└── test/java/io/legado/app/   ← Unit tests (JUnit + Mockito)
```

## Test Methods at a Glance

| Method | Source Directory | When to Use |
|--------|-----------------|-------------|
| Espresso | `.../espresso/` | In-app UI: click views, type text, check visibility |
| UIAutomator | `.../espresso/` | System UI: SAF picker, notifications, cross-app flows |
| Unit Test | `.../test/` | No-Android logic: ViewModels, parsers, config |
| Integration Test | `.../androidTest/` (root) | DB migrations, DAO queries, ContentProvider |

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
./automation/run-tests.sh        # All 6 core tests
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

| File | What It Demonstrates |
|------|---------------------|
| `TestHelper.kt` | Shared test data setup/cleanup, dialog dismissal |
| `TC001_AppLaunchTest.kt` | Basic Espresso: launch Activity, check views |
| `TC003_OpenBookReadTest.kt` | Database setup: insert test book, launch with Intent |
| `TC006_BookshelfSearchTest.kt` | Overflow menu: open menu, check menu items |
