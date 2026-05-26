# Automation Scripts

## Espresso UI Tests

Espresso tests live inside the app module at:

```
app-under-test/legado-master/app/src/androidTest/java/io/legado/app/espresso/
```

This is required by the Android Gradle build system — instrumentation tests must be in the `androidTest` source set to compile and execute.

| File | Test Case | Description |
|------|-----------|-------------|
| `TestHelper.kt` | — | Shared utilities: test book insertion, cleanup, dialog dismissal |
| `TC001_AppLaunchTest.kt` | TC-001 | App launch → main screen verification |
| `TC002_ImportLocalBookTest.kt` | TC-002 | Import book — main screen ready check |
| `TC003_OpenBookReadTest.kt` | TC-003 | Open book → reading view verification |
| `TC004_ChangeReadingThemeTest.kt` | TC-004 | Change theme — reading view pre-check |
| `TC005_TTSPlaybackTest.kt` | TC-005 | TTS playback — reading view pre-check |
| `TC006_BookshelfSearchTest.kt` | TC-006 | Bookshelf search — search entry point check |

## Running Tests

### Command Line

```bash
# Run all tests
./automation/run-tests.sh

# Run a single test class
./automation/run-tests.sh TC001
```

### Android Studio

1. Open `app-under-test/legado-master/` as an Android project
2. Right-click `app/src/androidTest/java/io/legado/app/espresso/`
3. Select **Run 'Tests in io.legado.app.espresso'**

## Test Environment

- **Build**: `./gradlew :app:assembleAppDebug :app:assembleAppDebugAndroidTest`
- **Run**: `./gradlew :app:connectedAppDebugAndroidTest`
- **Min SDK**: 21
- **Target SDK**: 36
- **Test Runner**: `androidx.test.runner.AndroidJUnitRunner`
