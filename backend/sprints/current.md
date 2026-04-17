## Goal
Set up the base Spring Boot project

## Status
status: closed
started_at: 2026-04-16
closed_at: 2026-04-17
blocked_by:
waiting_for:

## Tasks
- [ ] Generate Spring Boot 3 project with required dependencies
- [ ] Configure PostgreSQL datasource via environment variables
- [ ] Create layared Dockerfile

## Risks
- None

## Dependencies
- Requires db service running (see infrastruture/sprints/current.md)

## Agent Instructions
- Use layered JAR strategy for the dockerfile
- Use SPRING_PROFILES_ACTIVE to switch between local and prod configuration
- Do not hardcorde any value that exists in backed.env.example
- After completing all tasks, generate openapi.json anf place it in docs/contracts

## Notes
- Stack: Java 21 + Spring Boot 3 + PostgreSQL 16 + Liquibase + JWT

## Review