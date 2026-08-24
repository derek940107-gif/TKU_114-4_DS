import java.util.ArrayList;
import java.util.List;

public class GenericRepositorySystem {

    public static class Repository<T> {
        private List<T> items;

        public Repository() {
            this.items = new ArrayList<>();
        }

        public void add(T item) {
            if (item != null) {
                items.add(item);
            }
        }

        public T get(int index) {
            if (index < 0 || index >= items.size()) {
                return null;
            }
            return items.get(index);
        }

        public boolean remove(T item) {
            return items.remove(item);
        }

        public int size() {
            return items.size();
        }

        public void printAll() {
            System.out.println("Repository Contents (Size: " + size() + "):");
            for (int i = 0; i < items.size(); i++) {
                System.out.println("  [" + i + "] " + items.get(i));
            }
        }
    }

    public static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Product product = (Product) obj;
            return id == product.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public String toString() {
            return String.format("Product{id=%d, name='%s', price=%.1f}", id, name, price);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Repository<String> ===");
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Java Programming");
        stringRepo.add("Data Structures");
        stringRepo.add("Algorithms");
        stringRepo.printAll();

        System.out.println("\nGet element at index 1: " + stringRepo.get(1));
        System.out.println("Removing 'Data Structures': " + stringRepo.remove("Data Structures"));
        stringRepo.printAll();

        System.out.println("\n=== Testing Repository<Product> ===");
        Repository<Product> productRepo = new Repository<>();
        Product p1 = new Product(101, "Laptop", 1200.0);
        Product p2 = new Product(102, "Smartphone", 800.0);
        Product p3 = new Product(103, "Tablet", 500.0);

        productRepo.add(p1);
        productRepo.add(p2);
        productRepo.add(p3);
        productRepo.printAll();

        System.out.println("\nGet product at index 0: " + productRepo.get(0));
        System.out.println("Removing product (ID: 102): " + productRepo.remove(p2));
        productRepo.printAll();
    }
}