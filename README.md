# Legado Mobile Testing

A structured mobile testing project for the [Legado](https://github.com/gedoor/legado) Android application — an open-source e-book reader.

## Overview

This repository contains the test documentation, manual test results, bug reports, screenshots, and automation setup for testing the Legado Android app. The app-under-test source code is included as a Git submodule reference for traceability.

> **Language**: All code, comments, documentation, and commit messages must be written in **English**.

## Directory Structure

```
legado-mobile-testing/
├── README.md                    # Project overview
├── CONTRIBUTING.md              # Commit rules, branch naming, PR process
├── AI-GUIDE.md                  # AI collaboration guide (setup, patterns, pitfalls)
├── .gitignore                   # Git ignore rules
├── app-under-test/              # Application source code under test
│   └── legado-master/           # Legado Android source
│       └── app/src/
│           ├── androidTest/     # Instrumented tests (Espresso, UIAutomator, Room)
│           │   └── java/io/legado/app/espresso/
│           │       ├── TestHelper.kt
│           │       └── TC*_*Test.kt
│           └── test/            # Unit tests (JUnit + Mockito)
├── test-docs/                   # Test documentation
│   ├── test-plan.md             # Overall test strategy & scope
│   ├── test-case-plan.md        # TC assignment plan & method distribution
│   ├── test-cases.md            # Detailed test case specifications
│   ├── bug-report-template.md   # Template for filing bugs
│   └── test-summary-report.md   # Final test summary
├── bug-reports/                 # Filed bug reports
│   └── bug-001.md               # Example bug report
├── screenshots/                 # Test evidence screenshots
├── automation/                  # Test runner scripts & config
│   ├── README.md                # Automation overview
│   └── run-tests.sh             # CLI test runner
└── test-results/                # Test execution results
    └── manual-test-result.md    # Manual test pass/fail records
```

## Test Scope

- **Functional Testing**: Core reading features, book import, bookshelf management, reading settings, WebDAV sync
- **UI Testing**: Layout rendering, theme switching, navigation flows
- **Compatibility Testing**: Android API levels, screen sizes, font scaling
- **Performance Testing**: App startup time, page rendering speed, memory footprint

## Testing Methods

This project uses multiple complementary testing approaches:

| Method | Tool | Scope |
|--------|------|-------|
| Espresso | `androidx.test.espresso` | In-app UI automation |
| UIAutomator | `androidx.test.uiautomator` | Cross-app & system UI |
| Unit Tests | JUnit 4 + Mockito | Business logic |
| Integration Tests | Room / ContentProvider | Data layer |
| Manual | Structured checklist | UX & accessibility |
| Performance | Benchmark / timer | Speed & memory |

See `AI-GUIDE.md` for how to contribute tests. See `test-docs/test-case-plan.md` for TC assignments.

## App Under Test

- **App Name**: Legado
- **Platform**: Android
- **Build System**: Gradle (Kotlin DSL)
- **Min SDK**: 21
- **Language**: Kotlin
