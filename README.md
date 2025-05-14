# Bloomberg Transaction Import

Welcome to the **Bloomberg Transaction Import** project! This project is designed to collect and analyze foreign exchange transactions for Bloomberg. The main objective is to import transactions into a database and handle validation, storage, and error management.

## Prerequisites

Before you begin, make sure you have the following tools installed:
- **Java** (JDK 17)
- **Maven**
- **Docker** (optional, but recommended for the production environment)
- **Docker Compose** (optional)

## Project Description

### Features:
- Collect foreign exchange transactions.
- Validate transactions before import (format, missing fields, etc.).
- Store transactions in a database (MySQL, PostgreSQL, or MongoDB).
- Error and exception handling with appropriate responses.
- Ensure transactions are imported only once (no duplicates).
- RESTful API for transaction import.

### Data Structure:
Transactions have the following fields:
- `transactionId`: Unique transaction ID.
- `sourceCurrencyCode`: ISO code of the source currency.
- `destinationCurrencyCode`: ISO code of the destination currency.
- `timestamp`: Transaction timestamp.
- `amount`: Transaction amount in the source currency.

## Setup

### 1. Clone the Project

Clone the GitHub repository to get the source code.

```bash
git clone https://github.com/your-username/bloomberg-transaction-import.git
cd bloomberg-transaction-import
```

## 📡 API Endpoints

### ▶️ Insert Transaction  
**Endpoint**: `POST /api/transactions/import`  
**URL**: `http://localhost:8080/api/transactions/import`  
**Description**: Imports a list of foreign exchange transactions into the database after validation.

---

### 📥 Request JSON Example:
```json
[
  {
    "transactionId": "TXN10001",
    "sourceCurrencyCode": "USD",
    "destinationCurrencyCode": "EUR",
    "timestamp": "2025-08-14T10:15:30",
    "amount": 2500.00
  },
  {
    "transactionId": "TXN10002",
    "sourceCurrencyCode": "GBP",
    "destinationCurrencyCode": "JPY",
    "timestamp": "2025-08-14T11:00:00",
    "amount": 4000.75
  }
]
```
### 📥 Response JSON Example:
Response (HTTP 200 OK):
```json
Transactions imported successfully
```

### ❌ Validation Rules

**Fields Validated:**
- **transactionId**: Must not be blank and must be unique.
- **sourceCurrencyCode**: Must be a valid 3-letter ISO code (e.g., "USD").
- **destinationCurrencyCode**: Must be a valid 3-letter ISO code (e.g., "EUR").
- **timestamp**: Must be a valid ISO timestamp (e.g., 2025-08-14T10:15:30).
- **amount**: Must be a positive decimal number.

**Validation Mechanism:**
- Uses Jakarta Validation annotations such as `@NotBlank`, `@NotNull`, `@Positive`.
- Custom currency validation via a list of valid ISO currency codes.
- Rejects duplicate transaction IDs.

---

## 🛢 Database Interaction

**Database:** MySQL / PostgreSQL (depending on configuration)

**Entity:** Transactions table with columns:
- **transaction_id** (String, Primary Key)
- **source_currency_code** (String, 3 chars)
- **destination_currency_code** (String, 3 chars)
- **timestamp** (LocalDateTime)
- **amount** (BigDecimal)

**Duplicate Prevention:** Unique constraint on **transaction_id**.

---

## 🧪 Testing

**Framework:** JUnit 5 with Spring Boot Test

**Coverage:**
- Service layer unit tests
- Validation tests for currency and structure

**Location:** `src/test/java`

**Run Tests:**
```bash
make test


