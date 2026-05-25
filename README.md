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
│   └── legado-master/           # Legado Android source (reference)
├── test-docs/                   # Test documentation
│   ├── test-plan.md             # Overall test strategy & scope
│   ├── test-cases.md            # Detailed test cases
│   ├── bug-report-template.md   # Template for filing bugs
│   └── test-summary-report.md   # Final test summary
├── bug-reports/                 # Filed bug reports
│   └── bug-001.md               # Example bug report
├── screenshots/                 # Test evidence screenshots
├── automation/                  # UI automation scripts (Espresso/UIAutomator)
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
