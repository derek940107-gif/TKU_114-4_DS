```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LowestKPriceTracker {
    public static List<Integer> lowestK(Integer[] prices, int k) {
        List<Integer> result = new ArrayList<>();

        if (k <= 0) {
            return result;
        }

        List<Integer> heap = new ArrayList<>();

        for (Integer price : prices) {
            if (price == null || price < 0) {
                continue;
            }

            if (heap.size() < k) {
                heap.add(price);
                siftUp(heap, heap.size() - 1);
            } else if (price < heap.get(0)) {
                heap.set(0, price);
                siftDown(heap, 0);
            }
        }

        result.addAll(heap);
        Collections.sort(result);

        return result;
    }

    private static void siftUp(List<Integer> heap, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent) >= heap.get(index)) {
                break;
            }

            int temp = heap.get(parent);
            heap.set(parent, heap.get(index));
            heap.set(index, temp);

            index = parent;
        }
    }

    private static void siftDown(List<Integer> heap, int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int largest = index;

            if (left < heap.size() && heap.get(left) > heap.get(largest)) {
                largest = left;
            }

            if (right < heap.size() && heap.get(right) > heap.get(largest)) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            int temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);

            index = largest;
        }
    }

    public static void main(String[] args) {
        Integer[] prices = {120, null, 50, -10, 80, 30, 100, 20, 60};
        int k = 4;

        System.out.println(lowestK(prices, k));
        System.out.println(lowestK(prices, 0));
    }
}
```
