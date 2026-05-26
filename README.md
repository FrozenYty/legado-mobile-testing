# Legado Mobile Testing

A structured mobile testing project for the [Legado](https://github.com/gedoor/legado) Android application — an open-source e-book reader.

## Overview

This repository contains the test documentation, manual test results, bug reports, screenshots, and automation setup for testing the Legado Android app. The app-under-test source code is included as a Git submodule reference for traceability.

## Directory Structure

```
legado-mobile-testing/
├── README.md                    # Project overview
├── .gitignore                   # Git ignore rules
├── app-under-test/              # Application source code under test
│   └── legado-master/           # Legado Android source
│       └── app/src/androidTest/ # Espresso UI automation tests
│           └── java/io/legado/app/espresso/
│               ├── TestHelper.kt
│               ├── TC001_AppLaunchTest.kt
│               ├── TC002_ImportLocalBookTest.kt
│               ├── TC003_OpenBookReadTest.kt
│               ├── TC004_ChangeReadingThemeTest.kt
│               ├── TC005_TTSPlaybackTest.kt
│               └── TC006_BookshelfSearchTest.kt
├── test-docs/                   # Test documentation
│   ├── test-plan.md             # Overall test strategy & scope
│   ├── test-cases.md            # Detailed test cases (6 cases)
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

- **Functional Testing**: Core reading features, book import, bookshelf management, reading settings
- **UI Testing**: Layout rendering, theme switching, navigation flows
- **Compatibility Testing**: Android API levels, screen sizes
- **Performance Testing**: Page rendering speed, memory usage during long reading sessions

## App Under Test

- **App Name**: Legado
- **Platform**: Android
- **Build System**: Gradle (Kotlin DSL)
- **Min SDK**: 21
- **Language**: Kotlin
