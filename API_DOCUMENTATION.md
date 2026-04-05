# Haulage Management System - API Documentation

## Base URL
```
http://localhost:8080/api
```

---

## Authentication

### Overview
The API uses JWT (JSON Web Token) based authentication. All protected endpoints require a valid Bearer token in the `Authorization` header.

### Header Format
```
Authorization: Bearer <your_jwt_token>
```

---

## Auth Endpoints

### 1. Register User
Create a new user account.

**Endpoint:** `POST /api/auth/register`

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

**Request Validation:**
- `username`: Required, 3-64 characters
- `password`: Required, 6-128 characters

**Response:**
- **Status:** `201 Created`
- **Body:** Empty

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securePassword123"
  }'
```

**Error Responses:**
- `400 Bad Request`: Validation error (username/password invalid format)
- `409 Conflict`: Username already exists

---

### 2. Login User
Authenticate and obtain a JWT token.

**Endpoint:** `POST /api/auth/login`

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer"
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securePassword123"
  }'
```

**Error Responses:**
- `401 Unauthorized`: Invalid username or password

---

## Drivers Endpoints

### 1. Create Driver
Add a new driver to the system.

**Endpoint:** `POST /api/drivers`

**Request Body:**
```json
{
  "name": "John Smith",
  "licenseNumber": "DL123456",
  "phoneNumber": "+1234567890"
}
```

**Response:**
- **Status:** `201 Created`
- **Body:**
```json
{
  "driverId": 1,
  "name": "John Smith",
  "licenseNumber": "DL123456",
  "phoneNumber": "+1234567890"
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/drivers \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Smith",
    "licenseNumber": "DL123456",
    "phoneNumber": "+1234567890"
  }'
```

---

### 2. Get Driver by ID
Retrieve driver details by ID.

**Endpoint:** `GET /api/drivers/{id}`

**Parameters:**
- `id` (path): Driver ID (integer)

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "driverId": 1,
  "name": "John Smith",
  "licenseNumber": "DL123456",
  "phoneNumber": "+1234567890"
}
```

**Example cURL:**
```bash
curl -X GET http://localhost:8080/api/drivers/1 \
  -H "Authorization: Bearer <token>"
```

**Error Responses:**
- `404 Not Found`: Driver not found

---

### 3. Get All Drivers
Retrieve a list of all drivers.

**Endpoint:** `GET /api/drivers`

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
[
  {
    "driverId": 1,
    "name": "John Smith",
    "licenseNumber": "DL123456",
    "phoneNumber": "+1234567890"
  },
  {
    "driverId": 2,
    "name": "Jane Doe",
    "licenseNumber": "DL654321",
    "phoneNumber": "+0987654321"
  }
]
```

**Example cURL:**
```bash
curl -X GET http://localhost:8080/api/drivers \
  -H "Authorization: Bearer <token>"
```

---

### 4. Update Driver
Update driver information.

**Endpoint:** `PUT /api/drivers/{id}`

**Parameters:**
- `id` (path): Driver ID (integer)

**Request Body:**
```json
{
  "name": "John Smith Updated",
  "licenseNumber": "DL123456",
  "phoneNumber": "+1234567890"
}
```

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "driverId": 1,
  "name": "John Smith Updated",
  "licenseNumber": "DL123456",
  "phoneNumber": "+1234567890"
}
```

**Example cURL:**
```bash
curl -X PUT http://localhost:8080/api/drivers/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Smith Updated",
    "licenseNumber": "DL123456",
    "phoneNumber": "+1234567890"
  }'
```

---

### 5. Delete Driver
Remove a driver from the system.

**Endpoint:** `DELETE /api/drivers/{id}`

**Parameters:**
- `id` (path): Driver ID (integer)

**Response:**
- **Status:** `204 No Content`

**Example cURL:**
```bash
curl -X DELETE http://localhost:8080/api/drivers/1 \
  -H "Authorization: Bearer <token>"
```

---

## Trucks Endpoints

### 1. Create Truck
Add a new truck to the system.

**Endpoint:** `POST /api/trucks`

**Request Body:**
```json
{
  "registrationNumber": "TRUCK-001",
  "capacity": 5000,
  "status": "AVAILABLE"
}
```

**Status Values:** `AVAILABLE`, `IN_TRANSIT`, `UNDER_MAINTENANCE`

**Response:**
- **Status:** `201 Created`
- **Body:**
```json
{
  "truckId": 1,
  "registrationNumber": "TRUCK-001",
  "capacity": 5000,
  "status": "AVAILABLE"
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/trucks \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "registrationNumber": "TRUCK-001",
    "capacity": 5000,
    "status": "AVAILABLE"
  }'
```

---

### 2. Get Truck by ID
Retrieve truck details by ID.

**Endpoint:** `GET /api/trucks/{id}`

**Parameters:**
- `id` (path): Truck ID (integer)

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "truckId": 1,
  "registrationNumber": "TRUCK-001",
  "capacity": 5000,
  "status": "AVAILABLE"
}
```

**Example cURL:**
```bash
curl -X GET http://localhost:8080/api/trucks/1 \
  -H "Authorization: Bearer <token>"
```

---

### 3. Get All Trucks
Retrieve a list of all trucks.

**Endpoint:** `GET /api/trucks`

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
[
  {
    "truckId": 1,
    "registrationNumber": "TRUCK-001",
    "capacity": 5000,
    "status": "AVAILABLE"
  },
  {
    "truckId": 2,
    "registrationNumber": "TRUCK-002",
    "capacity": 7500,
    "status": "IN_TRANSIT"
  }
]
```

**Example cURL:**
```bash
curl -X GET http://localhost:8080/api/trucks \
  -H "Authorization: Bearer <token>"
```

---

### 4. Update Truck
Update truck information.

**Endpoint:** `PUT /api/trucks/{id}`

**Parameters:**
- `id` (path): Truck ID (integer)

**Request Body:**
```json
{
  "registrationNumber": "TRUCK-001",
  "capacity": 5500
}
```

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "truckId": 1,
  "registrationNumber": "TRUCK-001",
  "capacity": 5500,
  "status": "AVAILABLE"
}
```

**Example cURL:**
```bash
curl -X PUT http://localhost:8080/api/trucks/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "registrationNumber": "TRUCK-001",
    "capacity": 5500
  }'
```

---

### 5. Delete Truck
Remove a truck from the system.

**Endpoint:** `DELETE /api/trucks/{id}`

**Parameters:**
- `id` (path): Truck ID (integer)

**Response:**
- **Status:** `204 No Content`

**Example cURL:**
```bash
curl -X DELETE http://localhost:8080/api/trucks/1 \
  -H "Authorization: Bearer <token>"
```

---

## Jobs Endpoints

### 1. Create Job
Create a new job assignment.

**Endpoint:** `POST /api/jobs`

**Request Body:**
```json
{
  "truckId": 1,
  "driverId": 1,
  "pickupLocation": "Warehouse A",
  "deliveryLocation": "Customer Location B",
  "cargoDescription": "Electronics shipment"
}
```

**Response:**
- **Status:** `201 Created`
- **Body:**
```json
{
  "jobId": 1,
  "pickupLocation": "Warehouse A",
  "deliveryLocation": "Customer Location B",
  "cargoDescription": "Electronics shipment",
  "status": "PENDING",
  "truckId": 1,
  "driverId": 1
}
```

**Example cURL:**
```bash
curl -X POST http://localhost:8080/api/jobs \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "truckId": 1,
    "driverId": 1,
    "pickupLocation": "Warehouse A",
    "deliveryLocation": "Customer Location B",
    "cargoDescription": "Electronics shipment"
  }'
```

---

### 2. Get Job by ID
Retrieve job details by ID.

**Endpoint:** `GET /api/jobs/{id}`

**Parameters:**
- `id` (path): Job ID (integer)

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "jobId": 1,
  "pickupLocation": "Warehouse A",
  "deliveryLocation": "Customer Location B",
  "cargoDescription": "Electronics shipment",
  "status": "PENDING",
  "truckId": 1,
  "driverId": 1
}
```

**Example cURL:**
```bash
curl -X GET http://localhost:8080/api/jobs/1 \
  -H "Authorization: Bearer <token>"
```

---

### 3. Get All Jobs
Retrieve a list of all jobs.

**Endpoint:** `GET /api/jobs`

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
[
  {
    "jobId": 1,
    "pickupLocation": "Warehouse A",
    "deliveryLocation": "Customer Location B",
    "cargoDescription": "Electronics shipment",
    "status": "PENDING",
    "truckId": 1,
    "driverId": 1
  },
  {
    "jobId": 2,
    "pickupLocation": "Warehouse C",
    "deliveryLocation": "Customer Location D",
    "cargoDescription": "Furniture shipment",
    "status": "IN_TRANSIT",
    "truckId": 2,
    "driverId": 2
  }
]
```

**Example cURL:**
```bash
curl -X GET http://localhost:8080/api/jobs \
  -H "Authorization: Bearer <token>"
```

---

### 4. Update Job Status
Update the status of a job.

**Endpoint:** `PATCH /api/jobs/{id}/status`

**Parameters:**
- `id` (path): Job ID (integer)

**Request Body:**
```json
{
  "status": "IN_TRANSIT"
}
```

**Status Values:** `PENDING`, `ASSIGNED`, `IN_TRANSIT`, `DELIVERED`, `CANCELLED`

**Response:**
- **Status:** `200 OK`
- **Body:**
```json
{
  "jobId": 1,
  "pickupLocation": "Warehouse A",
  "deliveryLocation": "Customer Location B",
  "cargoDescription": "Electronics shipment",
  "status": "IN_TRANSIT",
  "truckId": 1,
  "driverId": 1
}
```

**Example cURL:**
```bash
curl -X PATCH http://localhost:8080/api/jobs/1/status \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "status": "IN_TRANSIT"
  }'
```

---

### 5. Delete Job
Remove a job from the system.

**Endpoint:** `DELETE /api/jobs/{id}`

**Parameters:**
- `id` (path): Job ID (integer)

**Response:**
- **Status:** `204 No Content`

**Example cURL:**
```bash
curl -X DELETE http://localhost:8080/api/jobs/1 \
  -H "Authorization: Bearer <token>"
```

---

## Enums

### JobStatus
- `PENDING` - Job waiting to be assigned
- `ASSIGNED` - Job assigned to driver and truck
- `IN_TRANSIT` - Job in progress
- `DELIVERED` - Job completed successfully
- `CANCELLED` - Job cancelled

### TruckStatus
- `AVAILABLE` - Truck available for new jobs
- `IN_TRANSIT` - Truck currently on a job
- `UNDER_MAINTENANCE` - Truck under maintenance

---

## Error Responses

### Standard Error Format
All error responses follow this format:

```json
{
  "timestamp": "2026-04-05T10:30:00Z",
  "status": 400,
  "message": "Error message here",
  "details": "Additional error details"
}
```

### Common HTTP Status Codes
- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `204 No Content` - Request successful, no content to return
- `400 Bad Request` - Invalid request parameters or validation error
- `401 Unauthorized` - Missing or invalid authentication token
- `404 Not Found` - Resource not found
- `409 Conflict` - Resource conflict (e.g., username already exists)
- `500 Internal Server Error` - Server error

---

## Authentication Flow Example

1. **Register User:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securePassword123"
  }'
```

2. **Login and Get Token:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securePassword123"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer"
}
```

3. **Use Token in Requests:**
```bash
curl -X GET http://localhost:8080/api/drivers \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## Rate Limiting & Pagination

Currently, the API does not implement rate limiting or pagination. All `GET` endpoints return the complete list of resources.

---

## Future Enhancements

Potential improvements for the API:
- Pagination support for list endpoints
- Rate limiting to prevent abuse
- Advanced filtering and search capabilities
- Batch operations
- WebSocket support for real-time updates
- API versioning

---

## Support

For issues or questions regarding this API, please refer to the project's GitHub repository or contact the development team.
