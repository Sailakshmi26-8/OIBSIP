 Online Train Reservation System

**Internship:** OASIS INFOBYTE  
**Track:** Java Development  
**Task:** Task 1  
**Technologies:** Java, JavaFX, JDBC, MySQL

 Project Overview

The Online Train Reservation System is a desktop application built using JavaFX, JDBC, and MySQL. It allows users to log in, make train reservations, view booking confirmations, and cancel reservations using a PNR.

 Features

- User login
- Dashboard navigation
- Train reservation form
- Passenger and journey details
- Train name and PNR generation
- MySQL database integration
- Ticket cancellation using PNR
- Input validation and confirmation messages

 Prerequisites

Install the following before running the application:

- Java Development Kit (JDK)
- Apache Maven, if the project uses Maven
- MySQL Server
- A Java IDE such as Visual Studio Code or IntelliJ IDEA

 Database Setup

 1. Create the database

Open MySQL Workbench or the MySQL command-line client and execute:

sql Quries

CREATE DATABASE Railways;

 2. Create a database user

Create a dedicated MySQL user for the application. Choose your own password and replace the example password below.

sql 
CREATE USER 'railway_app'@'localhost'
IDENTIFIED BY 'CHANGE_THIS_PASSWORD';

GRANT ALL PRIVILEGES ON Railways.*
TO 'railway_app'@'localhost';

FLUSH PRIVILEGES;


Security:*Replace `CHANGE_THIS_PASSWORD` with a strong password of your choice. Never commit your real password to GitHub.

3. Create the required table

Use the table definition required by the project's `DatabaseConnection.java` and booking code. The column names and data types must match the application's SQL queries.

If your project already creates the table automatically, follow that implementation instead of creating a duplicate table.

Configure Database Credentials

The application reads database credentials from environment variables.

Open PowerShell in the project folder and run:

powershell command

$env:RAILWAY_DB_USER = "railway_app"
$env:RAILWAY_DB_PASSWORD = Read-Host "Enter your MySQL password"


Enter the password you configured for your MySQL user.

These variables apply only to the current PowerShell session. If you open a new terminal, configure them again before running the application.

Important: Set the variables in the same terminal or environment used to launch the application.

Run the Application

1. Open the project in your Java IDE.
2. Ensure the MySQL server is running.
3. Confirm the database and required tables exist.
4. Configure the database environment variables.
5. Run the application's main Java class or use the project's Maven run configuration.

If the project uses Maven, compile it with:

powershell command
mvn clean compile


Use the run configuration specified by the project's `pom.xml` and main application class to launch the JavaFX interface.

 Troubleshooting

 Database credentials are not configured

Check that both `RAILWAY_DB_USER` and `RAILWAY_DB_PASSWORD` are set in the terminal running the application.

Access denied by MySQL

Verify the username, password, host, and permissions for the MySQL account.

 Database or table does not exist

Confirm that the `Railways` database and all required tables have been created with the expected schema.

Connection refused

Make sure MySQL Server is running and that the configured host and port match your MySQL installation.

 Security Notes

- Never upload real database passwords to GitHub.
- Each user should configure their own MySQL credentials.
- Keep local configuration files containing secrets out of version control.
- Do not share production database credentials with other users.


 Author

Sai Lakshmi Priya Perakam

 Repository

[OIBSIP GitHub Repository](https://github.com/Sailakshmi26-8/OIBSIP)
