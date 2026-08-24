import java.util.EmptyStackException;

public class GenericArrayStackDemo {

    public static class ArrayStack<T> {
        private Object[] elements;
        private int top;
        private int capacity;

        public ArrayStack(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than 0");
            }
            this.capacity = capacity;
            this.elements = new Object[capacity];
            this.top = -1;
        }

        public void push(T item) {
            if (isFull()) {
                System.out.println("Stack is full, cannot push: " + item);
                return;
            }
            elements[++top] = item;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T item = (T) elements[top];
            elements[top--] = null;
            return item;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return (T) elements[top];
        }

        public int size() {
            return top + 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == capacity - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing ArrayStack<String> ===");
        ArrayStack<String> stringStack = new ArrayStack<>(3);
        
        System.out.println("isEmpty: " + stringStack.isEmpty());
        stringStack.push("Java");
        stringStack.push("Data Structures");
        stringStack.push("Generics");
        
        System.out.println("isFull: " + stringStack.isFull());
        stringStack.push("Overflow Item");
        
        System.out.println("Peek top element: " + stringStack.peek());
        System.out.println("Stack size: " + stringStack.size());

        while (!stringStack.isEmpty()) {
            System.out.println("Popped: " + stringStack.pop());
        }

        System.out.println("\n=== Testing ArrayStack<Integer> ===");
        ArrayStack<Integer> intStack = new ArrayStack<>(2);
        
        intStack.push(100);
        intStack.push(200);
        System.out.println("Integer stack top: " + intStack.peek());
        System.out.println("Integer stack size: " + intStack.size());
        System.out.println("Popped: " + intStack.pop());
        System.out.println("Integer stack size after pop: " + intStack.size());
    }
}