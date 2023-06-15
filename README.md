# Proyecto ComandApp

Este es el repositorio del proyecto ComandApp, que consiste en una aplicación web desarrollada con Angular en el frontend y Java (Spring Boot) en el backend. 

Esta aplicación propone una solución sencilla y útil para cualquier trabajador del entorno hostelero, ayudando con la gestión de productos disponibles y los pedidos de las mesas del establecimiento. Consta de un login para controlar el acceso de usuario y un panel de mesas, que deriva en un menú de productos con los que interactuar. También tiene incluido un apartado de gestión de productos.

## Características

- Autenticación de usuarios con JWT.
- Consultas a la API RESTful desde el frontend.
- Manipulación de datos de productos y mesas.
- Seguridad basada en roles de usuario.

## Tecnologías utilizadas

- Frontend:
  - Angular
  - TypeScript
  - HTML
  - SCSS

- Backend:
  - Java
  - Spring Boot
  - Swagger
  - JWT
  - JPA
  - MySQL


## Instalación y configuración

1. Clona este repositorio en tu máquina local.
2. Asegúrate de tener instalado Node.js y Angular CLI para el frontend, además de Java JDK 1.8 y Maven para el backend.
3. Configura las variables de entorno necesarias, como las credenciales de la base de datos y las claves JWT.
4. Ejecuta el comando `npm install` en la carpeta `front/ComandApp/` para instalar las dependencias del frontend.
5. Ejecuta el comando `mvn install -DskipTests` en la carpeta `back/ComandApp/` para compilar y construir el backend.
6. Levantar contenedor Base de datos 'docker-compose up -d' en la carpeta 'back/ComandApp'.
7. Inicia el servidor de desarrollo del frontend ejecutando `ng serve` en la carpeta `front/ComandApp/`.
8. Inicia el backend ejecutando `java -jar nombre-del-archivo.jar` en la carpeta `back/ComandApp/target`.

## Uso

1. Abre tu navegador web e ingresa a `http://localhost:4200` para acceder al frontend de la aplicación.
2. Utiliza las funcionalidades proporcionadas, como autenticación de usuarios, manipulación de datos de productos y mesas, etc.
3. Puedes ver el concepto visual visitando el siguiente figma:
   https://www.figma.com/proto/3dpjxeoYvT5jebybDhlKGj/Proyecto-Tema-1---Desktop?node-id=39-25&starting-point-node-id=39%3A25


¡Disfruta de ComandApp!
