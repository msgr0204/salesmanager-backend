# SalesManager Backend

## Descripción
El backend de **SalesManager** es una API desarrollada en **Java** con **Spring Boot** que gestiona las operaciones de un sistema de ventas, incluyendo productos, ventas y reportes.

Este servicio se conecta a una base de datos PostgreSQL para almacenar los datos y expone endpoints RESTful que pueden ser consumidos por el frontend de SalesManager.

---

## Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.4.1**
- **PostgreSQL**
- **Maven**
- **iTextPDF** (para generar reportes en formato PDF)

---

## Funcionalidades principales
1. **Gestión de Productos**:
   - Crear, leer, actualizar y eliminar productos.
2. **Gestión de Ventas**:
   - Registrar ventas y asociarlas con productos.
   - Validar stock antes de registrar la venta.
3. **Reportes de Ventas**:
   - Generar reportes diarios con el número de transacciones realizadas, lista de productos vendidos, cantidad total vendida por producto y el total de ingresos.
   - Exportar reportes en formato CSV y PDF.

---

## Requisitos previos
- **Java 17** o superior.
- **PostgreSQL** instalado y en funcionamiento.
- **Maven** para gestionar dependencias.

---

## Configuración e instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/msgr0204/salesmanager-backend.git
   cd salesmanager-backend
   ```

2. Configura las credenciales de la base de datos en `application.properties`:
  ```properties
spring.application.name=salesmanager-backend
spring.datasource.url=jdbc:postgresql://localhost:5432/sales_manager
spring.datasource.username=<tu_usuario_postgres>
spring.datasource.password=<tu_contraseña_postgres>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true
server.port=8081

   ```

3. Compila y ejecuta el proyecto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. La API estará disponible en: `http://localhost:8081`.

---

## Endpoints principales
- **Productos**:
  - `POST /api/products` - Crear producto.
  - `GET /api/products` - Obtener todos los productos.
  - `PUT /api/products/{id}` - Actualizar producto.
  - `DELETE /api/products/{id}` - Eliminar producto.

- **Ventas**:
  - `POST /api/sales` - Registrar una venta.
  - `GET /api/sales` - Listar todas las ventas.

- **Reportes**:
  - `GET /api/reports/daily/{date}` - Generar reporte diario.
  - `GET /api/reports/daily/{date}/export/csv` - Exportar reporte en CSV.
  - `GET /api/reports/daily/{date}/export/pdf` - Exportar reporte en PDF.

---

## Generar reportes de ejemplo
Los reportes diarios se generarán automáticamente en la carpeta `reports` del proyecto al utilizar los endpoints de exportación.

---

## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un **issue** o un **pull request** en este repositorio.

---

## Licencia
Este proyecto está licenciado bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más información.

---

