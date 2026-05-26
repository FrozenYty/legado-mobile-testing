# Test Plan — Legado Mobile

> This plan describes the overall strategy. For detailed TC assignments, see `test-case-plan.md`.
> For how to contribute, see `AI-GUIDE.md`.

## 1. Introduction

This document outlines the testing strategy for the Legado Android application.

## 2. Test Objectives

- Verify core reading functionality works as expected
- Identify UI/UX issues across different Android versions
- Validate import, export, backup, and sync features
- Ensure app stability during prolonged usage
- Cover both in-app interactions and cross-app/system flows

## 3. Scope

### In Scope
- Book import (local files, network sources)
- Reading interface (text rendering, pagination, themes, fonts)
- Bookshelf management (add, remove, group, batch operations)
- Settings and preferences (reading, theme, backup)
- TTS (Text-to-Speech) and audio playback
- Book source management (import, export, health check)
- WebDAV backup and restore
- RSS subscription and reading
- Database migrations and ContentProvider APIs

### Out of Scope
- Backend/server-side testing (Legado has no server)
- Third-party book source content validation
- Production deployment / release testing

## 4. Test Approach

Uses a multi-method strategy for broad coverage:

| Method | Scope |
|--------|-------|
| **Espresso** | In-app UI automation: activity launches, button clicks, view assertions |
| **UIAutomator** | Cross-app & system UI: SAF file picker, notifications, system dialogs |
| **Unit Tests** | Business logic: ViewModels, config, text parsing, rule engines |
| **Integration Tests** | Data layer: Room DB migrations, DAO queries, ContentProvider endpoints |
| **Manual / Exploratory** | UX evaluation: visual feel, audio quality, long-session comfort, accessibility |
| **Performance** | Metrics: startup time, page render speed, memory footprint |

## 5. Test Environment

| Item | Details |
|------|---------|
| Device | Android Emulator (API 32+) / Physical device |
| OS | Android 12+ |
| App Version | `app-under-test/legado-master` (debug build) |
| Test Runner | `androidx.test.runner.AndroidJUnitRunner` |

## 6. Schedule

| Phase | Activity | Status |
|-------|----------|--------|
| Test Design | Write test plan, design test cases, assign modules | Completed |
| Sprint 1 | Core foundation tests (TC-001~006, Espresso) | Completed — Tianyu Yao |
| Sprint 2 | Module A (Reading Experience) + B (Data & Backend) + C (System & Platform) | In Progress — Members 2, 3, 4 |
| Sprint 3 | Bug fixes, regression, final report | Pending |

## 7. Risks

- Frequent upstream changes in Legado source may break existing test selectors
- Limited physical device availability — emulator-only testing has gaps (TTS, sensors)
- Network-dependent features (book sources, WebDAV, RSS) may be affected by server availability
- SAF file picker and other system UIs require UIAutomator, which is slower and less reliable than Espresso
