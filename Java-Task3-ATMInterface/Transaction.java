import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaction {

    private static int counter = 1000; 
    private static final String RUPEE = "\u20B9";

    // Private fields
    private final String transactionID;
    private final String transactionType;
    private final String senderAccount;
    private final String receiverAccount;
    private final double amount;
    private final double remainingBalance;
    private final LocalDateTime timestamp;

    public Transaction(String transactionType,
                       String senderAccount,
                       String receiverAccount,
                       double amount,
                       double remainingBalance) {

        this.transactionID = generateTransactionId();
        this.transactionType = transactionType;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.remainingBalance = remainingBalance;
        this.timestamp = LocalDateTime.now();
    }

    private static synchronized String generateTransactionId() {
        counter++;
        return "TXN" + counter;
    }

    // Getters
    public String getTransactionID() {
        return transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public double getAmount() {
        return amount;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.ENGLISH);

        StringBuilder sb = new StringBuilder();
        sb.append("Transaction ID : ").append(transactionID).append("\n");
        sb.append("Type           : ").append(transactionType).append("\n");

        if (senderAccount != null && !senderAccount.isBlank()) {
            sb.append("From           : ").append(senderAccount).append("\n");
        }
        if (receiverAccount != null && !receiverAccount.isBlank()) {
            sb.append("To             : ").append(receiverAccount).append("\n");
        }

        sb.append(String.format("Amount         : %s%.2f%n", RUPEE, amount));
        sb.append("Time           : ").append(timestamp.format(fmt)).append("\n");
        sb.append(String.format("Balance        : %s%.2f%n", RUPEE, remainingBalance));
        sb.append("-----------------------\n");

        return sb.toString();
    }
}