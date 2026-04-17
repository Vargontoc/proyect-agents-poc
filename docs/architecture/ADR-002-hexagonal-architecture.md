# ADR-002 — Hexagonal architecture for backend
# ─────────────────────────────────────────────

## Status
status:        accepted
date:          2026-04-17
superseded_by: —

## Context
The backend needs a structure that keeps business logic independent of
frameworks, databases and HTTP concerns. The project owner's focus is
software design over infrastructure, so the architecture must make
domain logic the first-class citizen and infrastructure details secondary.

## Decision
Apply hexagonal architecture (ports and adapters) per functional domain.
Each domain has: domain/model, domain/ports/in, domain/ports/out,
domain/service, application, infrastructure and dto packages.
Domain model classes must not import Spring or JPA annotations.
Infrastructure adapters implement the domain ports.

## Consequences
positive:
  - Business logic is testable in isolation without Spring context
  - Infrastructure can be swapped without touching domain logic
  - Clear boundaries make agent-assisted coding more reliable
  - Architecture violations are easy to detect and explain

negative:
  - More boilerplate than a layered MVC approach
  - Steeper learning curve for developers unfamiliar with the pattern

neutral:
  - Requires discipline to keep domain model free of framework annotations

## Alternatives considered
alternative:      Classic layered architecture (controller/service/repository)
reason_rejected: Business logic tends to leak into controllers or repositories
                  over time. Harder to test domain logic in isolation.

## References
ADR-001 — Layered monorepo structure
https://alistair.cockburn.us/hexagonal-architecture/