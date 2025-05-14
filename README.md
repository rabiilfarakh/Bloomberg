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
