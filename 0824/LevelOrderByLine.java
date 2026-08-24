import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderByLine {

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

        public BinaryTree(TreeNode<T> root) {
            this.root = root;
        }

        public void printLevelOrder() {
            if (root == null) {
                System.out.println("樹為空，無節點可輸出。");
                return;
            }

            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.offer(root);

            int level = 1;
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                System.out.print("Level " + level + " (節點數: " + levelSize + "): ");

                for (int i = 0; i < levelSize; i++) {
                    TreeNode<T> current = queue.poll();
                    System.out.print(current.val + " ");

                    if (current.left != null) {
                        queue.offer(current.left);
                    }
                    if (current.right != null) {
                        queue.offer(current.right);
                    }
                }
                System.out.println();
                level++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 空樹 ===");
        BinaryTree<String> emptyTree = new BinaryTree<>(null);
        emptyTree.printLevelOrder();

        System.out.println("\n=== 測試 2: 一般二元樹 ===");
        TreeNode<String> nodeD = new TreeNode<>("D");
        TreeNode<String> nodeE = new TreeNode<>("E");
        TreeNode<String> nodeF = new TreeNode<>("F");
        TreeNode<String> nodeG = new TreeNode<>("G");

        TreeNode<String> nodeB = new TreeNode<>("B", nodeD, nodeE);
        TreeNode<String> nodeC = new TreeNode<>("C", nodeF, nodeG);

        TreeNode<String> rootA = new TreeNode<>("A", nodeB, nodeC);

        BinaryTree<String> tree = new BinaryTree<>(rootA);
        tree.printLevelOrder();
    }
}
