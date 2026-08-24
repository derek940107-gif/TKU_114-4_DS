import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class TraversalTestReport {

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
    }

    public static void runTestCase(
            String caseName,
            BinaryTree<String> tree,
            List<String> expPre,
            List<String> expIn,
            List<String> expPost,
            List<String> expLevel) {

        System.out.println("==================================================");
        System.out.println("測試案例: " + caseName);
        System.out.println("==================================================");

        List<String> actPre = tree.preorder();
        List<String> actIn = tree.inorder();
        List<String> actPost = tree.postorder();
        List<String> actLevel = tree.levelOrder();

        verifyAndPrint("前序遍歷 (Pre-order)  ", expPre, actPre);
        verifyAndPrint("中序遍歷 (In-order)   ", expIn, actIn);
        verifyAndPrint("後序遍歷 (Post-order) ", expPost, actPost);
        verifyAndPrint("層序遍歷 (Level-order)", expLevel, actLevel);
        System.out.println();
    }

    private static void verifyAndPrint(String label, List<String> expected, List<String> actual) {
        boolean isMatch = Objects.equals(expected, actual);
        String status = isMatch ? "【相符 (PASS)】" : "【不相符 (FAIL)】";
        System.out.println(label + " -> " + status);
        System.out.println("  預期結果: " + expected);
        System.out.println("  實際結果: " + actual);
    }

    public static void main(String[] args) {

        BinaryTree<String> emptyTree = new BinaryTree<>();
        runTestCase(
                "1. 空樹 (Empty Tree)",
                emptyTree,
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
        );

        BinaryTree<String> singleNodeTree = new BinaryTree<>(new TreeNode<>("A"));
        runTestCase(
                "2. 單節點樹 (Single Node)",
                singleNodeTree,
                Arrays.asList("A"),
                Arrays.asList("A"),
                Arrays.asList("A"),
                Arrays.asList("A")
        );

        TreeNode<String> leftSkewedRoot = new TreeNode<>("A",
                new TreeNode<>("B",
                        new TreeNode<>("C"), null), null);
        BinaryTree<String> leftSkewedTree = new BinaryTree<>(leftSkewedRoot);
        runTestCase(
                "3. 唯左樹 (Left-Skewed Tree)",
                leftSkewedTree,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("C", "B", "A"),
                Arrays.asList("C", "B", "A"),
                Arrays.asList("A", "B", "C")
        );

        TreeNode<String> rightSkewedRoot = new TreeNode<>("A", null,
                new TreeNode<>("B", null,
                        new TreeNode<>("C")));
        BinaryTree<String> rightSkewedTree = new BinaryTree<>(rightSkewedRoot);
        runTestCase(
                "4. 唯右樹 (Right-Skewed Tree)",
                rightSkewedTree,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("A", "B", "C"),
                Arrays.asList("C", "B", "A"),
                Arrays.asList("A", "B", "C")
        );

        TreeNode<String> completeRoot = new TreeNode<>("1",
                new TreeNode<>("2", new TreeNode<>("4"), new TreeNode<>("5")),
                new TreeNode<>("3", new TreeNode<>("6"), null)
        );
        BinaryTree<String> completeTree = new BinaryTree<>(completeRoot);
        runTestCase(
                "5. 完全樹 (Complete Tree)",
                completeTree,
                Arrays.asList("1", "2", "4", "5", "3", "6"),
                Arrays.asList("4", "2", "5", "1", "6", "3"),
                Arrays.asList("4", "5", "2", "6", "3", "1"),
                Arrays.asList("1", "2", "3", "4", "5", "6")
        );

        TreeNode<String> irregularRoot = new TreeNode<>("A",
                new TreeNode<>("B",
                        null,
                        new TreeNode<>("D", new TreeNode<>("E"), null)),
                new TreeNode<>("C",
                        new TreeNode<>("F"),
                        null)
        );
        BinaryTree<String> irregularTree = new BinaryTree<>(irregularRoot);
        runTestCase(
                "6. 不規則樹 (Irregular Tree)",
                irregularTree,
                Arrays.asList("A", "B", "D", "E", "C", "F"),
                Arrays.asList("B", "E", "D", "A", "F", "C"),
                Arrays.asList("E", "D", "B", "F", "C", "A"),
                Arrays.asList("A", "B", "C", "D", "F", "E")
        );
    }
}