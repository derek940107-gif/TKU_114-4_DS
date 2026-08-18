public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W001", "王小明", 1000);

        System.out.println(wallet);

        wallet.deposit(500);
        System.out.println("儲值500後：" + wallet);

        System.out.println("付款300：" + wallet.pay(300));
        System.out.println(wallet);

        System.out.println("付款2000：" + wallet.pay(2000));
        System.out.println(wallet);

        System.out.println("付款-100：" + wallet.pay(-100));
        System.out.println(wallet);

        System.out.println("退款200：" + wallet.refund(200));
        System.out.println(wallet);

        System.out.println("交易次數：" + wallet.getTransactionCount());
    }
}

class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, double balance) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = balance >= 0 ? balance : 0;
        this.transactionCount = 0;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;
        return true;
    }

    public boolean pay(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        transactionCount++;
        return true;
    }

    public boolean refund(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;
        return true;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return "錢包ID：" + walletId
                + "，所有者：" + owner
                + "，餘額：" + balance;
    }
}