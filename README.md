# Mini Store JDBC

Un sistema simple de gestión de inventario desarrollado en Java con conexión a base de datos MySQL mediante JDBC y una interfaz gráfica básica utilizando JOptionPane.

## Descripción

Esta aplicación permite administrar un inventario de productos con operaciones CRUD (Crear, Leer, Actualizar, Eliminar). Está diseñada siguiendo patrones de diseño como la inyección de dependencias y el patrón repositorio para mantener el código modular y fácil de mantener.

## Funcionalidades

- Añadir nuevos productos al inventario
- Listar todos los productos disponibles
- Actualizar precio de productos
- Actualizar stock de productos
- Eliminar productos
- Búsqueda de productos por nombre
- Resumen de operaciones realizadas

## Tecnologías Utilizadas

- Java 24
- MySQL
- JDBC
- Swing (JOptionPane)
- Maven

## Estructura del Proyecto

El proyecto sigue una arquitectura de capas:

- **Entity**: Objetos de dominio (Product)
- **Model**: Servicios y repositorios
- **Controller**: Controladores para la lógica de negocio
- **Database**: Configuración de la base de datos

## Configuración

1. Asegúrate de tener MySQL instalado
2. Configura el archivo `src/main/resources/db.properties` con tus credenciales:
   ```
   driver=com.mysql.cj.jdbc.Driver
   url=jdbc:mysql://localhost:3306/nombre_base_datos
   user=tu_usuario
   password=tu_contraseña
   ```
3. Crea la base de datos con la tabla de productos

## Ejecución

1. Compila el proyecto con Maven: `mvn clean package`
2. Ejecuta el archivo JAR generado o la clase Main desde tu IDE

## Requisitos

- JDK 24
- MySQL 8.0 o superior
- Maven