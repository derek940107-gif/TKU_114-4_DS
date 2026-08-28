```java
import java.util.ArrayList;
import java.util.List;

public class LibraryBookBst {

    public static class Book {
        private final String isbn;
        private final String title;
        private final String author;
        private boolean available;

        public Book(
                String isbn,
                String title,
                String author,
                boolean available) {

            if (isbn == null || isbn.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.isbn = isbn.trim();
            this.title = title.trim();
            this.author = author.trim();
            this.available = available;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        private void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return isbn
                    + "|" + title
                    + "|" + author
                    + "|" + available;
        }
    }

    private static class Node {
        Book book;
        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
        }
    }

    private Node root;
    private int size;

    public boolean add(Book book) {
        if (book == null) {
            return false;
        }

        if (root == null) {
            root = new Node(book);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int comparison =
                    book.getIsbn()
                            .compareTo(current.book.getIsbn());

            if (comparison == 0) {
                return false;
            }

            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node(book);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(book);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Book find(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }

        isbn = isbn.trim();

        Node current = root;

        while (current != null) {
            int comparison =
                    isbn.compareTo(current.book.getIsbn());

            if (comparison == 0) {
                return current.book;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean borrowBook(String isbn) {
        Book book = find(isbn);

        if (book == null || !book.isAvailable()) {
            return false;
        }

        book.setAvailable(false);
        return true;
    }

    public boolean returnBook(String isbn) {
        Book book = find(isbn);

        if (book == null || book.isAvailable()) {
            return false;
        }

        book.setAvailable(true);
        return true;
    }

    public boolean remove(String isbn) {
        Book book = find(isbn);

        if (book == null || !book.isAvailable()) {
            return false;
        }

        root = removeNode(root, isbn.trim());
        size--;
        return true;
    }

    private Node removeNode(Node node, String isbn) {
        if (node == null) {
            return null;
        }

        int comparison =
                isbn.compareTo(node.book.getIsbn());

        if (comparison < 0) {
            node.left = removeNode(node.left, isbn);
        } else if (comparison > 0) {
            node.right = removeNode(node.right, isbn);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = node.right;

            while (successor.left != null) {
                successor = successor.left;
            }

            node.book = successor.book;

            node.right = removeNode(
                    node.right,
                    successor.book.getIsbn()
            );
        }

        return node;
    }

    public List<Book> booksBetween(
            String lowIsbn,
            String highIsbn) {

        List<Book> result = new ArrayList<>();

        if (lowIsbn == null || highIsbn == null) {
            return result;
        }

        lowIsbn = lowIsbn.trim();
        highIsbn = highIsbn.trim();

        if (lowIsbn.isEmpty()
                || highIsbn.isEmpty()
                || lowIsbn.compareTo(highIsbn) > 0) {
            return result;
        }

        booksBetween(
                root,
                lowIsbn,
                highIsbn,
                result
        );

        return result;
    }

    private void booksBetween(
            Node node,
            String lowIsbn,
            String highIsbn,
            List<Book> result) {

        if (node == null) {
            return;
        }

        String isbn = node.book.getIsbn();

        if (isbn.compareTo(lowIsbn) > 0) {
            booksBetween(
                    node.left,
                    lowIsbn,
                    highIsbn,
                    result
            );
        }

        if (isbn.compareTo(lowIsbn) >= 0
                && isbn.compareTo(highIsbn) <= 0) {
            result.add(node.book);
        }

        if (isbn.compareTo(highIsbn) < 0) {
            booksBetween(
                    node.right,
                    lowIsbn,
                    highIsbn,
                    result
            );
        }
    }

    public List<Book> report() {
        List<Book> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(
            Node node,
            List<Book> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.book);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LibraryBookBst library = new LibraryBookBst();

        library.add(
                new Book(
                        "978-001",
                        "Java",
                        "Alice",
                        true
                )
        );

        library.add(
                new Book(
                        "978-003",
                        "Data Structure",
                        "Bob",
                        true
                )
        );

        library.add(
                new Book(
                        "978-002",
                        "Algorithm",
                        "Cindy",
                        true
                )
        );

        System.out.println(
                library.add(
                        new Book(
                                "978-002",
                                "Duplicate",
                                "Test",
                                true
                        )
                )
        );

        System.out.println(
                library.find("978-002")
        );

        System.out.println(
                library.borrowBook("978-002")
        );

        System.out.println(
                library.remove("978-002")
        );

        System.out.println(
                library.returnBook("978-002")
        );

        System.out.println(
                library.remove("978-002")
        );

        System.out.println(
                library.booksBetween(
                        "978-001",
                        "978-003"
                )
        );

        System.out.println(
                library.report()
        );

        System.out.println(
                "size = " + library.size()
        );
    }
}
```
