# 📌 Proyecto: Backend - BP - Ana Erazo

Este proyecto implementa dos microservicios para la gestión de clientes, cuentas bancarias y movimientos, utilizando **Spring Boot**, **MySQL** y **Docker**.

## 📖 Descripción
- **Microservicio de Clientes**: CRUD de clientes.
- **Microservicio de Cuentas y Movimientos**: CRUD de cuentas, registro de movimientos y generación de reportes.
- **Base de Datos**: MySQL para almacenamiento de datos.
- **Despliegue**: Docker Compose para la orquestación de los microservicios y la base de datos.

## 🚀 Tecnologías Utilizadas
- **Java 21** + Spring Boot
- **Spring Data JPA** + MySQL
- **Spring Feign (FeignClient)** para comunicación entre microservicios
- **Spring Boot Profiles** para configuración de entornos
- **Docker & Docker Compose** para despliegue

## 🛠️ Configuración Local

### 1️⃣ **Clonar el repositorio**
```bash

git clone https://github.com/aberazo011296/movimientosAE.git

```

### 2️⃣ **Configurar MySQL**
Asegúrate de tener una instancia de MySQL con los siguientes datos (o usa Docker):
```
Host: localhost  
Usuario: root  
Contraseña: 011296erazo  
Base de datos: transacciones  
```

### 3️⃣ **Configurar los perfiles de Spring Boot**

En `application.properties`, se encuentran las variables para desplegar localmente.

En `application-docker.properties`, se encuentran las variables para desplegar en docker.

### 4️⃣ **Ejecutar los microservicios**

**Ejecutar Clientes**:
```bash
cd clientes
./mvnw spring-boot:run;
```
**Ejecutar Cuentas y Movimientos**:
```bash
cd movimientos
./mvnw spring-boot:run;
```

## 📦 Despliegue con Docker

### 1️⃣ **Realizar el empaquetado de los microservicios**
**Empaquetar Clientes**:
```bash
cd clientes
./mvnw clean package -DskipTests;
```
**Empaquetar Cuentas y Movimientos**:
```bash
cd movimientos
./mvnw clean package -DskipTests;
```

### 2️⃣ **Ejecutar con Docker Compose**
```bash
docker compose up --build;
```

### 3️⃣ **Verificar los Contenedores**
```bash
docker ps
```

## 📡 API Endpoints

### 📌 **Microservicio de Clientes** (`http://localhost:8081`)
- **Obtener Clientes**: `GET /clientes`
- **Obtener Cliente por ID**: `GET /clientes/{id}`
- **Crear Cliente**: `POST /clientes`
- **Actualizar Cliente**: `PUT /clientes/{id}`
- **Eliminar Cliente**: `DELETE /clientes/{id}`

### 📌 **Microservicio de Cuentas y Movimientos** (`http://localhost:8082`)

#### **Cuentas:**
- **Obtener Cuentas**: `GET /clientes`
- **Crear Cuenta de Cliente**: `POST /cuentas`
- **Obtener Cuenta de un Cliente**: `GET /cuentas/cliente/{clienteId}`
- **Actualizar Cuenta**: `PUT /cuentas/{id}`

#### **Movimientos:**
- **Obtener Movimientos**: `GET /movimientos`
- **Obtener Movimientos de una Cuenta**: `GET /movimientos/cuenta/{cuentaId}`
- **Actualizar Movimiento**: `PUT /movimientos/{id}`
- **Registrar Movimiento de Cuenta**: `POST /movimientos`
- **Generar Reporte de Estado de Cuenta**: `GET /reportes?fechaInicio=YYYY-MM-DD&fechaFin=YYYY-MM-DD&clienteId={id}`

## 🧪 Pruebas

Ejecutar pruebas unitarias e integración con Maven:
```bash
mvn test
```

## 📝 Notas
- Si tienes problemas con la conexión en Docker, revisa los logs con:
  ```bash
  docker-compose logs -f
  ```
- Para eliminar los contenedores y volúmenes:
  ```bash
  docker-compose down -v
  ```

---