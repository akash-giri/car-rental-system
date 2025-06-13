# 🚗 Car Rental System

A secure, RESTful **Car Rental System** built with **Spring Boot 3.4**, **Java 21**, and **MySQL**. This system allows customers to browse, search, and reserve cars, and allows admins to manage the car inventory securely using role-based access control (RBAC) with JWT authentication.

---

## 📋 Features

### 👤 Authentication & Authorization
- JWT-based login and registration
- Role-based access: `ROLE_USER` and `ROLE_ADMIN`
- Admin-only access for managing car inventory

### 🚘 Car Management (Admin only)
- Add new cars
- Update car details
- Delete cars
- View all cars

### 🔍 Car Browsing (Public/User)
- Browse available cars
- Search cars by:
  - Make
  - Price range
  - Car type
  - Availability

### 📅 Reservation (User)
- Reserve a car for specific dates
- Modify or cancel a reservation
- View personal reservations

### 💳 Payment & Availability Tracking
- Car availability automatically updates based on reservations
- Placeholder for payment processing logic

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot 3.4.3
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Lombok
- Swagger (OpenAPI 3)
- Postman for API testing

---

## 🧩 Roles & Admin Setup

### 🛡️ Default Admin User

Upon application startup, the following admin is automatically seeded into the DB:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

### 🔐 Security Configuration

- All `/admin/**` endpoints are secured using `ROLE_ADMIN`
- All users (including admins) must log in to obtain a JWT token

---

## 🧪 How to Test (Using Postman)

1. **Import the Postman Collection**  
   [Download here](./car-rental-admin-collection.json)

2. **Login as Admin**
   ```
   POST /auth/login
   Body:
   {
     "username": "admin",
     "password": "admin123"
   }
   ```

3. **Copy Token** from the login response and add it as Bearer Token in Postman.

4. **Use Admin Endpoints**
   - Add a Car: `POST /admin/cars`
   - Update a Car: `PUT /admin/cars/{id}`
   - Delete a Car: `DELETE /admin/cars/{id}`
   - Get All Cars: `GET /admin/cars`

---

## 🧱 Folder Structure

```
src/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AdminCarController.java
│   └── AuthController.java
├── dto/
│   ├── JwtResponse.java
│   ├── LoginRequest.java
│   └── RegisterRequest.java
├── entity/
│   ├── Car.java
│   └── User.java
├── repository/
│   ├── CarRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtAuthenticationFilter.java
│   ├── JwtTokenProvider.java
│   └── CustomUserDetailsService.java
├── service/
│   └── CarService.java
│   └── impl/CarServiceImpl.java
└── CarRentalApplication.java
```

---

## 🧾 How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/car-rental-system.git
   cd car-rental-system
   ```

2. Configure MySQL in `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. Run the app:
   ```bash
   mvn spring-boot:run
   ```

4. Access Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 📂 Postman Collection

✅ [car-rental-admin-collection.json](./car-rental-admin-collection.json) included in the root directory. Import this into Postman to test all APIs.

---

## 📌 Future Enhancements

- Payment gateway integration
- Email/SMS notifications
- Admin dashboard UI
- Car image uploads
- Search optimization

---

## 👨‍💻 Author

**Akash Giri**  
Senior Full-Stack Developer | Spring Boot, React.js, Kafka, AWS  
[LinkedIn](https://www.linkedin.com/) (add your link)

---

## 📝 License

This project is open-source and available under the [MIT License](LICENSE).
