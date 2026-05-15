# Project Roadmap: Realtime Chat App

This roadmap helps you stay focused on what to do next. Use `[x]` when completed.

## Phase 1: Foundation (Entities & DTOs)
- [x] Design Database Entities (`User`, `Conversation`, `Message`).
- [x] Create DTOs for Authentication (`Login`, `Register`).
- [x] Create DTOs for User Management.
- [x] Create DTOs for Conversation.
- [x] Create DTOs for Message.
- [x] Fix `UserResponseDto` (Remove password field).

## Phase 2: Mapping & Persistence
- [x] Implement `UserMapper` (Entity <-> DTO).
- [x] Fix `ConversationMapper` (Handle participants mapping).
- [x] Fix `MessageMapper` (Handle relation mapping).
- [x] Create `UserRepository`.
- [x] Create `ConversationRepository`.
- [x] Create `MessageRepository`.

## Phase 3: Core Business Logic (Services)
- [x] **AuthService**: Registration & Login (JWT).
- [x] **UserService**: Search for users to chat with.
- [ ] **ConversationService**:
    - [x] Create private chat.
    - [x] Create group chat.
    - [ ] Get list of user conversations.
- [ ] **MessageService**:
    - [ ] Save message to DB.
    - [ ] Get message history for a conversation.

## Phase 4: Messaging Infrastructure (RabbitMQ & WebSocket)
- [ ] Configure RabbitMQ (Exchange, Queues, Routing Keys).
- [ ] Configure WebSocket (STOMP Endpoint, Message Broker).
- [ ] Implement WebSocket Security (JWT check on connection).

## Phase 5: API & Real-time Controllers
- [ ] **Rest Controllers**:
    - [x] Auth Controller.
    - [ ] Conversation Controller.
    - [ ] Message Controller (History).
- [ ] **WebSocket Controller**:
    - [ ] Handle incoming messages.
    - [ ] Broadcast messages to RabbitMQ.

## Phase 6: Testing & Polish
- [ ] Test real-time message delivery between 2 users.
- [ ] Test group chat notifications.
- [x] Exception Handling (Global Exception Handler).
- [ ] Documentation (Swagger/OpenAPI).
