# Tourist Agency App

This project is a **tourist agency application** built with a **Spring Boot** backend and a **Vaadin** frontend. It uses **MySQL** for data storage, **Hibernate** for ORM, and integrates with external APIs for weather and currency exchange rates.

---

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Database Setup](#database-setup)
- [Entities & Relationships](#entities--relationships)
- [API Endpoints & Swagger](#api-endpoints--swagger)
- [Audit Tables & Triggers](#audit-tables--triggers)
- [External APIs](#external-apis)
- [Design Patterns](#design-patterns)
- [Testing](#testing)
- [Frontend](#frontend)
- [Scheduler](#scheduler)

---

## Features

- Manage users, trips, hotels, reservations and more.
- Record audit events for key actions.
- Integrate with external weather and currency exchange APIs.
- Vaadin-based frontend for user interaction.
- Seed data loaded automatically on startup.

---

## Getting Started

1. **Clone the repository.**
2. **Create a MySQL database named `kodilla_project`. User credentials from application.yaml file**
3. **Start the application** (`./gradlew bootRun` or from your IDE).
4. The database will be seeded with initial data from `data.sql`.
5. **Manually create audit tables and triggers** by running the queries from `audit_and_triggers.sql`.

## Entities & Relationships

### Main Entities

- **User**: Represents a user of the system. Related to reservations and reviews.
- **Trip**: Represents a travel offer. Related to hotels, reservations, and reviews.
- **Hotel**: Linked to trips and amenities.
- **Reservation**: Connects users and trips.
- **Amenity**: Linked to hotels.
- **Review**: Linked to users and trips.
- **Location**: Used by hotels and trips.
- **Weather**: Linked to locations.

### Relationships

- **User** ⟷ **Reservation** (One-to-Many)
- **User** ⟷ **Review** (One-to-Many)
- **Trip** ⟷ **Reservation** (One-to-Many)
- **Trip** ⟷ **Review** (One-to-Many)
- **Trip** ⟷ **Hotel** (Many-to-Many)
- **Hotel** ⟷ **Amenity** (Many-to-Many)
- **Hotel** ⟷ **Location** (Many-to-One)
- **Trip** ⟷ **Location** (Many-to-One)
- **Weather** ⟷ **Location** (One-to-One)

---

## API Endpoints & Swagger

- The backend exposes REST endpoints for all main entities.
- **Swagger UI** is available for exploring and testing endpoints.
- Example endpoints:
  - `/api/users`
  - `/api/trips`
  - `/api/hotels`
  - `/api/reservations`
  - `/api/amenities`
  - `/api/reviews`
  - `/api/weather/current/{city}`
  - `/api/frankfurter/exchange-rate/{base}`

---

## Audit Tables & Triggers

- The project records key events to audit tables.
- **Audit tables and triggers must be created manually** using the `audit_and_triggers.sql` file.
- The design of these tables is described in that file.

---

## External APIs

- **Weather API**:  
  Retrieve current weather for a location:  
  `http://localhost:8080/api/weather/current/Rome`
- **Frankfurter API**:  
  Get exchange rates for a base currency:  
  `http://localhost:8080/api/frankfurter/exchange-rate/USD`

---

## Design Patterns

- **Facade**: Used to simplify complex operations and API integrations.
- **Builder**: Used for constructing complex objects.

---

## Testing

- The project includes tests for:
  - One controller
  - One service
  - One mapper

---

## Frontend

- The Vaadin frontend is available at:  
  `http://localhost:8080/app`
- **Features:**
  - View the list of trips.
  - Register a new user at `/app/register`.
  - Book a trip at `/app/book` by selecting a trip and a user.

---

## Scheduler

There is also an application of a Scheduler, which sends emails to users regarding trips cheaper than $700 every day at 9:00 AM, as specified by `@Scheduled(cron = "0 0 9 * * ?")` in the backend.

---

## Notes

- Make sure to configure your MySQL credentials in `application.yaml`.
- For full audit functionality, run the SQL in `audit_and_triggers.sql` after the initial setup.

