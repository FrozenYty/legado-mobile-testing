# Test Cases — Legado Mobile

## TC-001: App Launch

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | App installed, first launch |
| **Steps** | 1. Tap app icon <br> 2. Wait for main screen |
| **Expected Result** | App launches without crash, bookshelf screen displayed |
| **Status** | **Passed** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC001_AppLaunchTest.kt` — Espresso UI test |

---

## TC-002: Import Local TXT Book

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | A .txt file stored on device |
| **Steps** | 1. Open app <br> 2. Tap "+" or import button <br> 3. Select local file <br> 4. Choose the .txt file |
| **Expected Result** | Book appears on bookshelf with correct title |
| **Status** | **Partial** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC002_ImportLocalBookTest.kt` — Espresso UI test (Steps 1-2 only) |
| **Note** | Steps 3-4 use Android SAF file picker which Espresso cannot automate. Verified manually or via UIAutomator. |

---

## TC-003: Open Book and Read

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | At least one book imported |
| **Steps** | 1. Tap book cover on bookshelf <br> 2. Swipe left to turn page <br> 3. Swipe right to go back |
| **Expected Result** | Reading view opens, page turns smoothly |
| **Status** | **Passed** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC003_OpenBookReadTest.kt` — Espresso UI test |

---

## TC-004: Change Reading Theme

| Field | Detail |
|-------|--------|
| **Priority** | Medium |
| **Precondition** | Reading view open |
| **Steps** | 1. Tap center of screen <br> 2. Tap theme/settings icon <br> 3. Select a different theme (e.g., Night mode) |
| **Expected Result** | Background and text colors change immediately |
| **Status** | **Partial** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC004_ChangeReadingThemeTest.kt` — Espresso UI test (verifies reading view opens) |
| **Note** | Theme toggle requires config-dependent tap zone or MENU key; verified manually. |

---

## TC-005: TTS Playback

| Field | Detail |
|-------|--------|
| **Priority** | Medium |
| **Precondition** | Reading view open, TTS engine installed on device |
| **Steps** | 1. Tap center of screen <br> 2. Tap TTS/read-aloud button <br> 3. Wait for playback |
| **Expected Result** | Text is read aloud, playback controls visible |
| **Status** | **Partial** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC005_TTSPlaybackTest.kt` — Espresso UI test (verifies reading view opens) |
| **Note** | Full TTS verification requires a physical device with a TTS engine installed. |

---

## TC-006: Bookshelf Search

| Field | Detail |
|-------|--------|
| **Priority** | Low |
| **Precondition** | Multiple books on bookshelf |
| **Steps** | 1. Tap search icon on bookshelf <br> 2. Type book name <br> 3. Observe results |
| **Expected Result** | Matching books filtered, non-matching hidden |
| **Status** | **Passed** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC006_BookshelfSearchTest.kt` — Espresso UI test |

---

## TC-007: Import Book via SAF File Picker

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | A .txt test file pushed to /sdcard/Download/ |
| **Steps** | 1. Launch app <br> 2. Open bookshelf overflow menu, tap import <br> 3. System SAF file picker opens <br> 4. File is selectable via UIAutomator |
| **Expected Result** | System file picker opens, cross-app interaction verified via UIAutomator |
| **Status** | **Passed** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC007_ImportBookSAFTest.kt` — UIAutomator cross-app test |
| **Note** | Full file-selection automation depends on DocumentsUI version; varies by device. |

---

## TC-008: Book Entity & DAO Validation

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | None (pure JUnit, no Android dependency) |
| **Steps** | 1. Verify Book entity field defaults <br> 2. Verify BookType constants <br> 3. Verify BookChapter entity fields |
| **Expected Result** | All entity fields have correct defaults; BookType constants match specification |
| **Status** | **Passed** (15/15 assertions) — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC008_BookEntityTest.kt` — Unit test (JUnit 4, no Android) |

---

## TC-009: Database Migration Integrity

| Field | Detail |
|-------|--------|
| **Priority** | Medium |
| **Precondition** | AppDatabase at version 50 with test data |
| **Steps** | 1. Create database at version 50 <br> 2. Insert a test book <br> 3. Run all migrations to latest version (75) <br> 4. Verify book data survives migration |
| **Expected Result** | All migrations execute without error; test data remains intact |
| **Status** | **Passed** — by Tianyu Yao on 2026-05-27 |
| **Automation** | `TC009_DatabaseMigrationTest.kt` — Room MigrationTestHelper integration test |

---

## TC-010: First-Launch User Flow

| Field | Detail |
|-------|--------|
| **Priority** | Medium |
| **Precondition** | Fresh app install (clear data via `pm clear io.legado.app.debug`) |
| **Steps** | 1. Launch app for the first time <br> 2. Observe the welcome/splash screen <br> 3. Accept the privacy consent dialog <br> 4. Skip the local password setup dialog <br> 5. Verify the main bookshelf screen appears <br> 6. Explore the bottom navigation tabs |
| **Expected Result** | All startup dialogs are clear and functional; main screen loads without crash |
| **Status** | **Passed** — by Tianyu Yao on 2026-05-27 (manually verified on MuMu emulator) |
| **Automation** | N/A — Manual exploratory test |
| **Note** | These dialogs appear only on first launch. They are handled automatically by `TestHelper.dismissStartupDialogs()` in automated tests. |
