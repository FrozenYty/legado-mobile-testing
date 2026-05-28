#!/bin/bash
# =============================================================================
# ARCHIVED — Legado Mobile Testing Test Runner
# All paths, package names, and test class names reference the historical
# Legado project. The app-under-test/ directory has been removed.
# See ../NOTICE.md
#
# This script is preserved as a template for the replacement project.
# Replace PROJECT_DIR, package names, and class lists with new values.
# =============================================================================
# Supports Espresso, UIAutomator, Integration, and Unit tests.
# =============================================================================
# Prerequisites:
#   - JAVA_HOME pointing to JDK 17+
#   - ANDROID_HOME pointing to Android SDK
#   - A running emulator or connected device (adb devices)
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

# --- Unit test mode -------------------------------------------------
if [ "${1:-}" = "--unit" ]; then
    cd "$PROJECT_DIR"
    if [ $# -ge 2 ]; then
        ./gradlew :app:testAppDebugUnitTest --tests "io.legado.app.unit.${2}_*"
    else
        ./gradlew :app:testAppDebugUnitTest
    fi
    exit 0
fi

# --- Instrumented test mode -----------------------------------------
# Check for connected device
DEVICE_COUNT=$("$ANDROID_HOME/platform-tools/adb" devices 2>/dev/null | grep -v "List of devices" | grep -c "device" || echo 0)
if [ "$DEVICE_COUNT" -eq 0 ]; then
    echo "ERROR: No device/emulator connected" >&2
    exit 1
fi

# Disable animations for stable tests
"$ANDROID_HOME/platform-tools/adb" shell settings put global window_animation_scale 0.0 2>/dev/null
"$ANDROID_HOME/platform-tools/adb" shell settings put global transition_animation_scale 0.0 2>/dev/null
"$ANDROID_HOME/platform-tools/adb" shell settings put global animator_duration_scale 0.0 2>/dev/null

# Package locations — tests are organized by tool type
ESPRESSO_PKG="io.legado.app.espresso"
UIAUTOMATOR_PKG="io.legado.app.uiautomator"
INTEGRATION_PKG="io.legado.app.integration"

cd "$PROJECT_DIR"

if [ $# -eq 0 ]; then
    # Run ALL instrumented test classes
    CLASSES="${ESPRESSO_PKG}.TC001_AppLaunchTest"
    CLASSES="$CLASSES,${ESPRESSO_PKG}.TC002_ImportLocalBookTest"
    CLASSES="$CLASSES,${ESPRESSO_PKG}.TC003_OpenBookReadTest"
    CLASSES="$CLASSES,${ESPRESSO_PKG}.TC004_ChangeReadingThemeTest"
    CLASSES="$CLASSES,${ESPRESSO_PKG}.TC005_TTSPlaybackTest"
    CLASSES="$CLASSES,${ESPRESSO_PKG}.TC006_BookshelfSearchTest"
    CLASSES="$CLASSES,${UIAUTOMATOR_PKG}.TC007_ImportBookSAFTest"
    CLASSES="$CLASSES,${INTEGRATION_PKG}.TC009_DatabaseMigrationTest"
else
    # Try to find the test class in the correct package
    TC="${1}"
    CLASSES="${ESPRESSO_PKG}.${TC}_*"
    # Build command with all packages for wildcard match
    ./gradlew :app:connectedAppDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.class="$CLASSES" \
        -Pandroid.testInstrumentationRunnerArguments.package="${ESPRESSO_PKG}" 2>&1 || \
    ./gradlew :app:connectedAppDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.class="${UIAUTOMATOR_PKG}.${TC}_*" 2>&1 || \
    ./gradlew :app:connectedAppDebugAndroidTest \
        -Pandroid.testInstrumentationRunnerArguments.class="${INTEGRATION_PKG}.${TC}_*" 2>&1
    exit 0
fi

./gradlew :app:connectedAppDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class="$CLASSES"
