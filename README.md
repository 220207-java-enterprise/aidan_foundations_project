# Expense Reimbursement Service: API

## Description
This is a RESTful Expense Reimbursement Service API built with Java. It makes use of Servlets to host its endpoints, stores data in a PostgresSQL database, and is deployed on a Tomcat server. Users of this API can complete various ERS related actions using the endpoints.

## Technologies Used
- Java - 1.8.0_322
- Maven - 10.14.6
- PostgresSQL - 42.3.3
- JavaX Servlets - 4.0.1
- Jackson Relational Mapper - 2.13.1
- JBCrypt - 0.4
- JJWT - 0.9.1
- JUnit - 4.13.2
- Mockito - 4.3.1

## Features
### Deployed
- Users can register as new users.
- Admins can approve new user registration.
- Admins can view a list of all active users.
- Users can securely log in using username and password.
- Passwords are encrypted with BCrypt before being stored in the database.
- User session information is transferred through the use of JWTs.
- Users can post new reimbursements.
- Admins can approve or deny reimbursements.
- Admins can view a list of all reimbursements.

### To-Do
- Create sufficient unit tests of the application service layer.
- Properly handle all errors.
- Log key points in the application to a file.
- Register the application with Prism, and receive payment id's from Prism for approved reimbursements.

## Contributors
Aidan Amato