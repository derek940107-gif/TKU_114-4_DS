import java.util.ArrayList;
import java.util.List;

public class StudentBstIndex {

    public static class Student {
        private final int studentId;
        private final String name;

        public Student(int studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }

        public int getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return studentId + "|" + name;
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
            int currentId = current.student.getStudentId();
            int newId = student.getStudentId();

            if (newId == currentId) {
                return false;
            }

            if (newId < currentId) {
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

    public Student find(int studentId) {
        Node current = root;

        while (current != null) {
            int currentId = current.student.getStudentId();

            if (studentId == currentId) {
                return current.student;
            }

            if (studentId < currentId) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean remove(int studentId) {
        if (find(studentId) == null) {
            return false;
        }

        root = removeNode(root, studentId);
        size--;
        return true;
    }

    private Node removeNode(Node node, int studentId) {
        if (node == null) {
            return null;
        }

        int currentId = node.student.getStudentId();

        if (studentId < currentId) {
            node.left = removeNode(node.left, studentId);
        } else if (studentId > currentId) {
            node.right = removeNode(node.right, studentId);
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
            node.right = removeNode(
                node.right,
                successor.student.getStudentId()
            );
        }

        return node;
    }

    public int size() {
        return size;
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

    public static void main(String[] args) {
        StudentBstIndex tree = new StudentBstIndex();

        tree.add(new Student(3001, "Amy"));
        tree.add(new Student(1001, "Bob"));
        tree.add(new Student(5001, "Cindy"));
        tree.add(new Student(2001, "David"));
        tree.add(new Student(4001, "Eva"));

        System.out.println(tree.add(new Student(1001, "Tom")));
        System.out.println(tree.find(2001));
        System.out.println(tree.find(9999));

        System.out.println(tree.remove(3001));
        System.out.println(tree.inorder());
        System.out.println(tree.size());
    }
}