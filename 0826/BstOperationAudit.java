import java.util.ArrayList;
import java.util.List;

public class BstOperationAudit {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

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

    private void audit(String operation, boolean result) {
        System.out.println(
            "operation = " + operation
        );
        System.out.println(
            "result = " + result
        );
        System.out.println(
            "inorder = " + inorder()
        );
        System.out.println(
            "size = " + size()
        );
        System.out.println(
            "height = " + height()
        );
        System.out.println(
            "valid = " + isValid()
        );
        System.out.println();
    }

    public static void main(String[] args) {
        BstOperationAudit tree = new BstOperationAudit();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80, 65
        };

        for (int value : values) {
            boolean result = tree.add(value);
            tree.audit("add " + value, result);
        }

        boolean duplicate = tree.add(40);
        tree.audit("add 40", duplicate);

        boolean missing = tree.remove(999);
        tree.audit("remove 999", missing);

        boolean leaf = tree.remove(20);
        tree.audit("remove 20", leaf);

        boolean oneChild = tree.remove(60);
        tree.audit("remove 60", oneChild);

        boolean twoChildren = tree.remove(70);
        tree.audit("remove 70", twoChildren);
    }
}