# Address Book

This is a Spring Boot project that provides a REST API for managing contacts.

## Getting Started

To get started with this project, you will need to have Java and Maven and postgresql installed on your system.

### Installing Java

You can download Java from the official website: [Java Download](https://www.java.com/en/download/)

### Installing Maven

You can download Maven from the official website: [Maven Download](https://maven.apache.org/download.cgi)

### Installing PostgreSQL
You can download PostgreSQL from the official website: [PostgreSQL Download](https://www.postgresql.org/download/)


## Building the Project

To build the project, run the following command in the project directory:

```shell
mvn clean install
```
This will compile the code, run the tests, and create a JAR file in the target directory.

## Running the Project
To run the project, run the following command in the project directory:

```shell
java -jar target/contact-api.jar
```
This will start the server on port 8080.

## Docker Setup

To use this project with Docker, follow the steps below:

### Building the Docker Image

1. Ensure you have Docker installed on your system.
2. In the project root directory, locate the `Dockerfile` file.
3. Build the Docker image by running the following command in the terminal:

   ```shell
   docker build -t address-book:0.0.1 -f Dockerfile .
   ```
   This command will build the Docker image with the tag address-book:0.0.1.

### Running the Docker Containers
1. In the project root directory, locate the `docker-compose.yml` file.

2. Run the following command in the terminal to start the Docker containers:

    ```shell
    docker-compose up
    ```
    This will start the address-book and db containers defined in the docker-compose.yml file.

### Accessing the Application
Once the Docker containers are up and running, you can access the application by opening your web browser and navigating to http://localhost:8080.

Please make sure you have the necessary dependencies installed (Java, Maven, Docker) before following these steps.

Feel free to adjust the Dockerfile and docker-compose.yml files according to your project's requirements.

## API Documentation

The API provides the following endpoints:

- **GET /contacts**: Returns a list of all contacts.
- **GET /contacts/{id}**: Returns the contact with the specified ID.
- **POST /contacts**: Creates a new contact.
Example request body:
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com",
  "phoneNumber": "+1 555-555-5555"
}
```
- **PUT /contacts/{id}**: Updates the contact with the specified ID.
Example request body:
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "janedoe@example.com",
  "phoneNumber": "+1 555-555-5555"
}
```
- **DELETE /contacts/{id}**: Deletes the contact with the specified ID.



