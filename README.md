# **Project: Resident City Admin**

This project is a RESTful API built with **Java**, **Spring Boot**, and **PostgreSQL**. It allows user management and city validation through the **GeoDB Cities API**. It also implements global exception handlers for centralized error management, following best practices for handling exceptions.

---

## **Project Features**

- Full CRUD for user management.
- City validation using the GeoDB Cities API.
- Global exception controllers for error handling.
- PostgreSQL database connection.
- Modular structure using DTOs, Services, and Repositories.

---

## **Technologies Used**

- **Java 17**  
- **Spring Boot**  
- **PostgreSQL**  
- **Jakarta validation libraries**  
- **Lombok**  
- **RestTemplate** for external API integrations.

---

## **Instructions to Run the Project**

### **Prerequisites**

- **Java Development Kit (JDK 17 or higher)**  
- **Maven** for dependency management.  
- **PostgreSQL** (local or remote instance, e.g., AWS RDS).  
- **pgAdmin** (optional, to manage the database).  
- **Postman** or **cURL** to test the endpoints.

---

### **Project Setup**

1. **Clone the project:**
   ```bash
   git clone <REPOSITORY_URL>
   cd resident-city-admin
   ```

2. **Set up database credentials:**

   Edit the `application.properties` or `application.yml` file:
   ```properties
   spring.datasource.url=jdbc:postgresql://<YOUR_HOST>:5432/<YOUR_DATABASE>
   spring.datasource.username=<YOUR_USERNAME>
   spring.datasource.password=<YOUR_PASSWORD>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

   **AWS RDS Example:**
   ```properties
   spring.datasource.url=jdbc:postgresql://db-example.us-east-1.rds.amazonaws.com:5432/resident_db
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```

3. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

4. **Run the project:**
   ```bash
   mvn spring-boot:run
   ```

---

## **Testing the Endpoints**

### **Available Endpoints**

1. **Create User**  
   **Method:** POST  
   **URL:** `http://localhost:8080/user`  
   **Body (JSON):**
   ```json
   {
     "name": "Alice Doe",
     "email": "alice@example.com",
     "city": "New York"
   }
   ```
   **cURL:**
   ```bash
   curl -X POST http://localhost:8080/user \
   -H "Content-Type: application/json" \
   -d '{"name": "Alice Doe", "email": "alice@example.com", "city": "New York"}'
   ```

2. **Get User by ID**  
   **Method:** GET  
   **URL:** `http://localhost:8080/user/by-id/{id}`  
   **cURL:**
   ```bash
   curl -X GET http://localhost:8080/user/by-id/1
   ```

3. **Get User by Email**  
   **Method:** GET  
   **URL:** `http://localhost:8080/user/by-email/{email}`  
   **cURL:**
   ```bash
   curl -X GET http://localhost:8080/user/by-email/alice@example.com
   ```

4. **Find Users by City**  
   **Method:** GET  
   **URL:** `http://localhost:8080/user/{city}`  
   **cURL:**
   ```bash
   curl -X GET http://localhost:8080/user/New%20York
   ```

5. **Partially Update User**  
   **Method:** PATCH  
   **URL:** `http://localhost:8080/user/{Id}`  
   **Body (JSON):**
   ```json
   {
     "name": "Alice Doe Updated"
   }
   ```
   **cURL:**
   ```bash
   curl -X PATCH http://localhost:8080/user/1 \
   -H "Content-Type: application/json" \
   -d '{"name": "Alice Doe Updated"}'
   ```

6. **Delete User by ID**  
   **Method:** DELETE  
   **URL:** `http://localhost:8080/user/{Id}`  
   **cURL:**
   ```bash
   curl -X DELETE http://localhost:8080/user/1
   ```

---

## **Exception Handling**

The project uses a **Global Exception Handler** to manage errors in a centralized way. Some of the exceptions handled include:

- **InvalidCityException:** Thrown if the entered city does not exist in the GeoDB Cities API.
- **ResourceNotFoundException:** Thrown if the requested resource is not found.
- **InvalidEmailFormatException:** Thrown if the email format is invalid.
- **Exception:** Handles unspecified errors with a 500 response.

**Example Response for an Invalid City:**
```json
{
  "timestamp": "2024-10-28T10:30:00",
  "message": "The city New York doesn't exist."
}
```

---

## **Project Structure**

- **Controller:** Handles HTTP requests.
- **Service:** Contains business logic, including validations with the GeoDB API.
- **Repository:** Interacts with the PostgreSQL database.
- **DTO:** Transfers data between different layers of the project.
- **Entities:** Represents database tables.
- **Exception:** Global exception controller.

---

## **Integration with GeoDB Cities API**

**RestTemplate** is used to perform HTTP calls to the external API. The cities are validated through this API before creating users.

Example API call:
```java
String url = API_URL + city;
HttpHeaders headers = new HttpHeaders();
headers.set("X-RapidAPI-Host", RAPIDAPI_HOST);
headers.set("X-RapidAPI-Key", RAPIDAPI_KEY);
HttpEntity<String> entity = new HttpEntity<>(headers);
String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
```

---

## **How to Contribute**

1. Fork the project.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit (`git commit -m 'Add new feature'`).
4. Push your changes (`git push origin feature/new-feature`).
5. Open a Pull Request.

---

## **License**

This project is licensed under the MIT License. See the `LICENSE` file for more details.
