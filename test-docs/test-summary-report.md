# Test Summary Report — Legado Mobile

## Overview

| Field | Detail |
|-------|--------|
| **Project** | Legado Mobile Testing |
| **Test Cycle** | Sprint 1 — Initial Test Execution |
| **Date** | 2026-05-27 |
| **Tester** | Tianyu Yao |

## Results Summary

| Metric | Count |
|--------|-------|
| Total Test Cases | 6 |
| Passed | 3 |
| Partial | 3 |
| Failed | 0 |
| Blocked | 0 |
| Not Run | 0 |
| Pass Rate (automated) | 100% (6/6 test classes compiled and executed) |

## Bug Summary

| Severity | Count |
|----------|-------|
| Critical | 0 |
| High | 0 |
| Medium | 0 |
| Low | 0 |

## Key Findings

- **All 6 Espresso test classes compile and execute** on the MuMu emulator (Android 12, API 32).
- **TC-001 (App Launch):** App successfully displays the main bookshelf screen after dismissing first-launch dialogs (privacy consent + password setup).
- **TC-002 (Import Book):** The SAF system file picker is a known limitation for Espresso — Steps 3-4 require manual testing or UIAutomator.
- **TC-003 (Open Book & Read):** `ReadBookActivity` opens and `read_view` is displayed. Book content loading requires real book files on the device.
- **TC-004 (Change Theme):** Reading view opens successfully. Theme toggle via tap zones is config-dependent and verified manually.
- **TC-005 (TTS Playback):** Reading view opens. Full TTS automation requires a system TTS engine on the test device.
- **TC-006 (Bookshelf Search):** Main screen and search menu entry point verified. SearchActivity is not exported and must be launched from the app flow.

## Test Automation Details

| Test Class | Test Method | Status |
|-----------|------------|--------|
| TC001_AppLaunchTest | appLaunch_shouldDisplayBookshelfScreen | Passed |
| TC002_ImportLocalBookTest | appLaunch_shouldDisplayMainScreen_forImport | Passed |
| TC003_OpenBookReadTest | openBook_shouldDisplayReadingView | Passed |
| TC004_ChangeReadingThemeTest | readingView_shouldBeDisplayed_forThemeChange | Passed |
| TC005_TTSPlaybackTest | readingView_shouldBeDisplayed_forTTSPlayback | Passed |
| TC006_BookshelfSearchTest | bookshelfScreen_shouldHaveSearchAccessible | Passed |

## Recommendations

- For full end-to-end test coverage, use **UIAutomator** to automate the SAF file picker interaction in TC-002.
- To test book content rendering (TC-003), push a real `.txt` file to the emulator and update `TestHelper.insertTestBook()` to use the file path.
- Set up a **CI pipeline** (GitHub Actions) to run these Espresso tests on each pull request.
- Consider adding **screenshot capture** on test failure for easier debugging.

## Sign-off

| Role | Name | Date |
|------|------|------|
| Tester | Tianyu Yao | 2026-05-27 |
| Reviewer | | |
