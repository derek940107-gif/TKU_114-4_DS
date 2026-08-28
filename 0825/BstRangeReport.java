public class BstRangeReport {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }

        return node;
    }

    public Integer min() {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    public Integer max() {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    public void printRange(int low, int high) {
        if (low > high) {
            System.out.println();
            return;
        }

        printRange(root, low, high);
        System.out.println();
    }

    private void printRange(Node node, int low, int high) {
        if (node == null) {
            return;
        }

        if (node.value > low) {
            printRange(node.left, low, high);
        }

        if (node.value >= low && node.value <= high) {
            System.out.print(node.value + " ");
        }

        if (node.value < high) {
            printRange(node.right, low, high);
        }
    }

    public static void main(String[] args) {
        BstRangeReport tree = new BstRangeReport();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("min = " + tree.min());
        System.out.println("max = " + tree.max());

        tree.printRange(30, 70);
        tree.printRange(65, 35);
    }
}