```java
import java.util.ArrayList;
import java.util.List;

public class CourseBstIndex {

    public static class Course {
        private final String code;
        private final String name;
        private int credits;

        public Course(String code, String name, int credits) {
            if (code == null || code.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.code = code.trim();
            this.name = name.trim();
            this.credits = normalizeCredits(credits);
        }

        private static int normalizeCredits(int credits) {
            if (credits < 1) {
                return 1;
            }

            if (credits > 6) {
                return 6;
            }

            return credits;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = normalizeCredits(credits);
        }

        @Override
        public String toString() {
            return code + "|" + name + "|" + credits;
        }
    }

    private static class Node {
        Course course;
        Node left;
        Node right;

        Node(Course course) {
            this.course = course;
        }
    }

    private Node root;
    private int size;

    public boolean add(Course course) {
        if (course == null) {
            return false;
        }

        if (root == null) {
            root = new Node(course);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            int comparison =
                course.getCode().compareTo(current.course.getCode());

            if (comparison == 0) {
                return false;
            }

            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node(course);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(course);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Course find(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }

        code = code.trim();

        Node current = root;

        while (current != null) {
            int comparison =
                code.compareTo(current.course.getCode());

            if (comparison == 0) {
                return current.course;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateCredits(String code, int credits) {
        Course course = find(code);

        if (course == null) {
            return false;
        }

        course.setCredits(credits);
        return true;
    }

    public boolean remove(String code) {
        if (find(code) == null) {
            return false;
        }

        root = removeNode(root, code.trim());
        size--;
        return true;
    }

    private Node removeNode(Node node, String code) {
        if (node == null) {
            return null;
        }

        int comparison =
            code.compareTo(node.course.getCode());

        if (comparison < 0) {
            node.left = removeNode(node.left, code);
        } else if (comparison > 0) {
            node.right = removeNode(node.right, code);
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

            node.course = successor.course;

            node.right = removeNode(
                node.right,
                successor.course.getCode()
            );
        }

        return node;
    }

    public List<Course> coursesBetween(
            String lowCode,
            String highCode) {

        List<Course> result = new ArrayList<>();

        if (lowCode == null || highCode == null) {
            return result;
        }

        lowCode = lowCode.trim();
        highCode = highCode.trim();

        if (lowCode.isEmpty()
                || highCode.isEmpty()
                || lowCode.compareTo(highCode) > 0) {
            return result;
        }

        coursesBetween(
            root,
            lowCode,
            highCode,
            result
        );

        return result;
    }

    private void coursesBetween(
            Node node,
            String lowCode,
            String highCode,
            List<Course> result) {

        if (node == null) {
            return;
        }

        String code = node.course.getCode();

        if (code.compareTo(lowCode) > 0) {
            coursesBetween(
                node.left,
                lowCode,
                highCode,
                result
            );
        }

        if (code.compareTo(lowCode) >= 0
                && code.compareTo(highCode) <= 0) {
            result.add(node.course);
        }

        if (code.compareTo(highCode) < 0) {
            coursesBetween(
                node.right,
                lowCode,
                highCode,
                result
            );
        }
    }

    public List<Course> report() {
        List<Course> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(
            Node node,
            List<Course> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.course);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        CourseBstIndex index = new CourseBstIndex();

        index.add(
            new Course("CS101", "Programming", 3)
        );

        index.add(
            new Course("DS201", "Data Structure", 4)
        );

        index.add(
            new Course("AI301", "Artificial Intelligence", 7)
        );

        index.add(
            new Course("DB202", "Database", 2)
        );

        System.out.println(
            index.add(
                new Course("CS101", "Duplicate", 3)
            )
        );

        System.out.println(index.find("DS201"));

        System.out.println(
            index.updateCredits("AI301", 6)
        );

        System.out.println(
            index.coursesBetween("CS101", "DS201")
        );

        System.out.println(index.report());

        System.out.println(index.remove("CS101"));

        System.out.println(index.report());

        System.out.println("size = " + index.size());
    }
}
```
