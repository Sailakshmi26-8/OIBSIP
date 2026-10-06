import java.util.*;
public class Account {

    // Private fields
    private int userId;
    private int pin;
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Each account maintains its own transaction history
    private final ArrayList<Transaction> transactionHistory;

    public Account(int userId, int pin, String accountHolderName, String accountNumber, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    // PIN validation
    public boolean verifyPIN(int inputPin) {
        return this.pin == inputPin;
    }
    // Balance operations
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient Funds.");
        }
        balance -= amount;
    }

    // Transaction history
    public void addTransaction(Transaction t) {
        transactionHistory.add(t);
    }
      //Returns a read-only view to protect internal ArrayList (encapsulation).
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    // Getters / Setters
    public int getUserId() {
        return userId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}