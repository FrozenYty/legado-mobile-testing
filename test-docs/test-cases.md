# Test Cases — Legado Mobile

## TC-001: App Launch

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | App installed, first launch |
| **Steps** | 1. Tap app icon <br> 2. Wait for main screen |
| **Expected Result** | App launches without crash, bookshelf screen displayed |
| **Status** | Not Run |

---

## TC-002: Import Local TXT Book

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | A .txt file stored on device |
| **Steps** | 1. Open app <br> 2. Tap "+" or import button <br> 3. Select local file <br> 4. Choose the .txt file |
| **Expected Result** | Book appears on bookshelf with correct title |
| **Status** | Not Run |

---

## TC-003: Open Book and Read

| Field | Detail |
|-------|--------|
| **Priority** | High |
| **Precondition** | At least one book imported |
| **Steps** | 1. Tap book cover on bookshelf <br> 2. Swipe left to turn page <br> 3. Swipe right to go back |
| **Expected Result** | Reading view opens, page turns smoothly |
| **Status** | Not Run |

---

## TC-004: Change Reading Theme

| Field | Detail |
|-------|--------|
| **Priority** | Medium |
| **Precondition** | Reading view open |
| **Steps** | 1. Tap center of screen <br> 2. Tap theme/settings icon <br> 3. Select a different theme (e.g., Night mode) |
| **Expected Result** | Background and text colors change immediately |
| **Status** | Not Run |

---

## TC-005: TTS Playback

| Field | Detail |
|-------|--------|
| **Priority** | Medium |
| **Precondition** | Reading view open, TTS engine installed on device |
| **Steps** | 1. Tap center of screen <br> 2. Tap TTS/read-aloud button <br> 3. Wait for playback |
| **Expected Result** | Text is read aloud, playback controls visible |
| **Status** | Not Run |

---

## TC-006: Bookshelf Search

| Field | Detail |
|-------|--------|
| **Priority** | Low |
| **Precondition** | Multiple books on bookshelf |
| **Steps** | 1. Tap search icon on bookshelf <br> 2. Type book name <br> 3. Observe results |
| **Expected Result** | Matching books filtered, non-matching hidden |
| **Status** | Not Run |
