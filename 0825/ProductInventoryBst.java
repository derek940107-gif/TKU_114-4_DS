import java.util.ArrayList;
import java.util.List;

public class ProductInventoryBst {

    public static class Product {
        private final int id;
        private final String name;
        private int stock;

        public Product(int id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = Math.max(stock, 0);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getStock() {
            return stock;
        }

        public boolean restock(int amount) {
            if (amount <= 0) {
                return false;
            }

            stock += amount;
            return true;
        }

        public boolean sell(int amount) {
            if (amount <= 0 || amount > stock) {
                return false;
            }

            stock -= amount;
            return true;
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + stock;
        }
    }

    private static class Node {
        Product product;
        Node left;
        Node right;

        Node(Product product) {
            this.product = product;
        }
    }

    private Node root;
    private int size;

    public boolean add(Product product) {
        if (product == null) {
            return false;
        }

        if (root == null) {
            root = new Node(product);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            if (product.getId() == current.product.getId()) {
                return false;
            }

            if (product.getId() < current.product.getId()) {
                if (current.left == null) {
                    current.left = new Node(product);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(product);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Product find(int id) {
        Node current = root;

        while (current != null) {
            if (id == current.product.getId()) {
                return current.product;
            }

            if (id < current.product.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean restock(int id, int amount) {
        Product product = find(id);

        if (product == null) {
            return false;
        }

        return product.restock(amount);
    }

    public boolean sell(int id, int amount) {
        Product product = find(id);

        if (product == null) {
            return false;
        }

        return product.sell(amount);
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }

        root = removeNode(root, id);
        size--;
        return true;
    }

    private Node removeNode(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.product.getId()) {
            node.left = removeNode(node.left, id);
        } else if (id > node.product.getId()) {
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

            node.product = successor.product;
            node.right = removeNode(
                node.right,
                successor.product.getId()
            );
        }

        return node;
    }

    public List<Product> inorder() {
        List<Product> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Product> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.product);
        inorder(node.right, result);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ProductInventoryBst inventory = new ProductInventoryBst();

        inventory.add(new Product(300, "Keyboard", 10));
        inventory.add(new Product(100, "Mouse", 20));
        inventory.add(new Product(500, "Monitor", 5));
        inventory.add(new Product(200, "Cable", 15));

        System.out.println(inventory.restock(100, 10));
        System.out.println(inventory.sell(300, 3));
        System.out.println(inventory.sell(500, 10));

        System.out.println(inventory.find(100));
        System.out.println(inventory.remove(300));
        System.out.println(inventory.inorder());
        System.out.println(inventory.size());
    }
}