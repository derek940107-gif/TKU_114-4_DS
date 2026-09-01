```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {

    static class Product {
        String id;
        int sales;

        Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        @Override
        public String toString() {
            return id + "|" + sales;
        }
    }

    public static List<Product> topK(Product[] products, int k) {
        List<Product> result = new ArrayList<>();

        if (products == null || k <= 0) {
            return result;
        }

        Map<String, Integer> merged = new HashMap<>();

        for (Product product : products) {
            if (product == null || product.id == null) {
                continue;
            }

            merged.put(
                product.id,
                merged.getOrDefault(product.id, 0) + product.sales
            );
        }

        PriorityQueue<Product> heap = new PriorityQueue<>(
            (a, b) -> {
                if (a.sales != b.sales) {
                    return Integer.compare(a.sales, b.sales);
                }

                return b.id.compareTo(a.id);
            }
        );

        for (Map.Entry<String, Integer> entry : merged.entrySet()) {
            Product product = new Product(entry.getKey(), entry.getValue());

            if (heap.size() < k) {
                heap.offer(product);
            } else {
                Product lowest = heap.peek();

                if (product.sales > lowest.sales ||
                    (product.sales == lowest.sales &&
                     product.id.compareTo(lowest.id) < 0)) {
                    heap.poll();
                    heap.offer(product);
                }
            }
        }

        result.addAll(heap);

        result.sort(
            (a, b) -> {
                if (a.sales != b.sales) {
                    return Integer.compare(b.sales, a.sales);
                }

                return a.id.compareTo(b.id);
            }
        );

        return result;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("A101", 500),
            new Product("B205", 800),
            new Product("C300", 300),
            new Product("A101", 400),
            new Product("D410", 800),
            new Product("B205", 200),
            new Product("E520", 600),
            new Product("F630", 800)
        };

        List<Product> result = topK(products, 3);

        for (Product product : result) {
            System.out.println(product);
        }
    }
}
```
