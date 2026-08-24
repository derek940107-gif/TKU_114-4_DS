import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrganizationTreeReport {

    public static class OrgNode {
        private String name;
        private List<OrgNode> children;

        public OrgNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<OrgNode> getChildren() {
            return children;
        }

        public void addChild(OrgNode child) {
            if (child != null) {
                this.children.add(child);
            }
        }
    }

    public static class OrganizationTree {
        private OrgNode root;

        public OrganizationTree(OrgNode root) {
            this.root = root;
        }

        public String findParent(String targetName) {
            if (targetName == null || root == null || root.name.equals(targetName)) {
                return null;
            }
            OrgNode parentNode = findParentHelper(root, targetName);
            return parentNode != null ? parentNode.name : null;
        }

        private OrgNode findParentHelper(OrgNode current, String targetName) {
            for (OrgNode child : current.children) {
                if (child.name.equals(targetName)) {
                    return current;
                }
                OrgNode res = findParentHelper(child, targetName);
                if (res != null) {
                    return res;
                }
            }
            return null;
        }

        public int findDepth(String targetName) {
            if (targetName == null || root == null) {
                return -1;
            }
            return findDepthHelper(root, targetName, 0);
        }

        private int findDepthHelper(OrgNode current, String targetName, int depth) {
            if (current.name.equals(targetName)) {
                return depth;
            }
            for (OrgNode child : current.children) {
                int res = findDepthHelper(child, targetName, depth + 1);
                if (res != -1) {
                    return res;
                }
            }
            return -1;
        }

        public List<String> pathFromRoot(String targetName) {
            List<String> path = new ArrayList<>();
            if (targetName == null || root == null) {
                return path;
            }
            pathFromRootHelper(root, targetName, path);
            return path;
        }

        private boolean pathFromRootHelper(OrgNode current, String targetName, List<String> path) {
            path.add(current.name);
            if (current.name.equals(targetName)) {
                return true;
            }
            for (OrgNode child : current.children) {
                if (pathFromRootHelper(child, targetName, path)) {
                    return true;
                }
            }
            path.remove(path.size() - 1);
            return false;
        }

        public void printByLevel() {
            if (root == null) {
                System.out.println("組織架構樹為空。");
                return;
            }
            System.out.println("=== 逐層組織架構報表 ===");
            Queue<OrgNode> queue = new LinkedList<>();
            queue.offer(root);

            int level = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                System.out.print("Level " + level + ": ");
                for (int i = 0; i < size; i++) {
                    OrgNode current = queue.poll();
                    System.out.print(current.name + " ");
                    for (OrgNode child : current.children) {
                        queue.offer(child);
                    }
                }
                System.out.println();
                level++;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        OrgNode ceo = new OrgNode("總經理");

        OrgNode techDept = new OrgNode("技術部");
        OrgNode devTeam = new OrgNode("開發組");
        OrgNode qaTeam = new OrgNode("測試組");
        techDept.addChild(devTeam);
        techDept.addChild(qaTeam);

        OrgNode hrDept = new OrgNode("人力資源部");
        OrgNode recruitTeam = new OrgNode("招募組");
        hrDept.addChild(recruitTeam);

        OrgNode salesDept = new OrgNode("業務部");

        ceo.addChild(techDept);
        ceo.addChild(hrDept);
        ceo.addChild(salesDept);

        OrganizationTree orgTree = new OrganizationTree(ceo);

        orgTree.printByLevel();

        System.out.println("=== 查詢測試 ===");
        System.out.println("'開發組' 的上級單位: " + orgTree.findParent("開發組"));
        System.out.println("'總經理' 的上級單位: " + orgTree.findParent("總經理"));
        System.out.println("'不存在部門' 的上級單位: " + orgTree.findParent("不存在部門"));
        System.out.println("null 的上級單位: " + orgTree.findParent(null));

        System.out.println("\n'測試組' 的深度: " + orgTree.findDepth("測試組"));
        System.out.println("'總經理' 的深度: " + orgTree.findDepth("總經理"));
        System.out.println("'不存在部門' 的深度: " + orgTree.findDepth("不存在部門"));

        System.out.println("\n從根節點到 '招募組' 的路徑: " + orgTree.pathFromRoot("招募組"));
        System.out.println("從根節點到 '不存在部門' 的路徑: " + orgTree.pathFromRoot("不存在部門"));
        System.out.println("從根節點到 null 的路徑: " + orgTree.pathFromRoot(null));

        System.out.println("\n=== 空樹測試 ===");
        OrganizationTree emptyTree = new OrganizationTree(null);
        emptyTree.printByLevel();
        System.out.println("空樹查詢上級: " + emptyTree.findParent("開發組"));
        System.out.println("空樹查詢深度: " + emptyTree.findDepth("開發組"));
        System.out.println("空樹查詢路徑: " + emptyTree.pathFromRoot("開發組"));
    }
}