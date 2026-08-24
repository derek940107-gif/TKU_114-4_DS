import java.util.Arrays;

public class CircularQueuePractice {

    public static class CircularQueue<T> {
        private Object[] elements;
        private int front;
        private int rear;
        private int size;
        private int capacity;

        public CircularQueue(int capacity) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
            this.front = 0;
            this.rear = 0;
            this.size = 0;
        }

        public boolean enqueue(T item) {
            if (isFull()) {
                System.out.println("Enqueue failed: Queue is full (" + item + ")");
                return false;
            }
            elements[rear] = item;
            rear = (rear + 1) % capacity;
            size++;
            return true;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (isEmpty()) {
                System.out.println("Dequeue failed: Queue is empty");
                return null;
            }
            T item = (T) elements[front];
            elements[front] = null;
            front = (front + 1) % capacity;
            size--;
            return item;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public int size() {
            return size;
        }

        public int getFront() {
            return front;
        }

        public int getRear() {
            return rear;
        }

        public void printInternalState() {
            System.out.println("內部陣列 (Internal Array): " + Arrays.toString(elements));
            System.out.println("front 指針位置: " + front);
            System.out.println("rear 指針位置: " + rear);
            System.out.println("目前元素數量 (size): " + size);
        }
    }

    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("=== 執行佇列操作步驟 ===");

        System.out.println("1. enqueue A");
        queue.enqueue("A");

        System.out.println("2. enqueue B");
        queue.enqueue("B");

        System.out.println("3. enqueue C");
        queue.enqueue("C");

        System.out.println("4. dequeue -> " + queue.dequeue());
        System.out.println("5. dequeue -> " + queue.dequeue());

        System.out.println("6. enqueue D");
        queue.enqueue("D");

        System.out.println("7. enqueue E");
        queue.enqueue("E");

        System.out.println("8. enqueue F");
        queue.enqueue("F");

        System.out.println("9. dequeue -> " + queue.dequeue());

        System.out.println("10. enqueue G");
        queue.enqueue("G");

        System.out.println("\n=== 操作完成後的內部狀態報告 ===");
        queue.printInternalState();

        System.out.println("\n=== 依照 FIFO 順序取出所有剩餘元素 ===");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }

        System.out.println("\n清空後的狀態：");
        queue.printInternalState();
    }
}