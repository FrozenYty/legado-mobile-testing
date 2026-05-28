# Mobile Testing Project

A university coursework project for structured Android mobile testing.

> **Compliance Notice**: This repository previously contained test code for the Legado Android app.
> On learning of copyright infringement issues, all app source, test code, bug reports, and
> screenshots targeting Legado were removed. See [NOTICE.md](NOTICE.md).
>
> **Language**: All code, comments, documentation, and commit messages must be written in **English**.

## Overview

This repository contains the test documentation, CI configuration, collaboration guides, and
automation setup for a mobile testing course project. The testing methodology and documentation
infrastructure are application-agnostic and will be reused for a new app-under-test (TBD).

## Getting Started

1. Read [NOTICE.md](NOTICE.md) — understand the project's current status
2. Read [TASK-BRIEF.md](TASK-BRIEF.md) — workflow checklist and reference examples
3. Read [CONTRIBUTING.md](CONTRIBUTING.md) — commit rules, branch naming, PR process
4. Read [AI-GUIDE.md](AI-GUIDE.md) — code patterns, pitfalls, complete workflow details
5. Read [test-docs/test-case-plan.md](test-docs/test-case-plan.md) — TC assignment plan

> **For AI assistants**: Feed [AI-PROMPT.md](AI-PROMPT.md) + [AI-GUIDE.md](AI-GUIDE.md) into your context before writing any code.

## Directory Structure

```
├── README.md
├── NOTICE.md                    # Compliance notice (read first)
├── TASK-BRIEF.md
├── AI-PROMPT.md
├── CONTRIBUTING.md
├── AI-GUIDE.md
├── .gitignore
├── .gitattributes
├── .github/                     # CI workflow & PR template
├── test-docs/                   # Test documentation (methodology, templates)
│   ├── test-plan.md
│   ├── test-case-plan.md
│   ├── test-cases.md
│   ├── bug-report-template.md
│   └── test-summary-report.md
├── bug-reports/                 # Bug report directory (template only)
├── screenshots/                 # Screenshot directory (template only)
├── automation/                  # Test runner scripts & config
└── test-results/                # Test execution results
```

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

See `AI-GUIDE.md` for test writing patterns. See `test-docs/test-case-plan.md` for TC assignments.

## App Under Test

**TBD** — A new app will be selected to replace Legado. Candidates under evaluation.
