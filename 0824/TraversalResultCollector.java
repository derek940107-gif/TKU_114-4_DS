import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalResultCollector {

    public static class TreeNode<T> {
        private T val;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(T val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.left = left;
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

        public List<String> preorder() {
            List<String> result = new ArrayList<>();
            preorderHelper(root, result);
            return result;
        }

        private void preorderHelper(TreeNode<T> node, List<String> result) {
            if (node == null) {
                return;
            }
            result.add(String.valueOf(node.val));
            preorderHelper(node.left, result);
            preorderHelper(node.right, result);
        }

        public List<String> inorder() {
            List<String> result = new ArrayList<>();
            inorderHelper(root, result);
            return result;
        }

        private void inorderHelper(TreeNode<T> node, List<String> result) {
            if (node == null) {
                return;
            }
            inorderHelper(node.left, result);
            result.add(String.valueOf(node.val));
            inorderHelper(node.right, result);
        }

        public List<String> postorder() {
            List<String> result = new ArrayList<>();
            postorderHelper(root, result);
            return result;
        }

        private void postorderHelper(TreeNode<T> node, List<String> result) {
            if (node == null) {
                return;
            }
            postorderHelper(node.left, result);
            postorderHelper(node.right, result);
            result.add(String.valueOf(node.val));
        }

        public List<String> levelOrder() {
            List<String> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode<T> current = queue.poll();
                result.add(String.valueOf(current.val));
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            return result;
        }

        public void printAllTraversals(String treeName) {
            System.out.println("=== " + treeName + " ===");
            System.out.println("前序遍歷 (Pre-order) : " + preorder());
            System.out.println("中序遍歷 (In-order)  : " + inorder());
            System.out.println("後序遍歷 (Post-order): " + postorder());
            System.out.println("層序遍歷 (Level-order): " + levelOrder());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        emptyTree.printAllTraversals("測試 1：空樹 (Empty Tree)");

        BinaryTree<String> singleNodeTree = new BinaryTree<>(new TreeNode<>("A"));
        singleNodeTree.printAllTraversals("測試 2：單節點樹 (Single Node Tree)");

        TreeNode<String> leftSkewedRoot = new TreeNode<>("A",
            new TreeNode<>("B",
                new TreeNode<>("C"), null), null);
        BinaryTree<String> leftSkewedTree = new BinaryTree<>(leftSkewedRoot);
        leftSkewedTree.printAllTraversals("測試 3：左偏樹 (Left-Skewed Tree)");

        TreeNode<String> completeRoot = new TreeNode<>("A",
            new TreeNode<>("B", new TreeNode<>("D"), new TreeNode<>("E")),
            new TreeNode<>("C", new TreeNode<>("F"), new TreeNode<>("G"))
        );
        BinaryTree<String> completeTree = new BinaryTree<>(completeRoot);
        completeTree.printAllTraversals("測試 4：完整二元樹 (Complete Tree)");
    }
}   