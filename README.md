# Data Element Market Supply-Side Management System - Backend

Frontend repository: [data-element-frontend](https://github.com/yexca/data-element-frontend)

## Overview

This project is a management system focused on the "supply side" of the data element market, developed to address the data explosion driven by the Fourth Industrial Revolution and the rise of Generative AI.

To overcome the challenges of traditional centralized data management, this system innovatively integrates SpringBoot, Elasticsearch, and the Web3 foundational technology of blockchain (Fisco Bcos). This integration provides efficient data search capabilities and immutable record-keeping, ensuring data authenticity and ownership.

## 🌟 Main Features

  * **RESTful APIs:** A suite of standardized APIs, designed and managed with Apifox, for communication with the frontend.
  * **Authentication & Authorization:** A JWT (JSON Web Token) based authentication system with request validation handled by interceptors.
  * **Role-Based Access Control (RBAC):** Fine-grained access control supporting multiple roles, including "Individual/Enterprise User" and "Employee/Administrator/Super Administrator".
  * **Data Search:** Leverages Elasticsearch to provide high-speed, full-text search capabilities for data products (utilizing the `ik_max_word` Chinese tokenizer).
  * **Blockchain Integration:** Records user registration information and data product metadata on the Fisco Bcos blockchain to ensure immutability of ownership and history.
  * **Unstructured Data Management:** Manages user-uploaded data product files (or samples) using Aliyun OSS (Object Storage Service).
  * **Robust Architecture:** Employs a layered architecture with a clear separation of concerns (Controller, Service, and Mapper/Data Access Layer).

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Core** | Java 17, Spring Boot |
| **Framework** | Spring MVC, MyBatis |
| **Database** | MySQL 8.2 |
| **Search Engine** | Elasticsearch 7.12.1 |
| **Blockchain** | Fisco Bcos |
| **Object Storage** | Aliyun OSS |
| **Authentication** | JWT (JSON Web Token) |
| **Build / Deployment** | Maven, Docker / Docker Compose |

## Getting Started

**1. Clone the repository:**

```bash
git clone https://github.com/yexca/data-element-backend.git
cd data-element-backend
```

**2. Set up dependencies:**

This system requires MySQL, Elasticsearch, Fisco Bcos, and Aliyun OSS to be running.

**3. Configure the application:**

Open `src/main/resources/application.yml` (and `application-prod.yml`) and update the configuration details for your services (database connection, OSS keys, Elasticsearch address, etc.).

**4. Run with Maven:**

```bash
mvn spring-boot:run
```

**5. Run with Docker (Recommended):**
You can build the image and run it as a container, (e.g., alongside MySQL and Elasticsearch using Docker Compose).

```bash
docker build -t yexca/data-element:v1.1 .
```
