# ADR-004 — docs/contracts as single source of truth
# ─────────────────────────────────────────────

## Status
status:        accepted
date:          2026-04-17
superseded_by: —

## Context
Frontend and backend need to agree on API shape. Without a neutral location
for contracts, frontend risks coupling directly to backend source code or
duplicating type definitions. The previous approach had a shared/ folder
inside frontend which created an implicit dependency on backend internals.

## Decision
All inter-layer contracts live exclusively in docs/contracts/.
Currently: openapi.json for the REST API.
No layer duplicates contract files locally.
Backend is responsible for generating and updating openapi.json after
any endpoint change. Frontend derives its TypeScript types from openapi.json.
Future contracts (websocket.json, ddl/) follow the same pattern.

## Consequences
positive:
  - Single location for all inter-layer agreements
  - Frontend sprint can be blocked explicitly when contract is not ready
  - CI can validate contract changes independently of layer CI
  - Breaking changes are detectable before they reach develop

negative:
  - Backend must remember to update openapi.json after every endpoint change
  - CI validation adds a step to the backend workflow

neutral:
  - docs/contracts/ grows as new integration types are introduced

## Alternatives considered
alternative:      shared/ folder inside frontend
reason_rejected: Creates implicit dependency on backend internals from
                  frontend layer. Breaks layer sovereignty.

alternative:      Generate types directly from backend source at build time
reason_rejected: Couples frontend build process to backend source code.
                  Not compatible with independent layer deployability.

## References
ADR-001 — Layered monorepo structure
.github/workflows/ci-contract.yml