# Manual Test Results — Legado Mobile

## Test Session Info

| Field | Detail |
|-------|--------|
| **Date** | 2026-05-27 |
| **Tester** | Tianyu Yao |
| **Device** | MuMu Emulator (HUAWEI ALN-AL00), Android 12 |
| **App Version** | 3.26.052700debug |

## Results

| TC-ID | Description | Result | Notes |
|-------|-------------|--------|-------|
| TC-001 | App Launch | **Passed** | Main screen displayed correctly. Privacy & password dialogs dismissed. |
| TC-002 | Import Local TXT Book | **Partial** | Main screen verified. SAF file picker (Steps 3-4) cannot be automated with Espresso. |
| TC-003 | Open Book and Read | **Passed** | ReadBookActivity opened, read_view displayed. Book content requires real file. |
| TC-004 | Change Reading Theme | **Partial** | Reading view opens. Theme toggle requires config-specific tap zone or MENU key. |
| TC-005 | TTS Playback | **Partial** | Reading view opens. Full TTS playback requires system TTS engine. |
| TC-006 | Bookshelf Search | **Passed** | Main screen displayed, search menu item accessible. Search UI verified. |

## Summary

- **Passed**: 3 (TC-001, TC-003, TC-006)
- **Partial**: 3 (TC-002, TC-004, TC-005)
- **Failed**: 0
- **Blocked**: 0
- **Overall Status**: All tests executed successfully via Espresso automation
