```java
import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class Bst {
        private Node root;
        private int size;

        public boolean add(int value) {
            if (root == null) {
                root = new Node(value);
                size++;
                return true;
            }

            Node current = root;

            while (true) {
                if (value == current.value) {
                    return false;
                }

                if (value < current.value) {
                    if (current.left == null) {
                        current.left = new Node(value);
                        size++;
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(value);
                        size++;
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        public boolean contains(int value) {
            Node current = root;

            while (current != null) {
                if (value == current.value) {
                    return true;
                }

                if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
        }

        public boolean remove(int value) {
            if (!contains(value)) {
                return false;
            }

            root = removeNode(root, value);
            size--;
            return true;
        }

        private Node removeNode(Node node, int value) {
            if (node == null) {
                return null;
            }

            if (value < node.value) {
                node.left = removeNode(node.left, value);
            } else if (value > node.value) {
                node.right = removeNode(node.right, value);
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

                node.value = successor.value;
                node.right =
                    removeNode(node.right, successor.value);
            }

            return node;
        }

        public List<Integer> inorder() {
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(
                Node node,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);
            result.add(node.value);
            inorder(node.right, result);
        }

        public List<Integer> range(int low, int high) {
            List<Integer> result = new ArrayList<>();

            if (low > high) {
                return result;
            }

            range(root, low, high, result);
            return result;
        }

        private void range(
                Node node,
                int low,
                int high,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            if (node.value > low) {
                range(node.left, low, high, result);
            }

            if (node.value >= low && node.value <= high) {
                result.add(node.value);
            }

            if (node.value < high) {
                range(node.right, low, high, result);
            }
        }

        public boolean isValid() {
            return isValid(root, null, null);
        }

        private boolean isValid(
                Node node,
                Integer low,
                Integer high) {

            if (node == null) {
                return true;
            }

            if (low != null && node.value <= low) {
                return false;
            }

            if (high != null && node.value >= high) {
                return false;
            }

            return isValid(node.left, low, node.value)
                    && isValid(node.right, node.value, high);
        }

        public int size() {
            return size;
        }

        public Node getRoot() {
            return root;
        }
    }

    static int passed = 0;
    static int failed = 0;

    public static void check(
            String description,
            boolean condition) {

        if (condition) {
            System.out.println("PASS: " + description);
            passed++;
        } else {
            System.out.println("FAIL: " + description);
            failed++;
        }
    }

    public static void main(String[] args) {

        Bst empty = new Bst();

        check(
            "empty size",
            empty.size() == 0
        );

        check(
            "empty contains",
            !empty.contains(10)
        );

        check(
            "empty inorder",
            empty.inorder().isEmpty()
        );

        check(
            "empty range",
            empty.range(1, 10).isEmpty()
        );

        check(
            "empty invariant",
            empty.isValid()
        );

        Bst tree = new Bst();

        check(
            "add root",
            tree.add(50)
        );

        check(
            "root size",
            tree.size() == 1
        );

        check(
            "root contains",
            tree.contains(50)
        );

        check(
            "duplicate root rejected",
            !tree.add(50)
        );

        check(
            "duplicate does not change size",
            tree.size() == 1
        );

        check(
            "root invariant",
            tree.isValid()
        );

        check(
            "add left child",
            tree.add(30)
        );

        check(
            "add right child",
            tree.add(70)
        );

        check(
            "leaf contains",
            tree.contains(30)
        );

        check(
            "missing value",
            !tree.contains(999)
        );

        check(
            "inorder after insertion",
            tree.inorder().equals(
                List.of(30, 50, 70)
            )
        );

        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        check(
            "full tree size",
            tree.size() == 7
        );

        check(
            "full inorder",
            tree.inorder().equals(
                List.of(20, 30, 40, 50, 60, 70, 80)
            )
        );

        check(
            "full tree invariant",
            tree.isValid()
        );

        check(
            "range includes endpoints",
            tree.range(30, 70).equals(
                List.of(30, 40, 50, 60, 70)
            )
        );

        check(
            "empty range",
            tree.range(90, 100).isEmpty()
        );

        check(
            "low greater than high",
            tree.range(70, 30).isEmpty()
        );

        check(
            "remove leaf",
            tree.remove(20)
        );

        check(
            "leaf removed",
            !tree.contains(20)
        );

        check(
            "size after leaf removal",
            tree.size() == 6
        );

        check(
            "invariant after leaf removal",
            tree.isValid()
        );

        check(
            "remove one-child node",
            tree.remove(30)
        );

        check(
            "one-child replacement",
            tree.inorder().equals(
                List.of(40, 50, 60, 70, 80)
            )
        );

        check(
            "size after one-child removal",
            tree.size() == 5
        );

        check(
            "remove two-child node",
            tree.remove(70)
        );

        check(
            "two-child replacement",
            tree.inorder().equals(
                List.of(40, 50, 60, 80)
            )
        );

        check(
            "size after two-child removal",
            tree.size() == 4
        );

        check(
            "invariant after two-child removal",
            tree.isValid()
        );

        check(
            "missing remove",
            !tree.remove(999)
        );

        check(
            "missing remove keeps size",
            tree.size() == 4
        );

        check(
            "missing remove keeps tree",
            tree.inorder().equals(
                List.of(40, 50, 60, 80)
            )
        );

        check(
            "remove root",
            tree.remove(50)
        );

        check(
            "root removed",
            !tree.contains(50)
        );

        check(
            "root removal invariant",
            tree.isValid()
        );

        check(
            "root removal inorder",
            tree.inorder().equals(
                List.of(40, 60, 80)
            )
        );

        check(
            "final size",
            tree.size() == 3
        );

        System.out.println();
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println(
            "Total: " + (passed + failed)
        );
    }
}
```
