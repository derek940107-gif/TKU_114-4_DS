public class ThreeTraversalPractice {

    public static class TreeNode {
        private String val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(String val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public TreeNode(String val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class BinaryTree {
        private TreeNode root;

        public BinaryTree(TreeNode root) {
            this.root = root;
        }

        public void preorder() {
            System.out.print("Pre-order: ");
            preorderHelper(root);
            System.out.println();
        }

        private void preorderHelper(TreeNode node) {
            if (node == null) {
                return;
            }
            System.out.print(node.val + " ");
            preorderHelper(node.left);
            preorderHelper(node.right);
        }

        public void inorder() {
            System.out.print("In-order: ");
            inorderHelper(root);
            System.out.println();
        }

        private void inorderHelper(TreeNode node) {
            if (node == null) {
                return;
            }
            inorderHelper(node.left);
            System.out.print(node.val + " ");
            inorderHelper(node.right);
        }

        public void postorder() {
            System.out.print("Post-order: ");
            postorderHelper(root);
            System.out.println();
        }

        private void postorderHelper(TreeNode node) {
            if (node == null) {
                return;
            }
            postorderHelper(node.left);
            postorderHelper(node.right);
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeF = new TreeNode("F", nodeB, null);

        TreeNode nodeR = new TreeNode("R");
        TreeNode nodeZ = new TreeNode("Z");
        TreeNode nodeT = new TreeNode("T", nodeR, nodeZ);

        TreeNode rootM = new TreeNode("M", nodeF, nodeT);

        BinaryTree tree = new BinaryTree(rootM);

        tree.preorder();
        tree.inorder();
        tree.postorder();
    }
}