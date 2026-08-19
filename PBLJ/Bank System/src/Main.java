import java.io.Closeable;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class AuditLogger implements Closeable {
    public AuditLogger() {
        System.out.println("[LOG] Opening Audit Logger...");
    }

    public void logTransaction(String details) {
        System.out.println("[LOG] " + details);
    }

    @Override
    public void close() {
        System.out.println("[LOG] Closing Audit Logger resource.");
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }

        balance += amount;
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Available: $" + balance +
                            ", Required: $" + amount
            );
        }

        balance -= amount;
    }

    public void transfer(BankAccount targetAccount, double amount)
            throws InsufficientFundsException {

        try (AuditLogger logger = new AuditLogger()) {
            logger.logTransaction(
                    accountNumber + " is sending $" + amount +
                            " to " + targetAccount.getAccountNumber()
            );

            withdraw(amount);
            targetAccount.deposit(amount);

            logger.logTransaction(
                    "Transfer successful: $" + amount +
                            " transferred from " + accountNumber +
                            " to " + targetAccount.getAccountNumber()
            );
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("ACC-101", 500.0);
        BankAccount acc2 = new BankAccount("ACC-202", 200.0);

        System.out.println("=== TEST 1: Successful Transfer ===");

        try {
            acc1.transfer(acc2, 150.0);

            System.out.println("Acc1 Balance: $" + acc1.getBalance());
            System.out.println("Acc2 Balance: $" + acc2.getBalance());

        } catch (InsufficientFundsException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        System.out.println("\n=== TEST 2: Overdraft (Checked Exception) ===");

        try {
            acc1.transfer(acc2, 1000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught Checked Exception: " + e.getMessage());
        }

        System.out.println("\n=== TEST 3: Negative Amount (Unchecked Exception) ===");

        try {
            acc1.deposit(-50.0);
        } catch (InvalidAmountException e) {
            System.out.println("Caught Unchecked Exception: " + e.getMessage());
        } finally {
            System.out.println("Cleanup / Always-executed code in main.");
        }
    }
}