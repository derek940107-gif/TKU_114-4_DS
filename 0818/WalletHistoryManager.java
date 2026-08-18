public class WalletHistoryManager {
    public static void main(String[] args) {
        DigitalWallet wallet1 = new DigitalWallet("W001", "王小明", 1000, 10);
        DigitalWallet wallet2 = new DigitalWallet("W002", "李小華", 500, 10);

        WalletTransactionSystem.deposit(wallet1, 300);
        WalletTransactionSystem.pay(wallet1, 200);

        System.out.println("轉帳結果："
                + WalletTransactionSystem.transferTo(wallet1, wallet2, 400));

        System.out.println();
        System.out.println(wallet1.statement());
        System.out.println();
        System.out.println(wallet2.statement());

        System.out.println();
        System.out.println("W001 第1筆交易："
                + wallet1.findTransaction(1));

        System.out.println("W001 儲值總額："
                + wallet1.totalByType("DEPOSIT"));

        System.out.println("W001 付款總額："
                + wallet1.totalByType("PAY"));

        System.out.println("W001 轉出總額："
                + wallet1.totalByType("TRANSFER_OUT"));
    }
}

class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private WalletTransaction[] transactions;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner,
                          double balance, int transactionSize) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = balance >= 0 ? balance : 0;
        this.transactions = new WalletTransaction[transactionSize];
        this.transactionCount = 0;
    }

    public double getBalance() {
        return balance;
    }

    public boolean changeBalance(double amount) {
        if (balance + amount < 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    public boolean addTransaction(String type, double amount) {
        if (transactionCount >= transactions.length) {
            return false;
        }

        transactions[transactionCount] =
                new WalletTransaction(
                        transactionCount + 1,
                        type,
                        amount
                );

        transactionCount++;
        return true;
    }

    public WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }

        return null;
    }

    public double totalByType(String type) {
        double total = 0;

        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equals(type)) {
                total += transactions[i].getAmount();
            }
        }

        return total;
    }

    public String statement() {
        String result = "錢包ID：" + walletId
                + "\n所有者：" + owner
                + "\n餘額：" + balance
                + "\n交易紀錄：\n";

        for (int i = 0; i < transactionCount; i++) {
            result += transactions[i] + "\n";
        }

        return result;
    }
}

class WalletTransactionSystem {
    public static boolean deposit(DigitalWallet wallet, double amount) {
        if (wallet == null || amount <= 0) {
            return false;
        }

        if (wallet.addTransaction("DEPOSIT", amount)) {
            wallet.changeBalance(amount);
            return true;
        }

        return false;
    }

    public static boolean pay(DigitalWallet wallet, double amount) {
        if (wallet == null || amount <= 0) {
            return false;
        }

        if (wallet.getBalance() < amount) {
            return false;
        }

        if (wallet.addTransaction("PAY", amount)) {
            wallet.changeBalance(-amount);
            return true;
        }

        return false;
    }

    public static boolean transferTo(
            DigitalWallet source,
            DigitalWallet target,
            double amount) {

        if (source == null || target == null) {
            return false;
        }

        if (source == target) {
            return false;
        }

        if (amount <= 0 || source.getBalance() < amount) {
            return false;
        }

        if (!source.canAddTransaction()
                || !target.canAddTransaction()) {
            return false;
        }

        source.addTransaction("TRANSFER_OUT", amount);
        target.addTransaction("TRANSFER_IN", amount);

        source.changeBalance(-amount);
        target.changeBalance(amount);

        return true;
    }
}

class WalletTransaction {
    private int sequence;
    private String type;
    private double amount;

    public WalletTransaction(int sequence, String type, double amount) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
    }

    public int getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "序號：" + sequence
                + "，類型：" + type
                + "，金額：" + amount;
    }
}