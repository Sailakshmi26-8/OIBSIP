Online Train Reservation System

 Project Overview
A JavaFX desktop application for booking and cancelling train reservations. The application uses MySQL to store and retrieve reservation details.

 Technologies Used
- Java
- JavaFX and FXML
- CSS
- JDBC
- MySQL
- Maven

Features
- Login with credential validation
- Train name automatically populated from the train number
- Passenger and journey detail entry
- Required-field and train-number validation
- Travel class and journey date selection
- PNR generation
- Save booking details to MySQL
- Fetch booking details using PNR
- Confirm ticket cancellation
- Delete cancelled reservations from the database
- Success and error messages

 Database
The application uses a MySQL database named `Railways` with a `bookings` table.

Configure these environment variables before running the application:
- `RAILWAY_DB_USER`
- `RAILWAY_DB_PASSWORD`

Never commit database passwords or other secrets to GitHub.

How to Run
1. Install a compatible JDK, Maven, and MySQL.
2. Create the `Railways` database and the required `bookings` table.
3. Configure the database environment variables.
4. Start the MySQL server.
5. Open a terminal in the project directory.
6. Run:

   `mvn clean javafx:run`

Author
Sai Lakshmi Priya Perakam