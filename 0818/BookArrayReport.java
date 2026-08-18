public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "資料結構", 450, 5),
            new Book("B002", "Java程式設計", 520, 3),
            new Book("B003", "演算法", 600, 2),
            new Book("B004", "物件導向程式設計", 480, 6)
        };

        System.out.println("所有書籍：");
        for (Book book : books) {
            System.out.println(book);
        }

        double totalValue = 0;
        for (Book book : books) {
            totalValue += book.getPrice() * book.getStock();
        }

        System.out.println("庫存總價值：" + totalValue);

        Book maxPriceBook = books[0];

        for (int i = 1; i < books.length; i++) {
            if (books[i].getPrice() > maxPriceBook.getPrice()) {
                maxPriceBook = books[i];
            }
        }

        System.out.println("價格最高的書：");
        System.out.println(maxPriceBook);

        System.out.println("庫存小於或等於3的書：");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}

class Book {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Book(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "書號：" + id
                + "，書名：" + name
                + "，價格：" + price
                + "，庫存：" + stock;
    }
}