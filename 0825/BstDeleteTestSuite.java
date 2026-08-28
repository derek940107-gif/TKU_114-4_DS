import java.util.ArrayList;
import java.util.List;

public class BstDeleteTestSuite {

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
                node.right = removeNode(node.right, successor.value);
            }

            return node;
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

        public int size() {
            return size;
        }

        public List<Integer> inorder() {
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(Node node, List<Integer> result) {
            if (node == null) {
                return;
            }

            inorder(node.left, result);
            result.add(node.value);
            inorder(node.right, result);
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    private static void check(
            String testName,
            boolean condition) {

        System.out.println(
            testName + ": " + (condition ? "PASS" : "FAIL")
        );
    }

    public static void main(String[] args) {

        Bst tree = new Bst();

        check(
            "空樹刪除",
            !tree.remove(10) && tree.size() == 0
        );

        tree.add(50);

        check(
            "單根存在",
            tree.contains(50) && tree.size() == 1
        );

        check(
            "單根刪除",
            tree.remove(50)
            && tree.size() == 0
            && tree.isEmpty()
        );

        tree.add(50);
        tree.add(30);

        check(
            "一子根刪除",
            tree.remove(50)
            && tree.inorder().equals(List.of(30))
            && tree.size() == 1
        );

        tree = new Bst();
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        check(
            "二子根刪除",
            tree.remove(50)
            && tree.inorder().equals(
                List.of(20, 30, 40, 60, 70, 80)
            )
            && tree.size() == 6
        );

        check(
            "缺失值刪除",
            !tree.remove(999)
            && tree.size() == 6
        );

        tree = new Bst();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        for (int value : values) {
            tree.remove(value);
        }

        check(
            "連續刪除到空",
            tree.size() == 0
            && tree.isEmpty()
            && tree.inorder().isEmpty()
        );
    }
}