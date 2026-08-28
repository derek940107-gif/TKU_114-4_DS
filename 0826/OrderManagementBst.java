```java
import java.util.ArrayList;
import java.util.List;

public class OrderManagementBst {

    public static class Order {
        private final String orderId;
        private final String customer;
        private final int amount;
        private String status;

        public Order(
                String orderId,
                String customer,
                int amount,
                String status) {

            if (orderId == null || orderId.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (customer == null || customer.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (amount < 0) {
                throw new IllegalArgumentException();
            }

            if (status == null || status.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.orderId = orderId.trim();
            this.customer = customer.trim();
            this.amount = amount;
            this.status = status.trim();
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomer() {
            return customer;
        }

        public int getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        private void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return orderId
                    + "|" + customer
                    + "|" + amount
                    + "|" + status;
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
            int comparison =
                    order.getOrderId()
                            .compareTo(current.order.getOrderId());

            if (comparison == 0) {
                return false;
            }

            if (comparison < 0) {
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

    public Order find(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            return null;
        }

        orderId = orderId.trim();

        Node current = root;

        while (current != null) {
            int comparison =
                    orderId.compareTo(current.order.getOrderId());

            if (comparison == 0) {
                return current.order;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateStatus(
            String orderId,
            String status) {

        if (status == null || status.trim().isEmpty()) {
            return false;
        }

        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        order.setStatus(status.trim());
        return true;
    }

    public boolean cancel(String orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if ("CANCELED".equals(order.getStatus())) {
            return false;
        }

        order.setStatus("CANCELED");
        return true;
    }

    public boolean remove(String orderId) {
        Order order = find(orderId);

        if (order == null
                || !"CANCELED".equals(order.getStatus())) {
            return false;
        }

        root = removeNode(root, orderId.trim());
        size--;
        return true;
    }

    private Node removeNode(Node node, String orderId) {
        if (node == null) {
            return null;
        }

        int comparison =
                orderId.compareTo(node.order.getOrderId());

        if (comparison < 0) {
            node.left = removeNode(node.left, orderId);
        } else if (comparison > 0) {
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

    public List<Order> ordersBetween(
            String lowId,
            String highId) {

        List<Order> result = new ArrayList<>();

        if (lowId == null || highId == null) {
            return result;
        }

        lowId = lowId.trim();
        highId = highId.trim();

        if (lowId.isEmpty()
                || highId.isEmpty()
                || lowId.compareTo(highId) > 0) {
            return result;
        }

        ordersBetween(
                root,
                lowId,
                highId,
                result
        );

        return result;
    }

    private void ordersBetween(
            Node node,
            String lowId,
            String highId,
            List<Order> result) {

        if (node == null) {
            return;
        }

        String orderId = node.order.getOrderId();

        if (orderId.compareTo(lowId) > 0) {
            ordersBetween(
                    node.left,
                    lowId,
                    highId,
                    result
            );
        }

        if (orderId.compareTo(lowId) >= 0
                && orderId.compareTo(highId) <= 0) {
            result.add(node.order);
        }

        if (orderId.compareTo(highId) < 0) {
            ordersBetween(
                    node.right,
                    lowId,
                    highId,
                    result
            );
        }
    }

    public int totalAmount() {
        return totalAmount(root);
    }

    private int totalAmount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.order.getAmount()
                + totalAmount(node.left)
                + totalAmount(node.right);
    }

    public List<Order> report() {
        List<Order> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(
            Node node,
            List<Order> result) {

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

    public static void main(String[] args) {
        OrderManagementBst system =
                new OrderManagementBst();

        system.add(
                new Order(
                        "O300",
                        "Mina",
                        1200,
                        "PAID"
                )
        );

        system.add(
                new Order(
                        "O100",
                        "Leo",
                        800,
                        "NEW"
                )
        );

        system.add(
                new Order(
                        "O500",
                        "Nora",
                        1500,
                        "PAID"
                )
        );

        system.add(
                new Order(
                        "O200",
                        "Ivy",
                        600,
                        "NEW"
                )
        );

        System.out.println(
                system.find("O200")
        );

        System.out.println(
                system.updateStatus(
                        "O200",
                        "PROCESSING"
                )
        );

        System.out.println(
                system.cancel("O200")
        );

        System.out.println(
                system.remove("O200")
        );

        System.out.println(
                system.ordersBetween(
                        "O100",
                        "O500"
                )
        );

        System.out.println(
                system.report()
        );

        System.out.println(
                "totalAmount = "
                        + system.totalAmount()
        );

        System.out.println(
                "size = " + system.size()
        );
    }
}
```
