import java.util.NoSuchElementException;

public class BinaryTreeStatistics {

    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    public static class BinaryTree {
        private TreeNode root;

        public BinaryTree() {
            this.root = null;
        }

        public BinaryTree(TreeNode root) {
            this.root = root;
        }

        public TreeNode getRoot() {
            return root;
        }

        public void setRoot(TreeNode root) {
            this.root = root;
        }

        public int size() {
            return sizeHelper(root);
        }

        private int sizeHelper(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return 1 + sizeHelper(node.left) + sizeHelper(node.right);
        }

        public int sum() {
            return sumHelper(root);
        }

        private int sumHelper(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return node.val + sumHelper(node.left) + sumHelper(node.right);
        }

        public int maximum() {
            if (root == null) {
                throw new NoSuchElementException("Cannot find maximum of an empty tree");
            }
            return maximumHelper(root);
        }

        private int maximumHelper(TreeNode node) {
            if (node == null) {
                return Integer.MIN_VALUE;
            }
            int leftMax = maximumHelper(node.left);
            int rightMax = maximumHelper(node.right);
            return Math.max(node.val, Math.max(leftMax, rightMax));
        }

        public int leafCount() {
            return leafCountHelper(root);
        }

        private int leafCountHelper(TreeNode node) {
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

        private int heightHelper(TreeNode node) {
            if (node == null) {
                return -1;
            }
            return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
        }

        public boolean contains(int target) {
            return containsHelper(root, target);
        }

        private boolean containsHelper(TreeNode node, int target) {
            if (node == null) {
                return false;
            }
            if (node.val == target) {
                return true;
            }
            return containsHelper(node.left, target) || containsHelper(node.right, target);
        }

        public void printStatistics(String treeName) {
            System.out.println("=== " + treeName + " 統計報表 ===");
            System.out.println("節點總數 (Size): " + size());
            System.out.println("數值總和 (Sum): " + sum());
            
            try {
                System.out.println("最大數值 (Maximum): " + maximum());
            } catch (NoSuchElementException e) {
                System.out.println("最大數值 (Maximum): 例外拋出 -> " + e.getMessage());
            }

            System.out.println("葉子總數 (Leaf Count): " + leafCount());
            System.out.println("樹的高度 (Height): " + height());
            System.out.println("是否包含 15: " + contains(15));
            System.out.println("是否包含 99: " + contains(99));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinaryTree emptyTree = new BinaryTree();
        emptyTree.printStatistics("測試 1：空樹");

        BinaryTree singleNodeTree = new BinaryTree(new TreeNode(42));
        singleNodeTree.printStatistics("測試 2：單節點樹 (值: 42)");

        BinaryTree negativeTree = new BinaryTree(
            new TreeNode(-10,
                new TreeNode(-20),
                new TreeNode(-5)
            )
        );
        negativeTree.printStatistics("測試 3：全負數樹");

        TreeNode root = new TreeNode(10);
        TreeNode nodeB = new TreeNode(5);
        TreeNode nodeC = new TreeNode(15);
        TreeNode nodeD = new TreeNode(3);
        TreeNode nodeE = new TreeNode(7);
        TreeNode nodeF = new TreeNode(12);
        TreeNode nodeG = new TreeNode(18);

        root.setLeft(nodeB);
        root.setRight(nodeC);

        nodeB.setLeft(nodeD);
        nodeB.setRight(nodeE);

        nodeC.setLeft(nodeF);
        nodeC.setRight(nodeG);

        BinaryTree multiNodeTree = new BinaryTree(root);
        multiNodeTree.printStatistics("測試 4：多節點樹");
    }
}