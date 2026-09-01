```java
import java.util.Arrays;

public class ArrayMinHeap {
    private int[] heap;
    private int size;

    public ArrayMinHeap() {
        heap = new int[4];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity();

        heap[size] = value;
        siftUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap[0];
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        size--;

        if (size > 0) {
            heap[0] = heap[size];
            siftDown(0);
        }

        return min;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] snapshot() {
        return Arrays.copyOf(heap, size);
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[parent] <= heap[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap minHeap = new ArrayMinHeap();

        int[] data = {
            50, 20, 80, 10, 40,
            70, 30, 90, 60, 15,
            25, 35, 45, 55, 65,
            5, 75, 85, 95, 100
        };

        for (int value : data) {
            minHeap.add(value);
        }

        System.out.println("size = " + minHeap.size());
        System.out.println("peek = " + minHeap.peek());
        System.out.println("snapshot = " + Arrays.toString(minHeap.snapshot()));

        System.out.print("removeMin = ");

        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }

        System.out.println();
        System.out.println("size = " + minHeap.size());
        System.out.println("isEmpty = " + minHeap.isEmpty());
    }
}
```
