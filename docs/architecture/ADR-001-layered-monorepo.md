# ADR-001 — Layered monorepo structure
# ─────────────────────────────────────────────

## Status
status:        accepted
date:          2026-04-17
superseded_by: —

## Context
The project needs to host backend, frontend and infrastructure in a single
repository while keeping each layer independently deployable and with its
own agent context. A structure was needed that supports multi-agent workflows
where each agent has a clearly bounded scope.

## Decision
Organise the repository as a layered monorepo with top-level directories
per layer: infrastructure/, backend/, frontend/, analysis/ and docs/.
Each layer owns its AGENT.md, skills/, sprints/ and source code.
No layer imports or depends on another layer's source code directly.

## Consequences
positive:
  - Single repository simplifies initial setup and onboarding
  - Each layer has a clear bounded context for agents
  - CI workflows can be scoped per layer using path filters
  - Contracts between layers are explicit in docs/contracts/

negative:
  - Monorepo grows in size as the project scales
  - CI must be carefully scoped to avoid unnecessary runs

neutral:
  - Git history mixes commits from all layers in one timeline

## Alternatives considered
alternative:      Separate repositories per layer
reason_rejected: Increases coordination overhead for a single-developer
                  base project. Contract management across repos requires
                  additional tooling (git submodules or package registry).

## References
ADR-004 — docs/contracts as single source of truth