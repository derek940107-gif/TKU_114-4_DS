public class SkewedBstReport {

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

        public int size() {
            return size;
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
    }

    public static Bst buildTree(int[] values) {
        Bst tree = new Bst();

        for (int value : values) {
            tree.add(value);
        }

        return tree;
    }

    public static void printReport(String name, Bst tree, int target) {
        System.out.println(name);
        System.out.println("size = " + tree.size());
        System.out.println("height = " + tree.height());
        System.out.println(
            "search(" + target + ") comparisons = "
            + tree.searchComparisons(target)
        );
        System.out.println();
    }

    public static void main(String[] args) {
        int[] sortedData = {
            10, 20, 30, 40, 50, 60, 70
        };

        int[] balancedOrder = {
            40, 20, 60, 10, 30, 50, 70
        };

        Bst skewedTree = buildTree(sortedData);
        Bst balancedTree = buildTree(balancedOrder);

        printReport("Sorted Data", skewedTree, 70);
        printReport("Balanced Order", balancedTree, 70);
    }
}