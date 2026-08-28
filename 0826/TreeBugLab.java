import java.util.ArrayList;
import java.util.List;

public class TreeBugLab {

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
    }

    private static void printTest(
            String name,
            boolean result) {

        System.out.println(
            name + ": " + (result ? "PASS" : "FAIL")
        );
    }

    private static void searchDirectionCase() {
        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(70);

        printTest(
            "搜尋方向",
            tree.contains(70) && tree.contains(30)
        );
    }

    private static void inorderCase() {
        Bst tree = new Bst();

        tree.add(2);
        tree.add(1);
        tree.add(3);

        printTest(
            "Inorder 順序",
            tree.inorder().equals(List.of(1, 2, 3))
        );
    }

    private static void deleteChildCase() {
        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(20);

        boolean removed = tree.remove(30);

        printTest(
            "刪除遺失子項目",
            removed
                && tree.inorder().equals(List.of(20, 50))
                && tree.size() == 2
        );
    }

    private static void validationCase() {
        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(70);

        printTest(
            "全域驗證",
            tree.isValid()
        );
    }

    private static void invalidBoundaryCase() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.left.right = new Node(60);

        boolean valid = isValid(root, null, null);

        printTest(
            "祖先 Boundary 驗證",
            !valid
        );
    }

    private static boolean isValid(
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

    public static void main(String[] args) {
        System.out.println("Tree Bug Lab");

        searchDirectionCase();
        inorderCase();
        deleteChildCase();
        validationCase();
        invalidBoundaryCase();

        System.out.println();

        Bst tree = new Bst();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("inorder = " + tree.inorder());
        System.out.println("size = " + tree.size());
        System.out.println("valid = " + tree.isValid());

        tree.remove(30);

        System.out.println("after remove 30");
        System.out.println("inorder = " + tree.inorder());
        System.out.println("size = " + tree.size());
        System.out.println("valid = " + tree.isValid());
    }
}