# Contact Registry Application

## Overview
The Contact Registry Application is a Java-based web application built with Spring Boot. It enables users to manage contact details efficiently, offering features such as CRUD operations, graphical statistics, and printable reports.

## Features
1. **CRUD Operations**: 
   - Add, view, update, and delete contact details via the `Manage Contacts` page.
2. **Admin Dashboard**:
   - Visualize user statistics by Gender and County of Residence.
   - View the 5 most recently added contacts in a table.
3. **Manage Contacts Page**:
   - A dedicated page for managing contacts with a form for creating/updating contacts and a table for listing, editing, and deleting them.
4. **Printable Reports**:
   - Generate and filter reports by County of Residence.

## Technologies Used
- **Java**: Core programming language.
- **Spring Boot**: Framework for application development.
- **MariaDB**: Database for storing contact data.
- **Thymeleaf**: Template engine for rendering the UI.
- **Spring Data JPA**: For database interaction.
- **Spring Web**: For building RESTful APIs.

## Prerequisites
- Java 17 or higher
- Maven
- MariaDB database server

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/cyruskkorir/contact-registry.git
   cd contact-registry
   ```

2. **Configure the Database**:
   - Ensure MariaDB is installed and running.
   - Create a database named `contact_registry`.
   - Update the `src/main/resources/application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mariadb://localhost:3306/contact_registry
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     ```

3. **Build and Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**:
   - Open your browser and navigate to `http://localhost:8080`.

## API Endpoints
- **Contacts**:
  - `GET /api/contacts`: Retrieve all contacts.
  - `POST /api/contacts`: Create a new contact.
  - `PUT /api/contacts/{id}`: Update an existing contact.
  - `DELETE /api/contacts/{id}`: Delete a contact.
- **Dashboard**:
  - `GET /api/contacts/dashboard`: View the admin dashboard.
- **Manage Contacts**:
  - `GET /api/contacts/manage`: Access the `Manage Contacts` page.

## Future Enhancements
- Add user authentication and role-based access control.
- Enhance the dashboard with more detailed analytics.
- Implement export functionality for reports (e.g., PDF or Excel).

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## Contact
For any questions or feedback, please contact cyruskorir@outlook.com.
