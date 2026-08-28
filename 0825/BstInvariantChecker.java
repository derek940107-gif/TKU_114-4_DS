public class BstInvariantChecker {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static boolean isValid(Node root) {
        return isValid(root, null, null);
    }

    private static boolean isValid(Node node, Integer low, Integer high) {
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

    public static Node validTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        return root;
    }

    public static Node invalidLeftTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.left.right = new Node(55);

        return root;
    }

    public static Node invalidRightTree() {
        Node root = new Node(50);

        root.right = new Node(70);
        root.right.left = new Node(40);

        return root;
    }

    public static Node invalidDuplicateTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);
        root.left.right = new Node(50);

        return root;
    }

    public static void main(String[] args) {
        Node valid = validTree();
        Node invalidLeft = invalidLeftTree();
        Node invalidRight = invalidRightTree();
        Node invalidDuplicate = invalidDuplicateTree();

        System.out.println("Valid Tree: " + isValid(valid));
        System.out.println("Invalid Left Tree: " + isValid(invalidLeft));
        System.out.println("Invalid Right Tree: " + isValid(invalidRight));
        System.out.println("Invalid Duplicate Tree: " + isValid(invalidDuplicate));
    }
}