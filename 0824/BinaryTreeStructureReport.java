public class BinaryTreeStructureReport {

    public static class TreeNode<T> {
        private T val;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(T val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public T getVal() {
            return val;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }
    }

    public static class BinaryTree<T> {
        private TreeNode<T> root;

        public BinaryTree() {
            this.root = null;
        }

        public BinaryTree(TreeNode<T> root) {
            this.root = root;
        }

        public TreeNode<T> getRoot() {
            return root;
        }

        public void setRoot(TreeNode<T> root) {
            this.root = root;
        }

        public int size() {
            return sizeHelper(root);
        }

        private int sizeHelper(TreeNode<T> node) {
            if (node == null) {
                return 0;
            }
            return 1 + sizeHelper(node.left) + sizeHelper(node.right);
        }

        public int leafCount() {
            return leafCountHelper(root);
        }

        private int leafCountHelper(TreeNode<T> node) {
            if (node == null) {
                return 0;
            }
            if (node.left == null && node.right == null) {
                return 1;
            }
            return leafCountHelper(node.left) + leafCountHelper(node.right);
        }

        public int height() {
            return heightHelper(root);
        }

        private int heightHelper(TreeNode<T> node) {
            if (node == null) {
                return -1;
            }
            return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
        }

        public void printLeaves() {
            System.out.print("所有葉子節點: ");
            if (root == null) {
                System.out.println("[無]");
                return;
            }
            printLeavesHelper(root);
            System.out.println();
        }

        private void printLeavesHelper(TreeNode<T> node) {
            if (node == null) {
                return;
            }
            if (node.left == null && node.right == null) {
                System.out.print(node.val + " ");
                return;
            }
            printLeavesHelper(node.left);
            printLeavesHelper(node.right);
        }

        public void printReport(String treeName) {
            System.out.println("=== " + treeName + " 結構報表 ===");
            System.out.println("根節點內容 (Root): " + (root != null ? root.val : "[空樹]"));
            System.out.println("節點總數 (Size): " + size());
            System.out.println("葉子總數 (Leaf Count): " + leafCount());
            System.out.println("樹高度 (Height): " + height());
            printLeaves();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        emptyTree.printReport("測試 1：空樹 (Empty Tree)");

        BinaryTree<String> singleNodeTree = new BinaryTree<>(new TreeNode<>("A"));
        singleNodeTree.printReport("測試 2：單節點樹 (Single Node Tree)");

        TreeNode<String> root = new TreeNode<>("A");
        TreeNode<String> nodeB = new TreeNode<>("B");
        TreeNode<String> nodeC = new TreeNode<>("C");
        TreeNode<String> nodeD = new TreeNode<>("D");
        TreeNode<String> nodeE = new TreeNode<>("E");
        TreeNode<String> nodeF = new TreeNode<>("F");
        TreeNode<String> nodeG = new TreeNode<>("G");

        root.setLeft(nodeB);
        root.setRight(nodeC);

        nodeB.setLeft(nodeD);
        nodeB.setRight(nodeE);

        nodeC.setLeft(nodeF);
        nodeC.setRight(nodeG);

        BinaryTree<String> multiNodeTree = new BinaryTree<>(root);
        multiNodeTree.printReport("測試 3：滿七節點二元樹 (7-Node Tree)");
    }
}