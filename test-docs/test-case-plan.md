# Test Case Assignment Plan — Legado Mobile Testing

> **Living document** — TC ranges, member counts, and method distributions can be extended.
> When adding new members or test cases, use the next available TC-IDs and update the assignment table.
> See `AI-GUIDE.md` for setup, conventions, and patterns.

## Testing Methods

This project uses complementary testing methods. Mix and match per test case as appropriate.

| Method | Tool / Framework | Best For |
|--------|-----------------|----------|
| **Espresso** | `androidx.test.espresso` | In-app UI: button clicks, text input, view assertions |
| **UIAutomator** | `androidx.test.uiautomator` | Cross-app & system UI: SAF file picker, notifications, system dialogs |
| **Unit Test** | JUnit 4 + Mockito | Business logic: ViewModels, parsers, config, helpers |
| **Integration Test** | Room / ContentProvider test helpers | Data layer: DB migrations, DAO queries, ContentProvider CRUD |
| **Manual / Exploratory** | Checklist + device | UX & usability: visual feel, audio quality, accessibility |
| **Performance** | Benchmark / manual timing | Metrics: startup time, render speed, memory footprint |

---

## Team Members & Current Assignment

> TC ranges are **allocated blocks** — not fixed caps. To add more cases, extend the range.

| Member | Module | Current TC Range |
|--------|--------|------------------|
| **Tianyu Yao** (Lead) | Core Foundation | TC-001 ~ TC-010 |
| **Member 2** | Reading Experience | TC-011 ~ TC-020 |
| **Member 3** | Data & Backend | TC-021 ~ TC-030 |
| **Member 4** | System & Platform | TC-031 ~ TC-040 |

---

## Tianyu Yao — Core Foundation (TC-001 onward)

| TC-ID | Method | Title | Priority |
|-------|--------|-------|----------|
| TC-001 | Espresso | App Launch | High |
| TC-002 | Espresso | Import Local TXT Book | High |
| TC-003 | Espresso | Open Book & Read View | High |
| TC-004 | Espresso | Change Reading Theme | Medium |
| TC-005 | Espresso | TTS Read Aloud Entry | Medium |
| TC-006 | Espresso | Bookshelf Search Entry | Low |
| TC-007 | UIAutomator | Import Book via SAF File Picker | High |
| TC-008 | Unit Test (JUnit) | Book Entity & DAO Validation | High |
| TC-009 | Integration Test (Room) | Database Migration Integrity | Medium |
| TC-010 | Manual / Exploratory | First-Launch User Flow | Medium |

---

## Member 2 — Reading Experience (TC-011 onward)

| TC-ID | Method | Title | Priority |
|-------|--------|-------|----------|
| TC-011 | Espresso | Chapter List Navigation | High |
| TC-012 | Espresso | Add & Delete Bookmarks | Medium |
| TC-013 | Espresso | In-Book Full-Text Search | Medium |
| TC-014 | UIAutomator | Font & Display Settings | Medium |
| TC-015 | UIAutomator | Screen Brightness & Night Mode | Medium |
| TC-016 | Unit Test (JUnit) | Reading Config State Logic | Medium |
| TC-017 | Unit Test (JUnit) | Content Processor & Text Parsing | Low |
| TC-018 | Integration Test (Room) | Bookmark CRUD & Query | Medium |
| TC-019 | Performance | Page Render Speed Benchmark | Medium |
| TC-020 | Manual / Exploratory | Long-Reading Comfort Evaluation | Low |

---

## Member 3 — Data & Backend (TC-021 onward)

| TC-ID | Method | Title | Priority |
|-------|--------|-------|----------|
| TC-021 | Espresso | Book Information Screen | High |
| TC-022 | Espresso | Bookshelf Group Management | Medium |
| TC-023 | Espresso | Batch Book Operations | Medium |
| TC-024 | UIAutomator | Book Source Import & Export | Medium |
| TC-025 | UIAutomator | Book Source Health Check | Low |
| TC-026 | Unit Test (JUnit) | Book Source Rule Parsing | High |
| TC-027 | Unit Test (JUnit) | Replace Rules Engine | Medium |
| TC-028 | Integration Test (Room) | Book Source DAO CRUD | Medium |
| TC-029 | Integration Test (ContentProvider) | ReaderProvider CRUD | High |
| TC-030 | Manual / Exploratory | Book Source Marketplace Usability | Medium |

---

## Member 4 — System & Platform (TC-031 onward)

| TC-ID | Method | Title | Priority |
|-------|--------|-------|----------|
| TC-031 | Espresso | App Theme Customization | Medium |
| TC-032 | Espresso | Read Record & Statistics | Low |
| TC-033 | UIAutomator | WebDAV Backup Flow | High |
| TC-034 | UIAutomator | RSS Subscription & Reading | Medium |
| TC-035 | Unit Test (JUnit) | Backup & Restore Logic | Medium |
| TC-036 | Unit Test (JUnit) | Network Request & Error Handling | Medium |
| TC-037 | Integration Test (Room) | RSS & Cache DAO | Medium |
| TC-038 | Integration Test (ContentProvider) | Chapter Content Query | Medium |
| TC-039 | Performance | App Startup Time & Memory Footprint | High |
| TC-040 | Manual / Exploratory | Accessibility & Edge Cases | Medium |

---

## Approximate Method Distribution

> Percentages are guidelines, not quotas. Adjust as the plan grows.

| Method | Approx. Target |
|--------|---------------|
| Espresso | ~35% |
| UIAutomator | ~20% |
| Unit Test (JUnit) | ~20% |
| Integration Test (Room/Provider) | ~15% |
| Manual / Exploratory | ~10% |
| Performance | ~5% (optional) |

---

## Source Code Locations

```
app-under-test/legado-master/app/src/
├── androidTest/java/io/legado/app/
│   ├── espresso/         ← Espresso & UIAutomator test classes
│   └── *.kt              ← Integration tests (Room, ContentProvider)
├── test/java/io/legado/app/   ← Unit tests (JUnit + Mockito)
└── main/java/io/legado/app/   ← App source (read-only reference)
```

## Standard Conventions

| Aspect | Convention |
|--------|-----------|
| Language | English (code, comments, documentation) |
| Attribution | `@author [English Name]` in class KDoc |
| Espresso runner | `@RunWith(AndroidJUnit4::class)` |
| UIAutomator | `UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())` |
| Unit test | JUnit 4 + Mockito, no Android framework dependency |
| Integration test | `@RunWith(AndroidJUnit4::class)` + Room test helpers |
| Performance | `androidx.benchmark` or manual `System.nanoTime()` |

## How to Extend This Plan

- **Adding a member**: Insert a new section with the next available TC-ID block (e.g., Member 5 — TC-041+). No need to renumber existing cases.
- **Adding cases to an existing member**: Extend their TC range and add rows to their table.
- **Adding a new testing method**: Add it to the Methods table. Reference it in any TC's Method column. No other changes needed.
- **Splitting/merging modules**: Reassign TC ranges as needed. The module names are descriptive, not prescriptive.
- **Dependencies**: See `AI-GUIDE.md` for how to add test library dependencies to `libs.versions.toml`.
