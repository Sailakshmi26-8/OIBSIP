ATM Interface – Console Banking System in Java

OIBSIP Java Development Internship

Task: Task 3 – ATM Interface
Language: Java
Type: Console-Based Banking System

Project Description

This project is a console-based ATM banking system developed in Java as part of the OIBSIP Java Development Internship.

The application allows users to securely log in using a User ID and PIN and perform common ATM operations such as checking balance, depositing money, withdrawing money, transferring money, viewing transaction history, and viewing account details.

Features

* User ID and PIN authentication
* Maximum 3 login attempts
* Account balance checking
* Deposit money
* Withdraw money
* Transfer money between accounts
* Transaction history
* Account details
* Balance validation
* Input validation
* Exception handling
* Multiple user accounts
* Logout / Quit option
* Transaction ID generation
* Date and time for transactions

 Technologies Used

* Java
* Object-Oriented Programming
* ArrayList
* Java Collections Framework
* Exception Handling
* Java Date and Time API
* Scanner for user input

Project Structure

text
Java-Task3-ATMInterface
│
├── Main.java
├── ATM.java
├── Bank.java
├── Account.java
├── Transaction.java
└── README.md


 How to Run

Open a terminal in the project folder.

 Compile
javac *.java


Run
java Main


Demo Accounts

| User ID |  PIN | Name        | Initial Balance |
| ------- | ---- | ----------- | --------------: |
| 1001    | 1234 | Demo User 1 |         ₹15,000 |
| 1002    | 2345 | Demo User 2 |         ₹10,000 |
| 1003    | 3456 | Demo User 3 |         ₹20,000 |
| 1004    | 4567 | Demo User 4 |          ₹5,000 |
| 1005    | 5678 | Demo User 5 |          ₹2,000 |
| 1006    | 6789 | Demo User 6 |         ₹50,000 |
| 1007    | 7890 | Demo User 7 |       ₹2,34,000 |

OOP Concepts Used

Encapsulation: Account information and transaction data are protected using private fields and public methods.
Abstraction: ATM and Bank classes handle banking operations separately from the user interface.
Composition: Bank manages multiple Account objects, and each Account maintains its transaction history.
Classes and Objects: The project is divided into separate classes based on their responsibilities.

Validation and Exception Handling

The application validates:

* Incorrect User ID or PIN
* Maximum login attempts
* Invalid menu choices
* Invalid numeric input
* Negative or zero transaction amounts
* Insufficient account balance
* Invalid recipient accounts
* Self-transfer attempts

Author

Sai Lakshmi Priya Perakam

Developed as part of the OASIS INFOBYTE Student Internship Program – Java Development Track.
