```java
import java.util.ArrayList;
import java.util.List;

public class TreeShapeComparison {

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

        public int searchComparisons(int target) {
            Node current = root;
            int comparisons = 0;

            while (current != null) {
                comparisons++;

                if (target == current.value) {
                    return comparisons;
                }

                if (target < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return comparisons;
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            return 1 + Math.max(leftHeight, rightHeight);
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

        public int size() {
            return size;
        }
    }

    public static int totalSearchComparisons(
            Bst tree,
            int[] values) {

        int total = 0;

        for (int value : values) {
            total += tree.searchComparisons(value);
        }

        return total;
    }

    public static int missingSearchComparisons(
            Bst tree,
            int[] missingValues) {

        int total = 0;

        for (int value : missingValues) {
            total += tree.searchComparisons(value);
        }

        return total;
    }

    public static Bst buildTree(int[] values) {
        Bst tree = new Bst();

        for (int value : values) {
            tree.add(value);
        }

        return tree;
    }

    public static void printReport(
            String name,
            Bst tree,
            int[] values,
            int[] missingValues) {

        int totalComparisons =
            totalSearchComparisons(tree, values);

        int missingComparisons =
            missingSearchComparisons(tree, missingValues);

        System.out.println(name);
        System.out.println(
            "size = " + tree.size()
        );
        System.out.println(
            "height = " + tree.height()
        );
        System.out.println(
            "all key comparisons = "
            + totalComparisons
        );
        System.out.println(
            "missing key comparisons = "
            + missingComparisons
        );
        System.out.println();
    }

    public static void main(String[] args) {

        int[] ascending = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] descending = {
            15, 14, 13, 12, 11,
            10, 9, 8, 7, 6,
            5, 4, 3, 2, 1
        };

        int[] balancedOrder = {
            8, 4, 12, 2, 6,
            10, 14, 1, 3, 5,
            7, 9, 11, 13, 15
        };

        int[] missingValues = {
            0, 16
        };

        Bst ascendingTree =
            buildTree(ascending);

        Bst descendingTree =
            buildTree(descending);

        Bst balancedTree =
            buildTree(balancedOrder);

        System.out.println("Tree Shape Comparison");
        System.out.println();

        printReport(
            "Ascending",
            ascendingTree,
            ascending,
            missingValues
        );

        printReport(
            "Descending",
            descendingTree,
            ascending,
            missingValues
        );

        printReport(
            "Balanced",
            balancedTree,
            ascending,
            missingValues
        );

        System.out.println(
            "Ascending inorder = "
            + ascendingTree.inorder()
        );

        System.out.println(
            "Descending inorder = "
            + descendingTree.inorder()
        );

        System.out.println(
            "Balanced inorder = "
            + balancedTree.inorder()
        );
    }
}
```
