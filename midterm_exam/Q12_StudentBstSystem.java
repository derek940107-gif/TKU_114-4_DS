import java.util.ArrayList;
import java.util.List;

public class Q12_StudentBstSystem {
    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0 || name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.id = id;
            this.name = name.trim();
            this.score = Math.max(0, Math.min(100, score));
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        private void setScore(int score) {
            this.score = Math.max(0, Math.min(100, score));
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            return true;
        }

        Node current = root;

        while (true) {
            if (student.getId() == current.student.getId()) {
                return false;
            }

            if (student.getId() < current.student.getId()) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Student find(int id) {
        Node current = root;

        while (current != null) {
            if (id == current.student.getId()) {
                return current.student;
            }

            if (id < current.student.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateScore(int id, int score) {
        Student student = find(id);

        if (student == null) {
            return false;
        }

        student.setScore(score);
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }

        root = removeNode(root, id);
        return true;
    }

    private Node removeNode(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.student.getId()) {
            node.left = removeNode(node.left, id);
        } else if (id > node.student.getId()) {
            node.right = removeNode(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = node.right;

            while (successor.left != null) {
                successor = successor.left;
            }

            node.student = successor.student;
            node.right = removeNode(node.right, successor.student.getId());
        }

        return node;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        // student-index-check S12-88
        List<Student> result = new ArrayList<>();

        if (lowId > highId) {
            return result;
        }

        studentsBetweenRecursive(root, lowId, highId, result);
        return result;
    }

    private void studentsBetweenRecursive(
            Node node, int lowId, int highId, List<Student> result) {

        if (node == null) {
            return;
        }

        int id = node.student.getId();

        if (id > lowId) {
            studentsBetweenRecursive(node.left, lowId, highId, result);
        }

        if (id >= lowId && id <= highId) {
            result.add(node.student);
        }

        if (id < highId) {
            studentsBetweenRecursive(node.right, lowId, highId, result);
        }
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node node, List<Student> result) {
        if (node == null) {
            return;
        }

        inorderRecursive(node.left, result);
        result.add(node.student);
        inorderRecursive(node.right, result);
    }
}