# Legado Mobile Testing

A structured mobile testing project for the [Legado](https://github.com/gedoor/legado) Android application — an open-source e-book reader.

## Overview

This repository contains the test documentation, manual test results, bug reports, screenshots, and automation setup for testing the Legado Android app. The app-under-test source code is included as a Git submodule reference for traceability.

> **Language**: All code, comments, documentation, and commit messages must be written in **English**.

## Getting Started

1. Read [TASK-BRIEF.md](TASK-BRIEF.md) — your assignment, workflow checklist, reference examples
2. Read [test-docs/test-case-plan.md](test-docs/test-case-plan.md) — find your exact TC range and methods
3. Read [CONTRIBUTING.md](CONTRIBUTING.md) — commit rules, branch naming, PR process
4. Read [AI-GUIDE.md](AI-GUIDE.md) — code patterns, pitfalls, complete workflow details
5. Create your branch: `git checkout -b tc/<your-name>/<TC-range>`
6. Write tests, compile, run, document, commit, push, open PR

> **For AI assistants**: Feed [AI-PROMPT.md](AI-PROMPT.md) + [AI-GUIDE.md](AI-GUIDE.md) into your context before writing any code.

## Directory Structure

```
legado-mobile-testing/
├── README.md                    # Project overview
├── TASK-BRIEF.md                 # Task brief & checklist for team members
├── AI-PROMPT.md                  # Ready-to-use prompt for AI assistants
├── CONTRIBUTING.md              # Commit rules, branch naming, PR process
├── AI-GUIDE.md                  # AI collaboration guide (patterns, pitfalls)
├── .gitignore                   # Git ignore rules
├── .gitattributes               # Line ending normalization
├── .github/                     # CI workflow & PR template
├── app-under-test/              # Application source code under test
│   └── legado-master/           # Legado Android source
│       └── app/src/
│           ├── androidTest/java/io/legado/app/
│           │   ├── espresso/    # Espresso UI tests
│           │   ├── uiautomator/ # UIAutomator tests
│           │   ├── integration/ # Room/ContentProvider tests
│           │   ├── performance/ # Benchmark tests
│           │   └── utils/       # Shared test utilities
│           └── test/java/io/legado/app/
│               ├── unit/        # JUnit unit tests
│               └── manual/      # Manually executed test code
├── test-docs/                   # Test documentation
│   ├── test-plan.md             # Overall test strategy & scope
│   ├── test-case-plan.md        # TC assignment plan & method distribution
│   ├── test-cases.md            # Detailed test case specifications
│   ├── bug-report-template.md   # Template for filing bugs
│   └── test-summary-report.md   # Final test summary
├── bug-reports/                 # Filed bug reports (bug-XXX.md)
├── screenshots/                 # Test evidence screenshots
├── automation/                  # Test runner scripts & config
└── test-results/                # Test execution results
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
