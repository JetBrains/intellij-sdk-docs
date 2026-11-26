# AGENTS.md

Instructions for AI coding agents working on the IntelliJ Platform SDK Documentation, published at https://plugins.jetbrains.com/docs/intellij/.

## Sources of Truth

The rules for humans and agents are the same and are maintained only in the following pages.
**Read them before writing or changing content**; they take precedence over anything copied from existing pages:

| File | Covers |
|------|--------|
| `topics/intellij-sdk-docs-original_CONTRIBUTING.md` | Workflow: repository structure, generated content, research, scope, page types and skeleton, related files to update, commit messages, checklist before submitting. |
| `topics/intro/sdk_style.md` | Markup, text formatting, voice, links, code, callouts, images, TOC, redirects. |
| `topics/intro/sdk_code_guidelines.md` | Code samples in `code_samples/`. |
| HTML comment at the top of `reference_guide/api_changes_list_20XX.md` | Exact syntax for incompatible API change entries (validated by CI). |

Do not duplicate these rules here; when a rule changes, update the page above.

## Agent-Specific Notes

- The pages above are Writerside sources: `%\gh-ic%` in them is an escaped `%gh-ic%`, and `<include>` tags render snippets from `topics/_shared/snippets.topic`.
- There is no local build. Use the self-checks below before finishing.
- Many older pages do not follow the style guide, for example, "your" in prose, "we" that does not mean JetBrains, or "you can use X" instead of "use X".
  Do not copy these patterns from neighboring pages.
- Verify that `%gh-ic%` links point to files that exist at `%ijPlatformReleaseTag%`:

  ```bash
  TAG=$(sed -nE 's/.*name="ijPlatformReleaseTag" value="([^"]+)".*/\1/p' v.list)
  curl -s -o /dev/null -w '%{http_code}\n' -I \
    "https://raw.githubusercontent.com/JetBrains/intellij-community/$TAG/<path/in/repo>"   # expect 200
  ```

## Self-Checks

Run from the repository root for each new or changed page (`P=...`):

```bash
P=topics/path/to/page.md
head -1 "$P" | grep -q '^<!-- Copyright 2000-20[0-9][0-9] JetBrains' || echo "BAD copyright line"
grep -q '^# ' "$P" || echo "MISSING H1 title"
grep -q '<link-summary>' "$P" || echo "MISSING <link-summary>"
grep -q "topic=\"$(basename "$P")\"" ijs.tree || echo "NOT in ijs.tree"
awk '/^[[:space:]]*```/{f=!f; next} !f' "$P" | sed -E 's#https?://[^ )]+##g' | grep -nwiE "your|yours|yourself|let's"
grep -nE 'TODO|^#+ (Introduction|Overview)$' "$P"
awk '/^[[:space:]]*```/{ if(!f && $0 ~ /^[[:space:]]*```[[:space:]]*$/) print NR": code fence without language"; f=!f }' "$P"
grep -oE '\]\([A-Za-z0-9_.-]+\.(md|topic)' "$P" | sed 's/^](//' | sort -u | while read -r t; do
  [ -n "$(find topics reference_guide -name "$t" -print -quit)" ] || echo "BROKEN link target: $t"; done
grep -oE '(primary|secondary)-label ref="[^"]+"' "$P" | sed -E 's/.*ref="([^"]+)"/\1/' | sort -u | while read -r l; do
  grep -q "id=\"$l\"" labels.list || echo "UNKNOWN label: $l"; done
grep -oE '\{src="[^"]+"' "$P" | sed -E 's/\{src="//; s/"$//' | while read -r s; do
  [ -f "code_samples/$s" ] || echo "MISSING sample file: code_samples/$s"; done
grep -n 'github.com/JetBrains/intellij-community/' "$P"
```

No output means the checks passed. They do not validate section anchors, `%var%` names, or includes; those are checked by the Writerside build and IDE plugin.
Additionally, review every remaining "you" and "we" in prose (`grep -nwiE "you|we|we're|we've|we'll|our|ours|us" "$P"`) and keep it only where the voice rules in `sdk_style.md` allow it.
