# Gestión de Citas Médicas

Este es un proyecto de gestión de citas médicas, donde se pueden gestionar citas para pacientes con doctores y asignarlos a consultorios dentro de un hospital. Está construido usando **Spring Boot** como framework principal para el backend, con una interfaz web que utiliza **Thymeleaf** para la renderización del lado del servidor.

## Rutas disponibles

Una vez que el proyecto esté corriendo, puedes acceder a las siguientes rutas:

- **[Gestión de Citas Médicas](http://localhost:8080/citas/gestion)**: Página para agendar y consultar citas médicas.
- **[Consultorios](http://localhost:8080/consultorios)**: Página para gestionar los consultorios disponibles en el sistema.
- **[Doctores](http://localhost:8080/doctores)**: Página para gestionar los doctores disponibles en el sistema.

## Tecnologías utilizadas

Este proyecto utiliza las siguientes tecnologías:

- **Spring Boot**: Framework utilizado para el desarrollo del backend.
- **Spring Data JPA**: Para la gestión de la persistencia de datos a través de la base de datos.
- **Spring Web**: Para la creación de aplicaciones web basadas en Spring, incluyendo el manejo de rutas HTTP.
- **Thymeleaf**: Motor de plantillas para generar la interfaz web dinámica en el lado del servidor.
- **Lombok**: Librería para reducir el código boilerplate en las clases Java.
- **H2 Database**: Base de datos en memoria utilizada para almacenamiento temporal durante el desarrollo (puedes configurarlo para usar otras bases de datos como MySQL, PostgreSQL, etc.).

## Dependencias

Las siguientes dependencias de Maven se han utilizado en el proyecto:

    <!-- Spring Boot Data JPA para manejo de la base de datos -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Boot Web para crear una aplicación web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- H2 Database para almacenamiento en memoria -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok para reducir el código boilerplate en las clases -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>

    <!-- Dependencia de pruebas para testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Thymeleaf para renderización de vistas HTML -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

Para ejecutar el proyecto localmente, sigue estos pasos:

Clona este repositorio:

git clone https://github.com/tu-usuario/consultorio-app.git

Navega al directorio del proyecto:
cd consultorio-app

Compila y ejecuta la aplicación usando Maven:
./mvnw spring-boot:run

o si estás utilizando Windows:
mvnw spring-boot:run

Abre tu navegador y accede a http://localhost:8080/citas/gestion para comenzar a gestionar las citas médicas.
