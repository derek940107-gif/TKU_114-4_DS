public class BstDuplicateCounter {

    static class Node {
        int key;
        int count;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.count = 1;
        }
    }

    private Node root;

    public void add(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            node.count++;
        }

        return node;
    }

    public void printInorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.key + "(" + node.count + ") ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        BstDuplicateCounter tree = new BstDuplicateCounter();

        int[] values = {50, 30, 70, 30, 50, 50, 20, 70, 80};

        for (int value : values) {
            tree.add(value);
        }

        tree.printInorder();
    }
}