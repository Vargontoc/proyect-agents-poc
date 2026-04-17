# ADR-003 — httpOnly cookie for JWT authentication
# ─────────────────────────────────────────────

## Status
status:        accepted
date:          2026-04-17
superseded_by: —

## Context
The application uses JWT for stateless authentication. A decision was needed
on where to store the token on the client side. The options are localStorage,
Pinia/memory store or httpOnly cookie. Security is a first-class concern
even in a base project template.

## Decision
Store the JWT in an httpOnly cookie set by the backend on login.
Frontend never reads or writes the token — the browser sends it automatically.
Axios is configured with withCredentials: true on all requests.
Pinia auth store holds only user info (name, role), never the token.
Backend must configure CORS to allow credentials from the frontend origin.

## Consequences
positive:
  - Token is inaccessible to JavaScript — XSS attacks cannot steal it
  - No manual token management in frontend code
  - Browser handles cookie lifecycle automatically

negative:
  - Requires CORS configuration with explicit allowed origin and credentials
  - Cookie must be configured with Secure flag in production (requires HTTPS)
  - Slightly more complex backend setup than localStorage approach

neutral:
  - Frontend and backend must be on compatible origins for cookies to work

## Alternatives considered
alternative:      localStorage
reason_rejected: Vulnerable to XSS. Any injected script can read the token.
                  Not acceptable even for a base project template.

alternative:      Pinia memory store
reason_rejected: Token is lost on page refresh. Requires refresh token
                  mechanism to recover session, adding complexity.

## References
ADR-001 — Layered monorepo structure
OWASP JWT Security Cheat Sheet