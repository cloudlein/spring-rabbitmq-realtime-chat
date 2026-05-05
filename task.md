# Project Roadmap: Realtime Chat App

Roadmap ini membantu kamu untuk tetap fokus pada apa yang harus dikerjakan selanjutnya. Gunakan `[x]` jika sudah selesai.

## Phase 1: Foundation (Entities & DTOs)
- [x] Design Database Entities (`User`, `Conversation`, `Message`).
- [x] Create DTOs for Authentication (`Login`, `Register`).
- [x] Create DTOs for User Management.
- [x] Create DTOs for Conversation.
- [x] Create DTOs for Message.
- [ ] Fix `UserResponseDto` (Hapus field password).

## Phase 2: Mapping & Persistence
- [ ] Implement `UserMapper` (Entity <-> DTO).
- [ ] Fix `ConversationMapper` (Handle participants mapping).
- [ ] Fix `MessageMapper` (Handle relation mapping).
- [ ] Create `UserRepository`.
- [ ] Create `ConversationRepository`.
- [ ] Create `MessageRepository`.

## Phase 3: Core Business Logic (Services)
- [ ] **AuthService**: Registrasi & Login (JWT).
- [ ] **UserService**: Cari user untuk diajak chat.
- [ ] **ConversationService**:
    - [ ] Create private chat.
    - [ ] Create group chat.
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
    - [ ] Auth Controller.
    - [ ] Conversation Controller.
    - [ ] Message Controller (History).
- [ ] **WebSocket Controller**:
    - [ ] Handle incoming messages.
    - [ ] Broadcast messages to RabbitMQ.

## Phase 6: Testing & Polish
- [ ] Test real-time message delivery between 2 users.
- [ ] Test group chat notifications.
- [ ] Exception Handling (Global Exception Handler).
- [ ] Documentation (Swagger/OpenAPI).

---
**Tips Guru:**
Fokuslah menyelesaikan **Phase 2** terlebih dahulu sebelum masuk ke logika RabbitMQ yang lebih kompleks.
