## Layer Context
layer: frontend
stack: Vue 3 + TypeScript + Vite + Pinia + Axios + Vue Router
structure: Standard Vue 3 - reorganise by domain only if packages grow large
does_not_own: backend logic, database JWT issuance, Docker Compose

## Contract dependency
source_of_truth: docs/contracts/openapi.json
rule: Never hardcode API URLs or response shapes - derive them from openapi.json
rule: If openapi.json does not exist yet, set sprint status to blocked

## Authentication Strategy
method: httpOnly cookie (set by backend on login)
axios: withCredentials: true on all requests - no exceptions
pinia: auth store holds user info (name, role) - never the JWT token
localstorage: never used for auth - not even as fallback
cors: backend must allow credentials from frontend origin (see backend AGENT.md)

## HTTP Client
tool: Axios with a shared instance in src/shared/api/axios.ts
rule: All API calls go through the shared Axios instance
rule: Use request interceptor to handle auth headers if needed
rule: Use response interceptor to handle 401 - redirect to login

## State Management
tool: Pinia
rule: One store per feature
rule: Stores must not call Axios directly - use services in src/services/

## Routing
tool: Vue Router
rule: Protected routes use a navigation guard that checks auth store
rule: If user is not authenticated, redirect to /login

## Skils Available
coding: frontend/skills/coding/SKILL.md
testing: frontend/skills/testing/SKILL.md
refactor: frontend/skills/refactor/SKILL.md
design: frontend/skills/design/SKILL.md

## Sprint Context
current_sprint: frontend/sprints/current.md

## Workflow
1. Read root AGENT.md for global context
2. Read this file for frontend context
3. Check frontend/sprints/current.md — if blocked, stop and report
4. Verify docs/contracts/openapi.json exists before writing any API call
5. Identify task type (coding, testing, refactor, design)
6. Load the matching skill from frontend/skills/
7. Follow Agent Instructions in current.md for this sprint
8. Never store JWT in localStorage or Pinia
9. Commit following: frontend/type/short-description

## Agent Compatibility
# Plain structured natural language.
# Compatible with: Claude Code, Gemini, and local models.
