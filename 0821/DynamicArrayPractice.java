import java.util.Arrays;

public class DynamicArrayPractice {

    public static class DynamicArray<T> {
        private Object[] elements;
        private int size;
        private static final int DEFAULT_CAPACITY = 4;

        public DynamicArray() {
            this.elements = new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }

        public DynamicArray(int initialCapacity) {
            if (initialCapacity <= 0) {
                this.elements = new Object[DEFAULT_CAPACITY];
            } else {
                this.elements = new Object[initialCapacity];
            }
            this.size = 0;
        }

        public void add(T value) {
            ensureCapacity();
            elements[size++] = value;
        }

        public void add(int index, T value) {
            if (index < 0 || index > size) {
                System.out.println("Invalid index for add: " + index);
                return;
            }
            ensureCapacity();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 0 || index >= size) {
                System.out.println("Invalid index for get: " + index);
                return null;
            }
            return (T) elements[index];
        }

        @SuppressWarnings("unchecked")
        public T set(int index, T value) {
            if (index < 0 || index >= size) {
                System.out.println("Invalid index for set: " + index);
                return null;
            }
            T oldValue = (T) elements[index];
            elements[index] = value;
            return oldValue;
        }

        @SuppressWarnings("unchecked")
        public T remove(int index) {
            if (index < 0 || index >= size) {
                System.out.println("Invalid index for remove: " + index);
                return null;
            }
            T removedValue = (T) elements[index];
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(elements, index + 1, elements, index, numMoved);
            }
            elements[--size] = null;
            return removedValue;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return elements.length;
        }

        private void ensureCapacity() {
            if (size == elements.length) {
                int newCapacity = elements.length * 2;
                Object[] newElements = new Object[newCapacity];
                System.arraycopy(elements, 0, newElements, 0, size);
                elements = newElements;
            }
        }

        @Override
        public String toString() {
            Object[] activeElements = new Object[size];
            System.arraycopy(elements, 0, activeElements, 0, size);
            return Arrays.toString(activeElements);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing DynamicArray<String> ===");
        DynamicArray<String> strArray = new DynamicArray<>(2);
        System.out.println("Initial capacity: " + strArray.capacity() + ", size: " + strArray.size());

        strArray.add("A");
        strArray.add("B");
        System.out.println("After adding 2 items - Capacity: " + strArray.capacity() + ", Content: " + strArray);

        strArray.add("C");
        System.out.println("After capacity expansion - Capacity: " + strArray.capacity() + ", Content: " + strArray);

        strArray.add(1, "X");
        System.out.println("After inserting 'X' at index 1: " + strArray);

        System.out.println("Get at index 2: " + strArray.get(2));
        System.out.println("Set at index 2 to 'Y': " + strArray.set(2, "Y"));
        System.out.println("Content after set: " + strArray);

        System.out.println("Remove at index 1: " + strArray.remove(1));
        System.out.println("Content after remove: " + strArray + ", size: " + strArray.size());

        System.out.println("\n=== Testing DynamicArray<Integer> ===");
        DynamicArray<Integer> intArray = new DynamicArray<>(2);
        intArray.add(10);
        intArray.add(20);
        intArray.add(30);
        System.out.println("Integer array content: " + intArray);

        System.out.println("\n=== Testing Invalid Indices & Empty Structure Operations ===");
        DynamicArray<String> emptyArray = new DynamicArray<>();

        System.out.println("Attempting remove on empty array (index 0):");
        emptyArray.remove(0);

        System.out.println("Attempting get with index -1:");
        strArray.get(-1);

        System.out.println("Attempting set with index equal to size (" + strArray.size() + "):");
        strArray.set(strArray.size(), "OutOfBounds");

        System.out.println("Attempting remove with index equal to size (" + strArray.size() + "):");
        strArray.remove(strArray.size());

        System.out.println("Attempting add with invalid index -1:");
        strArray.add(-1, "Invalid");
    }
}