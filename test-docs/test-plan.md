# Test Plan — Legado Mobile

## 1. Introduction

This document outlines the testing strategy for the Legado Android application.

## 2. Test Objectives

- Verify core reading functionality works as expected
- Identify UI/UX issues across different Android versions
- Validate import and export features
- Ensure app stability during prolonged usage

## 3. Scope

### In Scope
- Book import (local files, network sources)
- Reading interface (text rendering, pagination, themes)
- Bookshelf management (add, remove, organize)
- Settings and preferences
- TTS (Text-to-Speech) functionality

### Out of Scope
- Backend/server-side testing
- Third-party book source plugin testing
- Accessibility (TalkBack) — deferred to future cycle

## 4. Test Approach

- **Manual Testing**: Exploratory + scripted test cases
- **Automated Testing**: Espresso for critical user flows (planned)

## 5. Test Environment

| Item | Details |
|------|---------|
| Device | Android Emulator (API 30+) / Physical device |
| OS | Android 11+ |
| App Version | As tagged in app-under-test/legado-master |

## 6. Schedule

| Phase | Duration | Status |
|-------|----------|--------|
| Test Design | Week 1 | Not Started |
| Test Execution | Week 2 | Not Started |
| Bug Triage | Week 3 | Not Started |
| Regression | Week 4 | Not Started |

## 7. Risks

- Frequent upstream changes in Legado source
- Limited physical device availability
- Network-dependent features may be affected by source availability
