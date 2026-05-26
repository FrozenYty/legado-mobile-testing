#!/bin/bash
# =============================================================================
# Legado Mobile Testing — Espresso Test Runner
# =============================================================================
# Prerequisites:
#   - JAVA_HOME pointing to JDK 17+
#   - ANDROID_HOME pointing to Android SDK
#   - A running emulator or connected device (adb devices)
#
# Usage:
#   ./automation/run-tests.sh              # Run all 6 test classes
#   ./automation/run-tests.sh TC001        # Run a single test class
# =============================================================================

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")/app-under-test/legado-master"

if [ -z "${JAVA_HOME:-}" ]; then
    echo "ERROR: JAVA_HOME is not set" >&2
    exit 1
fi

if [ -z "${ANDROID_HOME:-}" ]; then
    echo "ERROR: ANDROID_HOME is not set" >&2
    exit 1
fi

# Check for connected device/emulator
DEVICE_COUNT=$("$ANDROID_HOME/platform-tools/adb" devices 2>/dev/null | grep -v "List of devices" | grep -c "device" || echo 0)
if [ "$DEVICE_COUNT" -eq 0 ]; then
    echo "ERROR: No device/emulator connected" >&2
    exit 1
fi

# Disable animations for stable Espresso tests
"$ANDROID_HOME/platform-tools/adb" shell settings put global window_animation_scale 0.0
"$ANDROID_HOME/platform-tools/adb" shell settings put global transition_animation_scale 0.0
"$ANDROID_HOME/platform-tools/adb" shell settings put global animator_duration_scale 0.0

BASE_PKG="io.legado.app.espresso"

if [ $# -eq 0 ]; then
    CLASSES="${BASE_PKG}.TC001_AppLaunchTest,${BASE_PKG}.TC002_ImportLocalBookTest,${BASE_PKG}.TC003_OpenBookReadTest,${BASE_PKG}.TC004_ChangeReadingThemeTest,${BASE_PKG}.TC005_TTSPlaybackTest,${BASE_PKG}.TC006_BookshelfSearchTest"
else
    CLASSES="${BASE_PKG}.${1}_*"
fi

cd "$PROJECT_DIR"
./gradlew :app:connectedAppDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class="$CLASSES"
