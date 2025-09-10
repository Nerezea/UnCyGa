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


## ✨ Features

- CRUD for **Teams** with fields:
  - `team_name`, `points`, `wins`, `losses`, `map_wins`, `map_losses`
- Persistence via **Spring Data JPA / Hibernate**
- **JavaFX** GUI with confirmation / alert dialogs

---

## 🛠 Tech stack

- **Java 17+** (works with 21)
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **JavaFX**
- **Gradle** (wrapper included)

---

## 🚀 Getting started

### 1) Prerequisites
- JDK **17+**
- **PostgreSQL** (or another relational DB depending on your config)
- Gradle Wrapper (in repo)

### 2) Configuration

This repo provides an example config at:

```
src/main/resources/application.properties.example
```

Copy it to `application.properties` (**git-ignored**) **or** set environment variables.

> **Note:** Your example file is configured to read credentials from environment variables **without defaults**:
>
> ```properties
> spring.datasource.url=${DB_URL:}
> spring.datasource.username=${DB_USER:}
> spring.datasource.password=${DB_PASSWORD:}
> 
> spring.datasource.hikari.maximum-pool-size=1
> spring.main.web-application-type=none
> spring.jpa.show-sql=true
> spring.jpa.properties.hibernate.format_sql=true
> spring.jpa.hibernate.ddl-auto=update
> ```
>
> This is safe to commit. Since there are no defaults, the app will only connect if you set the env vars locally.

**Copy example to active config:**
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

**Set environment variables (recommended):**

Windows (PowerShell):
```powershell
$env:DB_URL="jdbc:postgresql://127.0.0.1:5432/UnCyGa"
$env:DB_USER="postgres"
$env:DB_PASSWORD="your_local_password"
```

### 3) Database

Create a database (e.g. `UnCyGa`) and ensure the configured user has permissions.

### 4) Run

Using Gradle wrapper:
```bash
./gradlew bootRun
```

Or build and run the jar:
```bash
./gradlew clean build
java -jar build/libs/*.jar
```

**Startup behavior:** Spring Boot starts (without a web server) and then opens the **JavaFX** main window.

---

## 🖥 Usage

- Launch the application → the JavaFX main window appears.
- Manage teams via the GUI (create, list, update, delete).
- Confirmation and alert dialogs assist user interaction (e.g., on exit).

---