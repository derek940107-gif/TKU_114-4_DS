```java
import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private final List<Integer> heap = new ArrayList<>();

    public void add(int value) {
        heap.add(value);

        int index = heap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(index) <= heap.get(parent)) {
                break;
            }

            int temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);

            index = parent;
        }
    }

    public int peekMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();

        int[] data = {25, 40, 10, 50, 30, 50};

        for (int value : data) {
            maxHeap.add(value);
            System.out.println("加入 " + value + "："
                    + maxHeap.snapshot());
        }

        System.out.println("Max Heap Root = " + maxHeap.peekMax());
    }
}
```
