import java.util.ArrayList;
import java.util.List;

public class ScoreRangeBst {

    public static class Student {
        private final int studentId;
        private final String name;
        private final int score;

        public Student(int studentId, String name, int score) {
            this.studentId = studentId;
            this.name = name;
            this.score = score;
        }

        public int getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return score + "|" + studentId + "|" + name;
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
    private int size;

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int comparison = compare(student, current.student);

            if (comparison == 0) {
                return false;
            }

            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node(student);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    private int compare(Student a, Student b) {
        if (a.getScore() != b.getScore()) {
            return Integer.compare(a.getScore(), b.getScore());
        }

        return Integer.compare(a.getStudentId(), b.getStudentId());
    }

    public List<Student> range(int lowScore, int highScore) {
        List<Student> result = new ArrayList<>();

        if (lowScore > highScore) {
            return result;
        }

        range(root, lowScore, highScore, result);
        return result;
    }

    private void range(
            Node node,
            int lowScore,
            int highScore,
            List<Student> result) {

        if (node == null) {
            return;
        }

        int score = node.student.getScore();

        if (score >= lowScore) {
            range(node.left, lowScore, highScore, result);
        }

        if (score >= lowScore && score <= highScore) {
            result.add(node.student);
        }

        if (score <= highScore) {
            range(node.right, lowScore, highScore, result);
        }
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Student> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.student);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ScoreRangeBst tree = new ScoreRangeBst();

        tree.add(new Student(1001, "Amy", 85));
        tree.add(new Student(1002, "Bob", 90));
        tree.add(new Student(1003, "Cindy", 85));
        tree.add(new Student(1004, "David", 70));
        tree.add(new Student(1005, "Eva", 90));

        System.out.println(tree.range(80, 90));
        System.out.println(tree.inorder());
        System.out.println(tree.size());
    }
}