import java.util.*;
public class ATM {

    private final Bank bank;
    private final Scanner sc;

    // Currency symbol
    private static final String RUPEE = "₹";

    public ATM(Bank bank) {
        this.bank = bank;
        this.sc = new Scanner(System.in);
    }
    public void start() {
        printWelcome();

        Account loggedIn = login();
        if (loggedIn == null) {
            // Locked or user failed login attempts
            sc.close();
            return;
        }

        runMainMenu(loggedIn);
        sc.close();
    }

    private void printWelcome() {
        System.out.println("JAVA ATM INTERFACE PROJECT");
        System.out.println("==========================");
        System.out.println("WELCOME TO JAVA ATM SYSTEM");
        System.out.println("=========================================\n");
    }
    
    private Account login() { //Login system with maximum 3 attempts.
        int attemptsLeft = 3;

        while (attemptsLeft > 0) {
            int userId = readIntLine("Enter User ID : ");

            int pin = readIntLine("Enter PIN     : ");
            if (!isValidPinFormat(pin)) {
                System.out.println("PIN must be exactly 4 digits.\n");
                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts Left : " + attemptsLeft + "\n");
                }
                continue;
            }

            Account account = bank.authenticate(userId, pin);
            if (account != null) {
                System.out.println("\nLogin Successful!");
                System.out.println();
                System.out.println("Logged in as: " + account.getAccountHolderName());
                System.out.println("=========================================\n");
                return account;
            } else {
                attemptsLeft--;
                System.out.println("\nInvalid User ID or PIN.");
                if (attemptsLeft > 0) {
                    System.out.println("Attempts Left : " + attemptsLeft + "\n");
                }
            }
        }

        System.out.println("\nAccess Denied.");
        System.out.println("ATM Locked.");
        System.out.println("Thank You.");
        return null;
    }

    private boolean isValidPinFormat(int pin) {   // Exactly 4 digits
        return String.valueOf(pin).length() == 4;
    }
    private void runMainMenu(Account currentAccount) {

        while (true) {
            printMenu();

            // Demonstrates InputMismatchException handling 
            int choice = readMenuOption();

            try {
                switch (choice) {
                    case 1:
                        handleCheckBalance(currentAccount);
                        break;

                    case 2:
                        handleDeposit(currentAccount);
                        break;

                    case 3:
                        handleWithdraw(currentAccount);
                        break;

                    case 4:
                        handleTransfer(currentAccount);
                        break;

                    case 5:
                        handleTransactionHistory(currentAccount);
                        break;

                    case 6:
                        handleAccountDetails(currentAccount);
                        break;

                    case 7:
                        System.out.println("\nThank You For Using Java ATM.");
                        System.out.println("Visit Again.");
                        return;

                    default:
                        System.out.println("\nInvalid menu option. Please choose 1 to 7.\n");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("\n" + ex.getMessage() + "\n"); //friendly message for user
            } catch (Exception ex) {
                System.out.println("\nSomething went wrong. Please try again.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("=========================================");
        System.out.println("ATM MAIN MENU");
        System.out.println("=============");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Transaction History");
        System.out.println("6. Account Details");
        System.out.println("7. Logout / Quit");
        System.out.println("=========================================");
    }

    private void handleCheckBalance(Account account) {
        System.out.printf("%nCurrent Balance : %s%.2f%n%n", RUPEE, account.getBalance());
    }

    private void handleDeposit(Account account) {
        double amount = readAmount("Enter amount to deposit: ");
        bank.deposit(account, amount);

        System.out.println("\nDeposit Successful.");
        System.out.printf("Amount Deposited : %s%.2f%n", RUPEE, amount);
        System.out.printf("Updated Balance  : %s%.2f%n%n", RUPEE, account.getBalance());
    }

    private void handleWithdraw(Account account) {
        double amount = readAmount("Enter amount to withdraw: ");
        bank.withdraw(account, amount);

        System.out.println("\nWithdrawal Successful.");
        System.out.printf("Amount Withdrawn  : %s%.2f%n", RUPEE, amount);
        System.out.printf("Remaining Balance : %s%.2f%n%n", RUPEE, account.getBalance());
    }

    private void handleTransfer(Account sender) {
        int recipientUserId = readIntLine("Enter Recipient User ID: ");
        double amount = readAmount("Enter Amount: ");

        Account receiver = bank.transfer(sender, recipientUserId, amount);

        System.out.println("\nTransfer Successful.");
        System.out.printf("Transferred %s%.2f to %s.%n%n",
                "₹", amount, receiver.getAccountHolderName());
    }

    private void handleTransactionHistory(Account account) {
        List<Transaction> history = account.getTransactionHistory();

        System.out.println("\n=========== TRANSACTION HISTORY ===========\n");

        if (history.isEmpty()) {
            System.out.println("No Transactions Yet.\n");
            System.out.println();
            return;
        }

        int index = 1;
        for (Transaction t : history) {
            System.out.println(index + ".\n");
            System.out.print(t); // Transaction.toString() already formats nicely
            System.out.println();
            index++;
        }

        System.out.println("==========================================\n");
    }

    private void handleAccountDetails(Account account) {
        System.out.println("\n=========== ACCOUNT DETAILS ===========");
        System.out.println("Name          : " + account.getAccountHolderName());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("User ID       : " + account.getUserId());
        System.out.printf ("Balance       : %s%.2f%n", RUPEE, account.getBalance());
        System.out.println("PIN           : **** (Hidden)");
        System.out.println("=======================================\n");
    }
    private int readIntLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Input. Please enter numeric values only.\n");
            }
        }
    }
    private int readMenuOption() {
        while (true) {
            System.out.print("Choose an option: ");
            try {
                int option = sc.nextInt();
                sc.nextLine(); // consume leftover newline
                return option;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid Input. Please enter numeric values only.\n");
                sc.nextLine(); 
            }
        }
    }
    private double readAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= 0) {
                    System.out.println("Amount must be greater than zero.\n");
                    continue;
                }
                return amount;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid Input. Please enter numeric values only.\n");
                sc.nextLine(); 
            }
        }
    }
}