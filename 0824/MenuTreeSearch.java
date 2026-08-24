import java.util.ArrayList;
import java.util.List;

public class MenuTreeSearch {

    public static class MenuNode {
        private String name;
        private List<MenuNode> children;

        public MenuNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<MenuNode> getChildren() {
            return children;
        }

        public void addChild(MenuNode child) {
            this.children.add(child);
        }
    }

    public static class MenuTree {
        private MenuNode root;

        public MenuTree(MenuNode root) {
            this.root = root;
        }

        public boolean contains(String targetName) {
            if (targetName == null || root == null) {
                return false;
            }
            return containsHelper(root, targetName);
        }

        private boolean containsHelper(MenuNode node, String targetName) {
            if (node.name.equals(targetName)) {
                return true;
            }
            for (MenuNode child : node.children) {
                if (containsHelper(child, targetName)) {
                    return true;
                }
            }
            return false;
        }

        public int findDepth(String targetName) {
            if (targetName == null || root == null) {
                return -1;
            }
            return findDepthHelper(root, targetName, 0);
        }

        private int findDepthHelper(MenuNode node, String targetName, int currentDepth) {
            if (node.name.equals(targetName)) {
                return currentDepth;
            }
            for (MenuNode child : node.children) {
                int depth = findDepthHelper(child, targetName, currentDepth + 1);
                if (depth != -1) {
                    return depth;
                }
            }
            return -1;
        }

        public int countLeaves() {
            if (root == null) {
                return 0;
            }
            return countLeavesHelper(root);
        }

        private int countLeavesHelper(MenuNode node) {
            if (node.children.isEmpty()) {
                return 1;
            }
            int totalLeaves = 0;
            for (MenuNode child : node.children) {
                totalLeaves += countLeavesHelper(child);
            }
            return totalLeaves;
        }

        public void printPreOrder() {
            if (root == null) {
                System.out.println("選單樹為空");
                return;
            }
            System.out.println("=== 預序遍歷 (Pre-Order) 選單結構 ===");
            printPreOrderHelper(root, 0);
            System.out.println();
        }

        private void printPreOrderHelper(MenuNode node, int depth) {
            StringBuilder indent = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                indent.append("  ");
            }
            System.out.println(indent.toString() + "- " + node.name);
            for (MenuNode child : node.children) {
                printPreOrderHelper(child, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("主選單");

        MenuNode systemSetting = new MenuNode("系統設定");
        MenuNode userMgmt = new MenuNode("帳號管理");
        MenuNode roleMgmt = new MenuNode("權限設定");
        systemSetting.addChild(userMgmt);
        systemSetting.addChild(roleMgmt);

        MenuNode productMgmt = new MenuNode("商品管理");
        MenuNode categoryMgmt = new MenuNode("分類管理");
        MenuNode itemList = new MenuNode("商品列表");
        productMgmt.addChild(categoryMgmt);
        productMgmt.addChild(itemList);

        MenuNode orderMgmt = new MenuNode("訂單管理");

        root.addChild(systemSetting);
        root.addChild(productMgmt);
        root.addChild(orderMgmt);

        MenuTree menuTree = new MenuTree(root);

        menuTree.printPreOrder();

        System.out.println("包含 '商品列表': " + menuTree.contains("商品列表"));
        System.out.println("包含 '財務報表': " + menuTree.contains("財務報表"));

        System.out.println("'主選單' 深度: " + menuTree.findDepth("主選單"));
        System.out.println("'系統設定' 深度: " + menuTree.findDepth("系統設定"));
        System.out.println("'權限設定' 深度: " + menuTree.findDepth("權限設定"));
        System.out.println("'不存在項目' 深度: " + menuTree.findDepth("不存在項目"));

        System.out.println("葉子節點 (功能項目) 總數: " + menuTree.countLeaves());
    }
}