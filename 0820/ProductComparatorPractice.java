import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparatorPractice {

    public static class StoreProduct implements Comparable<StoreProduct> {
        private int id;
        private String name;
        private double price;
        private int stock;

        public StoreProduct(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }

        @Override
        public int compareTo(StoreProduct other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return String.format("StoreProduct{id=%d, name='%s', price=%.1f, stock=%d}", id, name, price, stock);
        }
    }

    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();
        products.add(new StoreProduct(5, "Keyboard", 99.0, 50));
        products.add(new StoreProduct(2, "Mouse", 49.0, 100));
        products.add(new StoreProduct(1, "Monitor", 299.0, 50));
        products.add(new StoreProduct(4, "Webcam", 49.0, 30));
        products.add(new StoreProduct(3, "Headset", 99.0, 100));

        System.out.println("=== 原始商品清單 ===");
        for (StoreProduct p : products) {
            System.out.println(p);
        }

        List<StoreProduct> naturalSorted = new ArrayList<>(products);
        Collections.sort(naturalSorted);
        System.out.println("\n=== 依自然排序 (ID 升冪) ===");
        for (StoreProduct p : naturalSorted) {
            System.out.println(p);
        }

        Comparator<StoreProduct> priceThenNameComparator = new Comparator<StoreProduct>() {
            @Override
            public int compare(StoreProduct p1, StoreProduct p2) {
                int priceCompare = Double.compare(p1.getPrice(), p2.getPrice());
                if (priceCompare != 0) {
                    return priceCompare;
                }
                return p1.getName().compareTo(p2.getName());
            }
        };

        List<StoreProduct> priceSorted = new ArrayList<>(products);
        priceSorted.sort(priceThenNameComparator);
        System.out.println("\n=== 比較器一：價格升冪 (同價依名稱升冪) ===");
        for (StoreProduct p : priceSorted) {
            System.out.println(p);
        }

        Comparator<StoreProduct> stockThenIdComparator = new Comparator<StoreProduct>() {
            @Override
            public int compare(StoreProduct p1, StoreProduct p2) {
                int stockCompare = Integer.compare(p2.getStock(), p1.getStock());
                if (stockCompare != 0) {
                    return stockCompare;
                }
                return Integer.compare(p1.getId(), p2.getId());
            }
        };

        List<StoreProduct> stockSorted = new ArrayList<>(products);
        stockSorted.sort(stockThenIdComparator);
        System.out.println("\n=== 比較器二：庫存降冪 (同庫存依 ID 升冪) ===");
        for (StoreProduct p : stockSorted) {
            System.out.println(p);
        }

        System.out.println("\n=== 確認原始清單未受影響 ===");
        for (StoreProduct p : products) {
            System.out.println(p);
        }
    }
}