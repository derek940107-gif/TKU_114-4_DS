import java.util.ArrayList;
import java.util.List;

public class FolderSizeTree {

    public static class FolderNode {
        private String name;
        private long ownSize;
        private FolderNode left;
        private FolderNode right;

        public FolderNode(String name, long ownSize) {
            this.name = name;
            this.ownSize = ownSize;
            this.left = null;
            this.right = null;
        }

        public FolderNode(String name, long ownSize, FolderNode left, FolderNode right) {
            this.name = name;
            this.ownSize = ownSize;
            this.left = left;
            this.right = right;
        }

        public String getName() {
            return name;
        }

        public long getOwnSize() {
            return ownSize;
        }

        public FolderNode getLeft() {
            return left;
        }

        public void setLeft(FolderNode left) {
            this.left = left;
        }

        public FolderNode getRight() {
            return right;
        }

        public void setRight(FolderNode right) {
            this.right = right;
        }
    }

    public static class TreeStats {
        private FolderNode maxSubtreeNode;
        private long maxSubtreeSize = -1;
    }

    private FolderNode root;

    public FolderSizeTree(FolderNode root) {
        this.root = root;
    }

    public long calculateTotalSize(FolderNode node) {
        if (node == null) {
            return 0;
        }
        long leftTotal = calculateTotalSize(node.left);
        long rightTotal = calculateTotalSize(node.right);
        return node.ownSize + leftTotal + rightTotal;
    }

    public long calculateAndFindMaxSubtree(FolderNode node, TreeStats stats) {
        if (node == null) {
            return 0;
        }
        long leftTotal = calculateAndFindMaxSubtree(node.left, stats);
        long rightTotal = calculateAndFindMaxSubtree(node.right, stats);
        long currentTotal = node.ownSize + leftTotal + rightTotal;

        if (currentTotal > stats.maxSubtreeSize) {
            stats.maxSubtreeSize = currentTotal;
            stats.maxSubtreeNode = node;
        }
        return currentTotal;
    }

    public void collectLeafFolders(FolderNode node, List<FolderNode> leaves) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leaves.add(node);
            return;
        }
        collectLeafFolders(node.left, leaves);
        collectLeafFolders(node.right, leaves);
    }

    public void printReport() {
        System.out.println("=== 目錄大小與結構分析報表 ===");
        if (root == null) {
            System.out.println("目錄樹為空。");
            return;
        }

        long totalSize = calculateTotalSize(root);
        System.out.println("全樹總容量大小: " + totalSize + " KB");

        TreeStats stats = new TreeStats();
        calculateAndFindMaxSubtree(root, stats);
        if (stats.maxSubtreeNode != null) {
            System.out.println("最大子樹目錄: " + stats.maxSubtreeNode.getName() + " (含子目錄總大小: " + stats.maxSubtreeSize + " KB)");
        }

        List<FolderNode> leaves = new ArrayList<>();
        collectLeafFolders(root, leaves);
        System.out.println("葉子資料夾 (無子目錄之資料夾):");
        for (FolderNode leaf : leaves) {
            System.out.println("  - " + leaf.getName() + " (本身大小: " + leaf.getOwnSize() + " KB)");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 空目錄樹 ===");
        FolderSizeTree emptyTree = new FolderSizeTree(null);
        emptyTree.printReport();

        System.out.println("=== 測試 2: 多層級目錄樹 ===");
        FolderNode leafD = new FolderNode("FolderD", 150);
        FolderNode leafE = new FolderNode("FolderE", 200);
        FolderNode leafF = new FolderNode("FolderF", 50);

        FolderNode nodeB = new FolderNode("FolderB", 100, leafD, leafE);
        FolderNode nodeC = new FolderNode("FolderC", 300, leafF, null);

        FolderNode rootA = new FolderNode("RootA", 500, nodeB, nodeC);

        FolderSizeTree tree = new FolderSizeTree(rootA);
        tree.printReport();
    }
}