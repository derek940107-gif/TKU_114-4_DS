import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }

        return node;
    }

    public List<Integer> valuesBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();

        if (low > high) {
            return result;
        }

        valuesBetween(root, low, high, result);
        return result;
    }

    private void valuesBetween(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        if (node.value > low) {
            valuesBetween(node.left, low, high, result);
        }

        if (node.value >= low && node.value <= high) {
            result.add(node.value);
        }

        if (node.value < high) {
            valuesBetween(node.right, low, high, result);
        }
    }

    public int countBetween(int low, int high) {
        if (low > high) {
            return 0;
        }

        return countBetween(root, low, high);
    }

    private int countBetween(
            Node node,
            int low,
            int high) {

        if (node == null) {
            return 0;
        }

        if (node.value < low) {
            return countBetween(node.right, low, high);
        }

        if (node.value > high) {
            return countBetween(node.left, low, high);
        }

        return 1
                + countBetween(node.left, low, high)
                + countBetween(node.right, low, high);
    }

    public int sumBetween(int low, int high) {
        if (low > high) {
            return 0;
        }

        return sumBetween(root, low, high);
    }

    private int sumBetween(
            Node node,
            int low,
            int high) {

        if (node == null) {
            return 0;
        }

        if (node.value < low) {
            return sumBetween(node.right, low, high);
        }

        if (node.value > high) {
            return sumBetween(node.left, low, high);
        }

        return node.value
                + sumBetween(node.left, low, high)
                + sumBetween(node.right, low, high);
    }

    public static void main(String[] args) {
        BstRangeStatistics tree = new BstRangeStatistics();

        int[] values = {
            50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("valuesBetween(30, 70) = "
                + tree.valuesBetween(30, 70));

        System.out.println("countBetween(30, 70) = "
                + tree.countBetween(30, 70));

        System.out.println("sumBetween(30, 70) = "
                + tree.sumBetween(30, 70));

        System.out.println("valuesBetween(90, 100) = "
                + tree.valuesBetween(90, 100));

        System.out.println("countBetween(90, 100) = "
                + tree.countBetween(90, 100));

        System.out.println("sumBetween(90, 100) = "
                + tree.sumBetween(90, 100));

        System.out.println("valuesBetween(70, 30) = "
                + tree.valuesBetween(70, 30));

        System.out.println("countBetween(70, 30) = "
                + tree.countBetween(70, 30));

        System.out.println("sumBetween(70, 30) = "
                + tree.sumBetween(70, 30));
    }
}