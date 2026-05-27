# Screenshots

Store test evidence screenshots here. Reference them from bug reports, manual test results,
or test case documentation.

## Quality Principle — Better None Than Junk

**Better none than junk.** Every screenshot in this directory must serve a clear purpose.
Review each screenshot before committing — if you can't explain why it's valuable in one
sentence, delete it.

### Capture

| Scenario | Example |
|----------|---------|
| Bug visual evidence | A crash dialog, a layout glitch, incorrect text rendering |
| Unique UI state | Reading view, system file picker, theme applied |
| Cross-app interaction | SAF picker, permission dialog — things Espresso can't see |
| Manual test proof | A completed manual step that needs visual confirmation |

### Do NOT capture

| Anti-pattern | Why |
|-------------|-----|
| Every test step | Screenshots are evidence, not a step-by-step log |
| Duplicate UI states | TC-002 shows the same bookshelf as TC-001 — pick one |
| Empty or blank screens | If the view hasn't fully loaded, wait, don't screenshot |
| Intermediate loading spinners | Transient states are not meaningful evidence |
| Test code output | Logs belong in `bug-reports/`, not as screenshots |

## Naming

`<tc-id>-<short-description>.png` — e.g., `tc001-launch-bookshelf.png`, `tc007-saf-file-picker.png`

## How to capture

See `AI-GUIDE.md` section "Capturing Screenshots" for Espresso, UIAutomator, and adb methods.
