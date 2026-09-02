```java
public class ResizableStringMap {

    private static class Entry {
        String key;
        String value;
        Entry next;

        Entry(String key, String value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry[] buckets;
    private int size;

    public ResizableStringMap() {
        buckets = new Entry[11];
        size = 0;
    }

    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void put(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        buckets[index] = new Entry(key, value, buckets[index]);
        size++;

        if ((double) size / buckets.length > 0.75) {
            resize();
        }
    }

    public String get(String key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public String remove(String key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
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

    private void resize() {
        Entry[] oldBuckets = buckets;
        buckets = new Entry[oldBuckets.length * 2 + 1];

        for (Entry entry : oldBuckets) {
            Entry current = entry;

            while (current != null) {
                Entry next = current.next;
                int index = hash(current.key);

                current.next = buckets[index];
                buckets[index] = current;

                current = next;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        buckets = new Entry[11];
        size = 0;
    }
}
```
