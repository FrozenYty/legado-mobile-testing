# Test Summary Report — Legado Mobile

## Overview

| Field | Detail |
|-------|--------|
| **Project** | Legado Mobile Testing |
| **Test Cycle** | Sprint 1 — Core Foundation (Tianyu Yao) |
| **Date** | 2026-05-27 |
| **Tester** | Tianyu Yao |

## Results Summary

| Metric | Count |
|--------|-------|
| Total Test Cases | 10 |
| Passed | 7 |
| Partial | 3 |
| Failed | 0 |
| Blocked | 0 |
| Not Run | 0 |
| Pass Rate (automated) | 100% (9/9 instrumented tests + 15/15 unit assertions) |

## Bug Summary

| Severity | Count |
|----------|-------|
| Critical | 0 |
| High | 0 |
| Medium | 1 (BUG-003) |
| Low | 1 (BUG-002) |

## Key Findings

- **All 10 test cases executed** across 6 testing methods: Espresso (6), UIAutomator (1), Unit Test (1), Integration Test (1), Manual (1).
- **TC-001~006 (Espresso):** Core app flows verified — launch, import entry, reading view, theme entry, TTS entry, search entry.
- **TC-007 (UIAutomator):** SAF system file picker detected via cross-app UIAutomator. `menu_add_local` flow works; file selection depends on DocumentsUI version.
- **TC-008 (Unit Test):** 15 assertions on Book entity defaults, BookType constants, and BookChapter fields. All pass without Android dependency.
- **TC-009 (Integration Test):** Room migration from version 50 to 75 preserves data. Fresh database opens at latest version.
- **TC-010 (Manual):** First-launch flow verified — privacy consent and password dialogs function correctly on MuMu emulator.

## Test Automation Details

| Test Class | Method | Type | Status |
|-----------|--------|------|--------|
| TC001_AppLaunchTest | Espresso | appLaunch_shouldDisplayBookshelfScreen | Passed |
| TC002_ImportLocalBookTest | Espresso | appLaunch_shouldDisplayMainScreen_forImport | Passed |
| TC003_OpenBookReadTest | Espresso | openBook_shouldDisplayReadingView | Passed |
| TC004_ChangeReadingThemeTest | Espresso | readingView_shouldBeDisplayed_forThemeChange | Passed |
| TC005_TTSPlaybackTest | Espresso | readingView_shouldBeDisplayed_forTTSPlayback | Passed |
| TC006_BookshelfSearchTest | Espresso | bookshelfScreen_shouldHaveSearchAccessible | Passed |
| TC007_ImportBookSAFTest | UIAutomator | importBookViaSAF_shouldOpenSystemFilePicker | Passed |
| TC008_BookEntityTest | Unit (JUnit) | 15 assertions across 13 test methods (io.legado.app.unit) | Passed |
| TC009_DatabaseMigrationTest | Integration (Room) | migrateFromVersion50 + freshDatabase (io.legado.app.integration) | Passed |
| TC010 (Manual) | Exploratory | First-launch privacy & password dialog flow | Passed |

## Recommendations

- **TC-002** — Enhance with UIAutomator file selection (currently partial, SAF picker interaction not automated).
- **TC-004/005** — Full theme toggle and TTS playback verification requires manual testing or device-specific setup.
- **Next sprint** — Members 2, 3, 4 should follow the patterns established in TC-001~010 for their assigned modules.

## Sign-off

| Role | Name | Date |
|------|------|------|
| Tester | Tianyu Yao | 2026-05-27 |
| Reviewer | | |
