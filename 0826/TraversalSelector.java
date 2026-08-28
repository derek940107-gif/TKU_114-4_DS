public class TraversalSelector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    public static String preorder(Node root) {
        if (root == null) {
            return "";
        }

        return root.value
                + preorder(root.left)
                + preorder(root.right);
    }

    public static String inorder(Node root) {
        if (root == null) {
            return "";
        }

        if (root.left == null && root.right == null) {
            return root.value;
        }

        return "("
                + inorder(root.left)
                + root.value
                + inorder(root.right)
                + ")";
    }

    public static String postorder(Node root) {
        if (root == null) {
            return "";
        }

        return postorder(root.left)
                + postorder(root.right)
                + root.value;
    }

    public static void main(String[] args) {
        Node root = new Node("*");

        root.left = new Node("+");
        root.right = new Node("-");

        root.left.left = new Node("a");
        root.left.right = new Node("b");

        root.right.left = new Node("c");
        root.right.right = new Node("d");

        System.out.println("前序：" + preorder(root));
        System.out.println("中序：" + inorder(root));
        System.out.println("後序：" + postorder(root));
    }
}