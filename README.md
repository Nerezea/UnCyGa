# UnCyGa – Universe Cyber Games

A Java desktop application combining **Spring Boot**, **Spring Data JPA**, and **JavaFX**.  
It allows you to manage and visualize eSports teams and their statistics.

The project integrates a relational database for persistence and provides a JavaFX GUI for user interaction.

---

## ✨ Features

- Manage **teams** with:
    - name
    - points
    - wins & losses
    - map wins & map losses
- Create, update, delete and query teams
- Persistence via **Spring Data JPA** and Hibernate
- Interactive GUI with **JavaFX**
- Confirmation and alert dialogs for safe user interaction

---

## 🛠 Tech stack

- **Java 17+**
- **Spring Boot** (application lifecycle, dependency injection)
- **Spring Data JPA / Hibernate** (ORM, persistence)
- **JavaFX** (desktop GUI)
- **Gradle** build system (with wrapper)


---

## 🚀 Getting started

### Prerequisites
- JDK 17+
- PostgreSQL (or another relational DB, depending on configuration)
- Gradle (wrapper included)

### 1. Database
Create a database (example name: `UnCyGa`) and adjust your `application.properties` accordingly.

### 2. Configuration
The repo provides **example configuration files**. Copy them and fill in your own credentials:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

### 3. Run
Using Gradle wrapper:

```bash
./gradlew bootRun
```

Or build and run:

```bash
./gradlew clean build
java -jar build/libs/*.jar
```

---

## 🖥 Usage

- Start the application → JavaFX main window appears.
- Teams can be displayed, created, updated, or deleted through the GUI.
- Dialog boxes (ConfirmBox, AlertBox) are used for user confirmation and feedback.