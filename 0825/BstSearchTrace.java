import java.util.Scanner;

public class BstSearchTrace {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public boolean search(int target) {
        Node current = root;
        int count = 0;

        while (current != null) {
            count++;

            if (target == current.value) {
                System.out.println(
                    "目前值: " + current.value +
                    ", 方向: FOUND" +
                    ", 比較計數: " + count
                );
                return true;
            }

            if (target < current.value) {
                System.out.println(
                    "目前值: " + current.value +
                    ", 方向: LEFT" +
                    ", 比較計數: " + count
                );
                current = current.left;
            } else {
                System.out.println(
                    "目前值: " + current.value +
                    ", 方向: RIGHT" +
                    ", 比較計數: " + count
                );
                current = current.right;
            }
        }

        System.out.println(
            "目前值: null" +
            ", 方向: NOT FOUND" +
            ", 比較計數: " + count
        );

        return false;
    }

    public static void main(String[] args) {
        BstSearchTrace tree = new BstSearchTrace();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values) {
            tree.add(value);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入搜尋值: ");
        int target = scanner.nextInt();

        boolean found = tree.search(target);

        System.out.println("搜尋結果: " + found);

        scanner.close();
    }
}