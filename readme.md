# VitaSync API Documentation

This document outlines all CRUD operations for each main module of the VitaSync platform. Each operation includes the expected HTTP method, endpoint, and JSON format for requests and responses.

---

## 1. User Account & Authentication

### Register User

* **POST** `/api/users/register`

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan@example.com",
  "clave": "securePassword123"
}
```

### Login User

* **POST** `/api/users/login`

```json
{
  "correo": "juan@example.com",
  "clave": "securePassword123"
}
```

### Get User Profile

* **GET** `/api/users/{id}`

### Update User Profile

* **PUT** `/api/users/{id}`

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "nuevo@email.com"
}
```

---

## 2. Task Management

### Create Task

* **POST** `/api/tareas`

### Get Task by ID

* **GET** `/api/tareas/{id}`

### List Tasks by User

* **GET** `/api/tareas?usuarioId=1`

### Update Task

* **PUT** `/api/tareas/{id}`

### Delete Task

* **DELETE** `/api/tareas/{id}`

### Create Category

* **POST** `/api/categorias`

### List Categories by User

* **GET** `/api/categorias?usuarioId=1`

### Update Category

* **PUT** `/api/categorias/{id}`

### Delete Category

* **DELETE** `/api/categorias/{id}`

---

## 3. Routine Service

### Create Routine

* **POST** `/api/rutinas`

### List Routines by User

* **GET** `/api/rutinas?usuarioId=1`

### Get Routine by ID

* **GET** `/api/rutinas/{id}`

### Update Routine

* **PUT** `/api/rutinas/{id}`

### Delete Routine

* **DELETE** `/api/rutinas/{id}`

### Log Routine Step Completion

* **POST** `/api/rutinas/{id}/log`

### Unlog Routine Step Completion

* **DELETE** `/api/rutinas/{id}/log?stepId=xx&date=YYYY-MM-DD`

### Get Routine Progress for Day

* **GET** `/api/rutinas/{id}/progreso?date=YYYY-MM-DD`

---

## 4. Habit Tracking

### Create Habit

* **POST** `/api/habitos`

### List Habits by User

* **GET** `/api/habitos?usuarioId=1`

### Get Habit by ID

* **GET** `/api/habitos/{id}`

### Update Habit

* **PUT** `/api/habitos/{id}`

### Delete Habit

* **DELETE** `/api/habitos/{id}`

### Log Habit Completion

* **POST** `/api/habitos/{id}/registro`

### Remove Habit Completion

* **DELETE** `/api/habitos/{id}/registro?date=YYYY-MM-DD`

### Get Habit Completion History

* **GET** `/api/habitos/{id}/historial`

---

## 5. Finance Service

### Create Transaction

* **POST** `/api/finanzas/transacciones`

### List Transactions

* **GET** `/api/finanzas/transacciones?usuarioId=1`

### Get Transaction by ID

* **GET** `/api/finanzas/transacciones/{id}`

### Update Transaction

* **PUT** `/api/finanzas/transacciones/{id}`

### Delete Transaction

* **DELETE** `/api/finanzas/transacciones/{id}`

### Get Financial Summary

* **GET** `/api/finanzas/resumen?usuarioId=1&periodo=MM-YYYY`

### Create Savings Goal

* **POST** `/api/finanzas/ahorros`

### List Savings Goals

* **GET** `/api/finanzas/ahorros?usuarioId=1`

### Get Savings Goal by ID

* **GET** `/api/finanzas/ahorros/{id}`

### Update Savings Goal

* **PUT** `/api/finanzas/ahorros/{id}`

### Delete Savings Goal

* **DELETE** `/api/finanzas/ahorros/{id}`

---

## 6. Emotional Journal

### Create Emotion Log Entry

* **POST** `/api/emociones`

### List Emotion Entries

* **GET** `/api/emociones?usuarioId=1`

### Get Emotion Entry by ID

* **GET** `/api/emociones/{id}`

### Update Emotion Entry

* **PUT** `/api/emociones/{id}`

### Delete Emotion Entry

* **DELETE** `/api/emociones/{id}`

### Get Emotion Statistics

* **GET** `/api/emociones/estadisticas?usuarioId=1&periodo=MM-YYYY`

---

## 7. Household Chores

### Create Chore

* **POST** `/api/quehaceres`

### List Chores

* **GET** `/api/quehaceres?usuarioId=1`

### Get Chore by ID

* **GET** `/api/quehaceres/{id}`

### Update Chore

* **PUT** `/api/quehaceres/{id}`

### Delete Chore

* **DELETE** `/api/quehaceres/{id}`

### Log Chore Completion

* **POST** `/api/quehaceres/{id}/registro`

### Get Completion Status

* **GET** `/api/quehaceres/{id}/estado?date=YYYY-MM-DD`

### List Chore Logs

* **GET** `/api/quehaceres/{id}/registros?from=YYYY-MM-DD&to=YYYY-MM-DD`

---

## 8. Shopping List

### Create Shopping Item

* **POST** `/api/compras`

### List Items

* **GET** `/api/compras?usuarioId=1`

### Get Item by ID

* **GET** `/api/compras/{id}`

### Update Item

* **PUT** `/api/compras/{id}`

### Delete Item

* **DELETE** `/api/compras/{id}`

---

## 9. Calendar Events

### Create Event

* **POST** `/api/eventos`

### List Events

* **GET** `/api/eventos?usuarioId=1&from=YYYY-MM-DD&to=YYYY-MM-DD`

### Get Event by ID

* **GET** `/api/eventos/{id}`

### Update Event

* **PUT** `/api/eventos/{id}`

### Delete Event

* **DELETE** `/api/eventos/{id}`
