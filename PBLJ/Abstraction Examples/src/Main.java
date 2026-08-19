abstract class Bank {
    private static int balance = 1000;

    public void getBalance() {
        System.out.println("Balance: " + balance);
    }

    public void setBalance(int balance) {
        Bank.balance = balance;
    }

    protected static int getCurrentBalance() {
        return balance;
    }

    protected static void updateBalance(int balance) {
        Bank.balance = balance;
    }
}

class Branch extends Bank {
    public static void debit(int bal) {
        int currentVal = getCurrentBalance();
        currentVal -= bal;
        updateBalance(currentVal);
    }

    public void credit(int bal) {
        int currentVal = getCurrentBalance();
        currentVal += bal;
        updateBalance(currentVal);
    }
}

public class Main {
    public static void main(String[] args) {
        Branch branch = new Branch();

        branch.getBalance();
        Branch.debit(200);
        branch.getBalance();
        branch.credit(500);
        branch.getBalance();
    }
}