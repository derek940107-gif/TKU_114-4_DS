```java
public class BookIsbnHashTable {

    private static class Entry {
        String isbn;
        String title;
        Entry next;

        Entry(String isbn, String title, Entry next) {
            this.isbn = isbn;
            this.title = title;
            this.next = next;
        }
    }

    private Entry[] buckets;
    private int size;

    public BookIsbnHashTable() {
        buckets = new Entry[11];
        size = 0;
    }

    private int hash(String isbn) {
        return (isbn.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void put(String isbn, String title) {
        if (isbn == null || title == null) {
            throw new IllegalArgumentException("ISBN and title cannot be null");
        }

        int index = hash(isbn);
        Entry current = buckets[index];

        while (current != null) {
            if (current.isbn.equals(isbn)) {
                current.title = title;
                return;
            }
            current = current.next;
        }

        buckets[index] = new Entry(isbn, title, buckets[index]);
        size++;
    }

    public String get(String isbn) {
        if (isbn == null) {
            return null;
        }

        int index = hash(isbn);
        Entry current = buckets[index];

        while (current != null) {
            if (current.isbn.equals(isbn)) {
                return current.title;
            }
            current = current.next;
        }

        return null;
    }

    public boolean contains(String isbn) {
        return get(isbn) != null;
    }

    public String remove(String isbn) {
        if (isbn == null) {
            return null;
        }

        int index = hash(isbn);
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.isbn.equals(isbn)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return current.title;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }

    public void printBucketReport() {
        System.out.println("儲存桶報告");

        for (int i = 0; i < buckets.length; i++) {
            int count = 0;
            Entry current = buckets[i];

            while (current != null) {
                count++;
                current = current.next;
            }

            System.out.println("Bucket " + i + ": " + count + " 筆");
        }

        System.out.println("總筆數：" + size);
        System.out.printf("負載因子：%.2f%n", loadFactor());
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable();

        table.put("978-0134685991", "Effective Java");
        table.put("978-0135166307", "Core Java");
        table.put("978-0262033848", "Introduction to Algorithms");
        table.put("978-1492056355", "Java Programming");

        System.out.println("搜尋結果：" + table.get("978-0134685991"));

        table.put("978-0134685991", "Effective Java 3rd Edition");

        System.out.println("更新後：" + table.get("978-0134685991"));

        System.out.println("是否存在：" + table.contains("978-0135166307"));

        System.out.println("刪除：" + table.remove("978-0262033848"));

        System.out.println("目前大小：" + table.size());

        table.printBucketReport();
    }
}
```
