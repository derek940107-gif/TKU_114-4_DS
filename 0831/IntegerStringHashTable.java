```java
import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {
    private static class Entry {
        int key;
        String value;
        Entry next;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Entry[] buckets;
    private int size;

    public IntegerStringHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        buckets = new Entry[capacity];
    }

    private int index(int key) {
        return Math.floorMod(key, buckets.length);
    }

    public void put(int key, String value) {
        int index = index(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Entry entry = new Entry(key, value);
        entry.next = buckets[index];
        buckets[index] = entry;
        size++;
    }

    public String get(int key) {
        int index = index(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public boolean containsKey(int key) {
        int index = index(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key == key) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public String remove(int key) {
        int index = index(key);
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.key == key) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public List<String> bucketReport() {
        List<String> report = new ArrayList<>();

        for (int i = 0; i < buckets.length; i++) {
            StringBuilder chain = new StringBuilder();
            Entry current = buckets[i];

            while (current != null) {
                if (chain.length() > 0) {
                    chain.append(" -> ");
                }

                chain.append(current.key)
                     .append("=")
                     .append(current.value);

                current = current.next;
            }

            report.add("Bucket " + i + ": " + chain);
        }

        return report;
    }

    public static void main(String[] args) {
        IntegerStringHashTable table = new IntegerStringHashTable(5);

        table.put(10, "Apple");
        table.put(15, "Banana");
        table.put(-5, "Cherry");
        table.put(7, "Dog");
        table.put(12, "Egg");

        table.put(10, "Updated Apple");

        System.out.println("get(10) = " + table.get(10));
        System.out.println("containsKey(15) = " + table.containsKey(15));
        System.out.println("containsKey(99) = " + table.containsKey(99));
        System.out.println("size = " + table.size());

        System.out.println("remove(15) = " + table.remove(15));
        System.out.println("size = " + table.size());

        for (String report : table.bucketReport()) {
            System.out.println(report);
        }
    }
}
```
