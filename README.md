# Heal Naturalis App

## Description

This project is a full-stack web application developed using Spring Boot for the backend and React for the frontend. The backend connects to a PostgreSQL database, and the connection information is stored as environment variables.

### Objectives

- **Community Platform**: Create a digital community space where like-minded individuals can engage, discuss, and share insights about holistic wellness and natural health practices.
- **Informational Resource**: Deliver valuable, up-to-date, and informative content through articles on various holistic health topics, aiming to educate users and promote awareness about natural wellness.
- **Health Services**: Provide users with access to a diverse range of services like Ayurvedic medicine, acupuncture, touch therapy, dark field microscopy, and more, allowing users to explore and choose what resonates best with their health journey.
- **Product Access**: Give users the ability to discover and purchase a variety of natural health and wellness products, including books, plant extracts, healing crystals, and eco-friendly beauty and personal care items.

### Current Status

- **Therapies Section Implementation**: Developed the therapies section's backend, including the model, repository, and service for therapy objects. Implemented a method to populate the database with therapy objects from a JSON file, leveraging Data Rest's underlying functionalities for object manipulation (without the need for dedicated controllers).
- **Frontend Development for Therapies Section**: Created all necessary frontend components and methods to handle calls for therapy objects. Implemented functionality to display therapy options in a dropdown menu, with full details accessible upon selecting an option.
- **User Interface and Experience Enhancements**: Introduced an Error component and a Loading component to manage errors and delays in data fetching efficiently. Ensured the website's behavior is fully responsive, providing an optimal user experience across different devices.

### Next Steps

- **Products Section Development**: Initial groundwork laid out for the Products section, including the creation of models and a method for populating the database with product entries. Upcoming tasks include developing the necessary frontend components for fetching and displaying Products/Categories from the database, enhancing the site's content and user interaction capabilities.

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

After ensuring the correctness of the environment variables, you can start the backend server with the command: `mvn spring-boot:run`, and the frontend with the command: `npm start` in the frontend directory.

