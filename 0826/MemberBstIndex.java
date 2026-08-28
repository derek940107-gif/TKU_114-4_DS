```java
import java.util.ArrayList;
import java.util.List;

public class MemberBstIndex {

    public static class Member {
        private final int memberId;
        private final String name;
        private String email;

        public Member(int memberId, String name, String email) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        public int getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            if (email == null || email.trim().isEmpty()) {
                return;
            }

            this.email = email;
        }

        @Override
        public String toString() {
            return memberId + "|" + name + "|" + email;
        }
    }

    private static class Node {
        Member member;
        Node left;
        Node right;

        Node(Member member) {
            this.member = member;
        }
    }

    private Node root;
    private int size;

    public boolean add(Member member) {
        if (member == null) {
            return false;
        }

        if (root == null) {
            root = new Node(member);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            if (member.getMemberId() == current.member.getMemberId()) {
                return false;
            }

            if (member.getMemberId() < current.member.getMemberId()) {
                if (current.left == null) {
                    current.left = new Node(member);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(member);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Member find(int memberId) {
        Node current = root;

        while (current != null) {
            if (memberId == current.member.getMemberId()) {
                return current.member;
            }

            if (memberId < current.member.getMemberId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateEmail(int memberId, String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        Member member = find(memberId);

        if (member == null) {
            return false;
        }

        member.setEmail(email);
        return true;
    }

    public boolean remove(int memberId) {
        if (find(memberId) == null) {
            return false;
        }

        root = removeNode(root, memberId);
        size--;
        return true;
    }

    private Node removeNode(Node node, int memberId) {
        if (node == null) {
            return null;
        }

        if (memberId < node.member.getMemberId()) {
            node.left = removeNode(node.left, memberId);
        } else if (memberId > node.member.getMemberId()) {
            node.right = removeNode(node.right, memberId);
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

            node.member = successor.member;
            node.right = removeNode(
                node.right,
                successor.member.getMemberId()
            );
        }

        return node;
    }

    public List<Member> report() {
        List<Member> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(
            Node node,
            List<Member> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.member);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MemberBstIndex index = new MemberBstIndex();

        index.add(new Member(3001, "Amy", "amy@example.com"));
        index.add(new Member(1001, "Bob", "bob@example.com"));
        index.add(new Member(5001, "Cindy", "cindy@example.com"));
        index.add(new Member(2001, "David", "david@example.com"));
        index.add(new Member(4001, "Eva", "eva@example.com"));

        System.out.println(
            index.add(
                new Member(1001, "Tom", "tom@example.com")
            )
        );

        System.out.println(index.find(2001));

        System.out.println(
            index.updateEmail(
                2001,
                "newdavid@example.com"
            )
        );

        System.out.println(index.remove(3001));

        System.out.println(index.report());

        System.out.println(index.size());
    }
}
```
