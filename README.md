# **Proyecto: Resident City Admin**

Este proyecto es una API REST creada con **Java, Spring Boot** y **PostgreSQL**, diseñada para gestionar usuarios, validar ciudades usando la API de GeoDB Cities, y manejar errores mediante controladores globales.

---

## **Instrucciones para Ejecutar el Proyecto**

### **Requisitos previos**

Asegúrate de tener las siguientes herramientas instaladas:

- **Java Development Kit (JDK 17 o superior)**  
- **Maven** (para gestionar las dependencias)  
- **PostgreSQL** (instancia local o remota, como AWS RDS)  
- **pgAdmin** (opcional, para administrar la base de datos)  
- **Postman** o **cURL** (para probar los endpoints)

---

### **Configuración del Proyecto**

1. **Clonar el proyecto:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd resident-city-admin
   ```

2. **Configurar las credenciales de la base de datos:**

   Edita el archivo `application.properties` o `application.yml`:

   ```properties
   spring.datasource.url=jdbc:postgresql://<TU_HOST>:5432/<TU_BASE_DE_DATOS>
   spring.datasource.username=<TU_USUARIO>
   spring.datasource.password=<TU_CONTRASEÑA>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

   **Ejemplo para AWS RDS:**

   ```properties
   spring.datasource.url=jdbc:postgresql://db-prueba.cf0kbmocirfw.us-east-1.rds.amazonaws.com:5432/resident_db
   spring.datasource.username=postgres
   spring.datasource.password=tu_contraseña
   ```

---

## **Probar los Endpoints con Postman o cURL**

### **Endpoints Disponibles:**

1. **Crear Usuario**  
   **Método:** POST  
   **URL:** `http://localhost:8080/users`  
   
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
   curl -X POST http://localhost:8080/users \
   -H "Content-Type: application/json" \
   -d '{"name": "Alice Doe", "email": "alice@example.com", "city": "New York"}'
   ```

2. **Obtener Usuario por ID**  
   **Método:** GET  
   **URL:** `http://localhost:8080/users/{id}`

   **cURL:**
   ```bash
   curl -X GET http://localhost:8080/users/1
   ```

3. **Obtener Usuarios por Ciudad**  
   **Método:** GET  
   **URL:** `http://localhost:8080/users/city/{city}`

   **cURL:**
   ```bash
   curl -X GET http://localhost:8080/users/city/New%20York
   ```

4. **Actualizar Usuario Parcialmente (PATCH)**  
   **Método:** PATCH  
   **URL:** `http://localhost:8080/users/{id}`

   **Body (JSON):**
   ```json
   {
     "name": "Alice Doe Updated"
   }
   ```

   **cURL:**
   ```bash
   curl -X PATCH http://localhost:8080/users/1 \
   -H "Content-Type: application/json" \
   -d '{"name": "Alice Doe Updated"}'
   ```

5. **Eliminar Usuario**  
   **Método:** DELETE  
   **URL:** `http://localhost:8080/users/{id}`

   **cURL:**
   ```bash
   curl -X DELETE http://localhost:8080/users/1
   ```

---

## **Manejo de Excepciones**

El proyecto implementa un controlador global de excepciones para manejar errores como:

- **InvalidCityException:** Si la ciudad ingresada no existe.
- **ResourceNotFoundException:** Si un recurso no es encontrado.

**Ejemplo de Respuesta para una ciudad inválida:**

```json
{
  "message": "The city New York doesn't exist."
}
```
