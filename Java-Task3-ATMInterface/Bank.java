import java.util.*;
public class Bank {

    private final String bankName;
    private final List<Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account authenticate(int userId, int pin) {
        Account account = findAccountByUserId(userId);
        if (account != null && account.verifyPIN(pin)) {
            return account;
        }
        return null;
    }

    //Finds an account by User ID 
    public Account findAccountByUserId(int userId) {
        for (Account acc : accounts) {
            if (acc.getUserId() == userId) {
                return acc;
            }
        }
        return null;
    }

    public void deposit(int userId, double amount) {
        Account acc = findAccountByUserId(userId);
        if (acc == null) {
            throw new IllegalArgumentException("Recipient account not found.");
        }
        deposit(acc, amount);
    }

    public void deposit(Account account, double amount) {
        // Account method does core balance update 
        account.deposit(amount);

        // Log transaction
        Transaction t = new Transaction("Deposit",account.getAccountNumber()
         + " (" + account.getAccountHolderName() + ")","Self",amount, account.getBalance() );
        account.addTransaction(t);
    }

    // Withdraw 
    public void withdraw(int userId, double amount) {
        Account acc = findAccountByUserId(userId);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        withdraw(acc, amount);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);

        Transaction t = new Transaction("Withdraw", "Self",
        account.getAccountNumber() + " (" + account.getAccountHolderName() + ")",amount,account.getBalance());
        account.addTransaction(t);
    }

    // Transfer    
    public Account transfer(Account sender, int recipientUserId, double amount) {
        if (sender == null) {
            throw new IllegalArgumentException("Invalid sender account.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        Account receiver = findAccountByUserId(recipientUserId);
        if (receiver == null) {
            throw new IllegalArgumentException("Recipient account does not exist.");
        }

        if (sender.getUserId() == receiver.getUserId()) {
            throw new IllegalArgumentException("Self-transfer is not allowed.");
        }

        // Withdraw from sender first 
        sender.withdraw(amount);

        // Deposit to receiver
        receiver.deposit(amount);

        // Log transaction for sender: Transfer Sent
        Transaction sent = new Transaction(
                "Transfer Sent",
                sender.getAccountNumber() + " (" + sender.getAccountHolderName() + ")",
                receiver.getAccountNumber() + " (" + receiver.getAccountHolderName() + ")",
                amount,
                sender.getBalance()
        );
        sender.addTransaction(sent);

        // Log transaction for receiver: Transfer Received
        Transaction received = new Transaction(
                "Transfer Received",
                sender.getAccountNumber() + " (" + sender.getAccountHolderName() + ")",
                receiver.getAccountNumber() + " (" + receiver.getAccountHolderName() + ")",
                amount,
                receiver.getBalance()
        );
        receiver.addTransaction(received);

        return receiver;
    }
}