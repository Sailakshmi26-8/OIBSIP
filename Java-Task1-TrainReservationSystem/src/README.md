 Online Train Reservation System


Project Overview

The Online Train Reservation System is a JavaFX desktop application that allows users to log in, book train tickets, view booking details, and cancel reservations. It uses MySQL to store and manage booking information.

 Technologies Used
- Java
- JavaFX
- FXML
- CSS
- JDBC
- MySQL
- Maven
- Visual Studio Code

Features
- User login and credential validation
- Train name automatically populated from the train number
- Passenger and journey details entry
- Train number and required-field validation
- Travel class selection and journey date selection
- Unique PNR generation
- Booking details stored in MySQL
- Fetch booking details using PNR
- Confirmation before ticket cancellation
- Cancelled bookings removed from the database
- Success and error alerts

 Database
The application uses a MySQL database named `Railways` and a `bookings` table to store reservation details.

Database credentials are read from environment variables:
- `RAILWAY_DB_USER`
- `RAILWAY_DB_PASSWORD`

 How to Run
1. Install JDK, Maven, and MySQL.
2. Create the `Railways` database and the required `bookings` table.
3. Configure the two database environment variables on your computer.
4. Ensure the MySQL server is running.
5. Open the project folder in a terminal.
6. Run:

   `mvn clean javafx:run`

Author
Sai Lakshmi Priya Perakam