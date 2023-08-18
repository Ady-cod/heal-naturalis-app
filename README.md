# Project Title

Heal Naturalis App

## Description

This project is a full-stack web application developed using Spring Boot for the backend and React for the frontend. The backend connects to a PostgreSQL database, and the connection information is stored as environment variables.

## Installation

1. Clone the repository to your local machine.
2. Navigate to the backend directory.
3. Install Maven dependencies using the command: `mvn install`.
4. Create a `.env` file in the root directory of the backend based on the provided `.env.example` file. You can do 
   this by copying the `.env.example` file and rename the copy to `.env`. Update the values in the `.env` file to match 
   your database configuration.

## Usage

To use the project after setup, ensure that the PostgreSQL database is up and running. The environment variables defined in the `.env` file should match the database connection details:

- `DATABASE_URL`: The URL to your PostgreSQL database, typically in the form `jdbc:postgresql://localhost:5432/yourDatabase`
- `DATABASE_USERNAME`: The username to connect to your PostgreSQL database.
- `DATABASE_PASSWORD`: The password to connect to your PostgreSQL database.

Replace the yourDatabase, yourUsername, and yourPassword placeholders in the DATABASE_URL, DATABASE_USERNAME, and DATABASE_PASSWORD (defined in .env.example) with appropriate values depending upon your database setup.

After ensuring the correctness of the environment variables, you can start the backend server with the command: `mvn spring-boot:run`.

## Contributing

If you wish to contribute to this project, please fork the repository and create a pull request with your changes. All contributions are welcome!
