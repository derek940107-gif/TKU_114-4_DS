import java.util.ArrayList;
import java.util.List;

public class OrderBstSystem {

    public static class Order {
        private final int orderId;
        private final String customer;
        private int quantity;

        public Order(int orderId, String customer, int quantity) {
            this.orderId = orderId;
            this.customer = customer;
            this.quantity = Math.max(quantity, 0);
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomer() {
            return customer;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = Math.max(quantity, 0);
        }

        @Override
        public String toString() {
            return orderId + "|" + customer + "|" + quantity;
        }
    }

    private static class Node {
        Order order;
        Node left;
        Node right;

        Node(Order order) {
            this.order = order;
        }
    }

    private Node root;
    private int size;

    public boolean add(Order order) {
        if (order == null) {
            return false;
        }

        if (root == null) {
            root = new Node(order);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            if (order.getOrderId() == current.order.getOrderId()) {
                return false;
            }

            if (order.getOrderId() < current.order.getOrderId()) {
                if (current.left == null) {
                    current.left = new Node(order);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(order);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Order find(int orderId) {
        Node current = root;

        while (current != null) {
            if (orderId == current.order.getOrderId()) {
                return current.order;
            }

            if (orderId < current.order.getOrderId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean cancel(int orderId) {
        if (find(orderId) == null) {
            return false;
        }

        root = removeNode(root, orderId);
        size--;
        return true;
    }

    private Node removeNode(Node node, int orderId) {
        if (node == null) {
            return null;
        }

        if (orderId < node.order.getOrderId()) {
            node.left = removeNode(node.left, orderId);
        } else if (orderId > node.order.getOrderId()) {
            node.right = removeNode(node.right, orderId);
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

            node.order = successor.order;
            node.right = removeNode(
                node.right,
                successor.order.getOrderId()
            );
        }

        return node;
    }

    public boolean updateQuantity(int orderId, int quantity) {
        Order order = find(orderId);

        if (order == null || quantity < 0) {
            return false;
        }

        order.setQuantity(quantity);
        return true;
    }

    public List<Order> rangeReport(int lowId, int highId) {
        List<Order> result = new ArrayList<>();

        if (lowId > highId) {
            return result;
        }

        rangeReport(root, lowId, highId, result);
        return result;
    }

    private void rangeReport(
            Node node,
            int lowId,
            int highId,
            List<Order> result) {

        if (node == null) {
            return;
        }

        int id = node.order.getOrderId();

        if (id > lowId) {
            rangeReport(node.left, lowId, highId, result);
        }

        if (id >= lowId && id <= highId) {
            result.add(node.order);
        }

        if (id < highId) {
            rangeReport(node.right, lowId, highId, result);
        }
    }

    public List<Order> summary() {
        List<Order> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Order> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.order);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public int totalQuantity() {
        return totalQuantity(root);
    }

    private int totalQuantity(Node node) {
        if (node == null) {
            return 0;
        }

        return node.order.getQuantity()
                + totalQuantity(node.left)
                + totalQuantity(node.right);
    }

    public static void main(String[] args) {
        OrderBstSystem system = new OrderBstSystem();

        system.add(new Order(3001, "Amy", 5));
        system.add(new Order(1001, "Bob", 3));
        system.add(new Order(5001, "Cindy", 8));
        system.add(new Order(2001, "David", 4));
        system.add(new Order(4001, "Eva", 6));

        System.out.println(system.find(2001));
        System.out.println(system.updateQuantity(2001, 10));
        System.out.println(system.rangeReport(1500, 4500));
        System.out.println(system.cancel(3001));
        System.out.println(system.summary());
        System.out.println(system.size());
        System.out.println(system.totalQuantity());
    }
}