# ADR-005 — Per-layer sprint management
# ─────────────────────────────────────────────

## Status
status:        accepted
date:          2026-04-14
superseded_by: —

## Context
The project uses a multi-agent workflow where each layer has its own agent.
Each layer can progress at a different pace — frontend may be blocked waiting
for a backend contract while infrastructure work continues independently.
A single global sprint would not reflect this reality accurately.

## Decision
Each layer manages its own sprint in {layer}/sprints/current.md.
The global sprint template lives in docs/sprints/sprint_template.md.
Closed sprints are archived in {layer}/sprints/history/.
Blocked layers declare their blocker explicitly in current.md using
status, blocked_by and waiting_for fields.
The analysis agent reads all sprint files but cannot modify them.
Only the human or the layer's own agent may update a layer sprint.

## Consequences
positive:
  - Each layer progresses at its own pace without blocking others
  - Blockers are explicit and machine-readable
  - Analysis agent can detect and report blockers automatically
  - Sprint history per layer provides clear audit trail

negative:
  - No single view of the global project sprint state
  - Human must check multiple current.md files for full picture

neutral:
  - Analysis agent partially compensates for lack of global view
    via the dependency-check skill

## Alternatives considered
alternative:      Single global sprint in docs/sprints/
reason_rejected: Does not reflect independent layer progress.
                  A blocked frontend would incorrectly mark the whole
                  project sprint as blocked.

## References
ADR-001 — Layered monorepo structure
analysis/skills/dependency-check/SKILL.md
docs/sprints/sprint_template.md