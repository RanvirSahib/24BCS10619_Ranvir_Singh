class BankAccount{
    private String account_no;
    private double balance;
    BankAccount(String account_num, double balance){
        this.account_no = account_num;
        this.balance = balance;
    }

    void getBalance(){
        System.out.println("Current balance: " + balance);
    }

    void setBalance(double newBalance){
        this.balance = newBalance;
    }

    void deposit(double amount) {
        this.balance += amount;
        System.out.println("Deposit successful: " + amount);
    }

    void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawn successful: " + amount);
        }
        else {
            System.out.println("You are poor");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("123456", 50000);
        acc1.getBalance();
        acc1.setBalance(2000);
        acc1.deposit(3000);
        acc1.getBalance();
        acc1.withdraw(300000);
        acc1.getBalance();
    }
}
