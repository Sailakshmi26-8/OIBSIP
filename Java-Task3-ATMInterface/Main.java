public class Main {

    public static void main(String[] args) {

        // Create bank
        Bank bank = new Bank("JAVA BANK");

        // Add sample accounts 
        bank.addAccount(new Account(1001, 1234, "Demo User 1", "SBIN0001001", 15000.00));
        bank.addAccount(new Account(1002, 2345, "Demo User 2", "SBIN0001002", 10000.00));
        bank.addAccount(new Account(1003, 3456, "Demo User 3", "SBIN0001003", 20000.00));
        bank.addAccount(new Account(1004, 4567, "Demo User 4", "SBIN0001004", 5000.00));
        bank.addAccount(new Account(1005, 5678, "Demo User 5", "SBIN0001005", 2000.00));
        bank.addAccount(new Account(1006, 6789, "Demo User 6", "SBIN0001006", 50000.00));
        bank.addAccount(new Account(1007, 7890, "Demo User 7", "SBIN0001007", 234000.00));
        // Start ATM
        ATM atm = new ATM(bank);
        atm.start();
    }
}