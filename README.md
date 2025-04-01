# Proyecto Spring Boot - Estudiante

Este es un proyecto de backend desarrollado en **Spring Boot** para gestionar información de estudiantes de la Universidad Mayor de San Andres, fue creado como parte de la primera practica de la materia de DESARROLO WEB BACKEND, 

## Descripción

Este proyecto permite realizar operaciones CRUD (Crear, Listar, Actualizar, Eliminar) para manejar los datos de estudiantes, sin usar una gestor de base de datos. Está diseñado como una API RESTful, utilizando Spring Boot para facilitar el desarrollo rápido y escalable.

## Funcionalidades

- **Crear Estudiante**: Permite agregar un nuevo estudiante.
- **Listar Estudiantes**: Devuelve una lista de todos los estudiantes registrados.
- **Actualizar Estudiante**: Permite modificar la información de un estudiante existente.
- **Eliminar Estudiante**: Permite eliminar un estudiante por su ID.

## Requisitos

- Java 11 o superior
- Spring Boot 2.x
- Maven

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/JhessicaV/proyecto-spring-boot-Estudiante.git
2. Navega al directorio de proyecto:
   cd proyecto-spring-boot-Estudiante
3. Compila el proyecto usando el comando:
   mvn clean install
4. Ejecuta la aplicacion con el siguiente comando:
   java -jar.\target\mi-proyecto-spring-boot-0.0.1-SNAPSHOT.jar
   
## Endpoints

1. GET /api/estudiantes: Obtiene todos los estudiantes.
2. POST /api/estudiantes: Crea un nuevo estudiante.
3. PUT /api/estudiantes/{id}: Actualiza la información de un estudiante por su ID.
4. DELETE /api/estudiantes/{id}: Elimina un estudiante por su ID.
   
## Contribución
Si deseas contribuir a este proyecto, por favor sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una nueva rama para tu característica o corrección de error.
3. Haz tus cambios y crea un pull request.

