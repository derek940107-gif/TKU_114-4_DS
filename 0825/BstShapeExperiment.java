public class BstShapeExperiment {

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

        public void add(int value) {
            if (root == null) {
                root = new Node(value);
                size++;
                return;
            }

            Node current = root;

            while (true) {
                if (value == current.value) {
                    return;
                }

                if (value < current.value) {
                    if (current.left == null) {
                        current.left = new Node(value);
                        size++;
                        return;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(value);
                        size++;
                        return;
                    }
                    current = current.right;
                }
            }
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(
                height(node.left),
                height(node.right)
            );
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

        public int totalSearchComparisons(int[] values) {
            int total = 0;

            for (int value : values) {
                total += searchComparisons(value);
            }

            return total;
        }

        public int size() {
            return size;
        }
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
            int[] values) {

        System.out.println(name);
        System.out.println("size = " + tree.size());
        System.out.println("height = " + tree.height());
        System.out.println(
            "total search comparisons = "
            + tree.totalSearchComparisons(values)
        );
        System.out.println();
    }

    public static void main(String[] args) {
        int[] values = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] sortedOrder = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] balancedOrder = {
            8, 4, 12, 2, 6,
            10, 14, 1, 3, 5,
            7, 9, 11, 13, 15
        };

        Bst sortedTree = buildTree(sortedOrder);
        Bst balancedTree = buildTree(balancedOrder);

        printReport("Sorted Insertion", sortedTree, values);
        printReport("Balanced Insertion", balancedTree, values);
    }
}