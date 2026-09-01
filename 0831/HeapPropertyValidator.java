```java
import java.util.Arrays;
import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(List<Integer> heap) {
        if (heap == null) {
            return false;
        }

        for (int i = 0; i < heap.size(); i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < heap.size() && heap.get(i) > heap.get(left)) {
                return false;
            }

            if (right < heap.size() && heap.get(i) > heap.get(right)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isMaxHeap(List<Integer> heap) {
        if (heap == null) {
            return false;
        }

        for (int i = 0; i < heap.size(); i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < heap.size() && heap.get(i) < heap.get(left)) {
                return false;
            }

            if (right < heap.size() && heap.get(i) < heap.get(right)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<Integer> minHeap = Arrays.asList(10, 20, 15, 30, 25);
        List<Integer> maxHeap = Arrays.asList(50, 40, 45, 20, 30);
        List<Integer> invalid = Arrays.asList(10, 5, 15);

        System.out.println(isMinHeap(minHeap));
        System.out.println(isMaxHeap(maxHeap));
        System.out.println(isMinHeap(invalid));
        System.out.println(isMinHeap(null));
        System.out.println(isMaxHeap(Arrays.asList()));
        System.out.println(isMaxHeap(Arrays.asList(10)));
    }
}
```
