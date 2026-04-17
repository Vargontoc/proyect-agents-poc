# Project Context
name: generic-base-project
purpose: Base tempalte to validate a layered multi-agent workflow structure
scope: Basic CRUD operations with minimal authentication
language: All code and comments must ve in English

# Stack:
backend: Java 21 + Spring Boot 3
frontend: Vue 3 + TypeScript
infraestructure: Docker + Docker Compose
auth: JWT (stateless)

# Layer Structure
infrastructure/ -> environment orchestration, Docker Compose, env vars
backend/ -> Spring Boot API, business logic, persistence
frontend/-> VUE 3 SPA, UI Components, API consumption
docs/ -> atchitecture decisions and design rationale

# Contracts
Single source of truth for inter-layer agreements
No layer should duplicate contract file locally
- docs/contracts/openapi.json -> Rest API contract between backend and frontend
- docs/contracts/websocket.json -> WebSocket event contracts (do not create untile needed)
- docs/contracts/ddl/ -> Database schema definitions (do not create untile needed)

# Sprint Structure
Sprints are managed per layer to reflect independent progress
The global template lives in docs/sprints/sprint_template.md
- docs/sprints/sprint_template.md -> global reusable template for all layers
- docs/sprints/history.md -> closed sprints archive (global)
- {layer}/sprints/current.md -> active sprint for the layer
- {layer}/sprints/history/ -> closed sprints archive for the layer
If a layer is blocked by another, current.md must declare:
- status: blocked
- blocked_by: {layer}/sprints/current.md
- waiting_for: description of what is needded
-
# Global Rules
- All code, comments, variable names and documentation must be in english
- Commit messages follow Conventional Commits: type(scope): description
- Branch naming: {layer}/type/short-description (e.g. backend/feature/user-auth)
- No secrets or credentials committed to the repository
- Each layer is independently deployable
- Changes to one layer muyst not break the contracts of other layers
- Frontend must never reference backend source directly, only via docs/contracts/

# Available Agents
infrastructure/AGENT.md -> manages Docker environments and service configuration
backend/AGENT.md -> handles API development, bus iness logic and persistence
frontend/AGENT.md -> handles UI development, state management and API integration

# Workflow
1. Read ths file first to understand the global project context
2. Identify wich layer the task belong to
3. Navigate to that layer and read its AGENT.ms before acting
4. Load the relevant skill from that layer's skills/ folder
7. Execute the task following the skill instructions
8. Commit changes following th Global Rules above

# Agent Compatibility
This file is written in plain structured natural language.
It is compatible with: Claude Code, Gemini, ChatGPT, and local models
No tool-specific syntax or commands are used.


## Git Workflow
# Add this section to the root AGENT.md

base_branch:   develop
protected:     true — no direct push allowed

## Agent Git Rules
rule: Never push directly to develop
rule: Always pull develop before starting a new sprint branch
rule: Branch name must follow {layer}/type/short-description
rule: One branch per sprint task — do not mix layer changes in one branch
rule: Commit messages must follow Conventional Commits:
        type(scope): description
        e.g. feat(auth): add JWT login endpoint
             fix(user): correct pagination response
             chore(infra): update healthcheck interval

## Sprint Start Sequence
# The agent must execute these steps at the start of every sprint:
1. git checkout develop
2. git pull origin develop
3. git checkout -b {layer}/type/short-description
4. Read {layer}/sprints/current.md
5. Begin work

## Sprint End Sequence
# The agent must execute these steps when all tasks are complete:
1. git add .
2. git commit -m "type(scope): description"
3. git push origin {current-branch}
4. Open PR to develop via GitHub
5. Notify human to review and merge
6. Do not merge without human confirmation
