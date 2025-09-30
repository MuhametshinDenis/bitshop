# BitShop - Интернет-магазин

> ⚠️ **Проект находится в активной разработке**  
> Архитектура пока не полностью структурирована, так как требовалась быстрая реализация MVP.

## Описание проекта

BitShop - это современный интернет-магазин, построенный на монолитной архитектуре. Проект включает в себя полнофункциональный веб-интерфейс и масштабируемый backend с поддержкой поиска, управления товарами и пользователями.

## Архитектура

Проект состоит из следующих компонентов:

### Backend

- **Core Service** - основной сервис с бизнес-логикой

### Frontend

- **Client** - веб-приложение на Next.js

### База данных

- **PostgreSQL** - основная реляционная БД
- **Redis** - кэширование и сессии

## Технологический стек

### Backend

#### Core Service

- **Java 21** - основной язык программирования
- **Spring Boot 3.5.5** - фреймворк для создания приложений
- **Spring Security** - аутентификация и авторизация
- **Spring Data JPA** - работа с базой данных
- **GraphQL** - API для гибких запросов
- **PostgreSQL** - основная база данных
- **Redis** - кэширование и управление сессиями
- **Flyway** - миграции базы данных
- **AWS S3** - хранение файлов
- **SpringDoc OpenAPI** - документация API
- **Lombok** - упрощение кода
- **Docker** - контейнеризация
- **JUnit 5 and Mockito** - тестирование

### Frontend

- **Next.js 15** - React фреймворк с SSR/SSG
- **React 19** - библиотека для UI
- **TypeScript** - типизированный JavaScript
- **TailwindCSS 4** - CSS фреймворк
- **Radix UI** - компоненты UI
- **TanStack Query** - управление состоянием сервера
- **React Hook Form** - работа с формами
- **Zod** - валидация схем
- **Axios** - HTTP клиент
- **Bun** - JavaScript runtime и пакетный менеджер

### Инструменты разработки

- **Gradle (Kotlin DSL)** - сборка Java проектов
- **ESLint** - линтер для JavaScript/TypeScript
- **Prettier** - форматирование кода
- **Docker Compose** - оркестрация контейнеров

## Структура проекта

```
bit-shop/
├── backend/
│   ├── core/                 # Основной сервис
│   │   ├── src/
│   │   ├── build.gradle.kts
│   │   ├── docker-compose.yml
│   │   └── Dockerfile
│   └── search-service/       # Сервис поиска
│       ├── src/
│       └── build.gradle.kts
├── frontend/                 # Клиентское приложение
│   ├── src/
│   ├── package.json
│   └── next.config.ts
├── database-diagram.drawio   # Диаграмма БД
└── README.md
```

## Быстрый старт

### Требования

- Java 21+
- Bun (для frontend)
- Docker и Docker Compose
- PostgreSQL
- Redis

### Запуск backend

#### Core Service

```bash
cd backend/core
./gradlew bootRun
```

### Запуск frontend

```bash
cd frontend
bun install
bun dev
```

### Запуск с Docker

```bash
cd backend/core
docker-compose up
```

## API Документация

- **Core Service**: http://localhost:8080/api/v1/docs/swagger-ui
- **GraphQL Playground**: http://localhost:8080/graphiql

## Особенности

- **Современный стек** - использование последних версий технологий
- **Кэширование** - Redis для повышения производительности
- **Файловое хранилище** - интеграция с AWS S3
- **Типобезопасность** - TypeScript на frontend
- **Responsive дизайн** - адаптивный интерфейс с TailwindCSS
- **Валидация данных** - Zod схемы на frontend, Spring Validation на backend

## Статус разработки

Проект находится в активной разработке. Текущие приоритеты:

- Рефакторинг архитектуры
- Улучшение документации
- Добавление тестов
- Оптимизация производительности
