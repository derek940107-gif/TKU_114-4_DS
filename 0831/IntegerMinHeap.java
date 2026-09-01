```java
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegerMinHeap {
    private final List<Integer> heap = new ArrayList<>();

    public void add(int value) {
        heap.add(value);

        int index = heap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent) <= heap.get(index)) {
                break;
            }

            int temp = heap.get(parent);
            heap.set(parent, heap.get(index));
            heap.set(index, temp);

            index = parent;
        }
    }

    public int peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }

        return heap.get(0);
    }

    public int removeMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }

        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);

            int index = 0;

            while (true) {
                int left = index * 2 + 1;
                int right = index * 2 + 2;
                int smallest = index;

                if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                    smallest = left;
                }

                if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                    smallest = right;
                }

                if (smallest == index) {
                    break;
                }

                int temp = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, temp);

                index = smallest;
            }
        }

        return min;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        IntegerMinHeap minHeap = new IntegerMinHeap();

        int[] data = {50, 20, 40, 10, 30, 10, 60};

        for (int value : data) {
            minHeap.add(value);
        }

        System.out.println("size = " + minHeap.size());
        System.out.println("peek = " + minHeap.peek());

        int previous = Integer.MIN_VALUE;

        while (!minHeap.isEmpty()) {
            int current = minHeap.removeMin();
            System.out.print(current + " ");

            if (current < previous) {
                throw new AssertionError("移除結果不是非遞減順序");
            }

            previous = current;
        }

        System.out.println();
        System.out.println("size = " + minHeap.size());
        System.out.println("isEmpty = " + minHeap.isEmpty());

        try {
            minHeap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("peek() 空堆測試通過");
        }

        try {
            minHeap.removeMin();
        } catch (NoSuchElementException e) {
            System.out.println("removeMin() 空堆測試通過");
        }
    }
}
```
