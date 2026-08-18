public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "王小明");

        Order order = new Order("O001", customer, 3);

        order.addItem(new OrderItem("P001", "鍵盤", 800, 1));
        order.addItem(new OrderItem("P002", "滑鼠", 500, 2));
        order.addItem(new OrderItem("P003", "耳機", 1200, 1));

        System.out.println(order.summary());
    }
}

class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private String productId;
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "品項編號：" + productId
                + "，品項名稱：" + productName
                + "，價格：" + price
                + "，數量：" + quantity;
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;
    private int itemCount;

    public Order(String orderId, Customer customer, int size) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new OrderItem[size];
        this.itemCount = 0;
    }

    public boolean addItem(OrderItem item) {
        if (item == null || itemCount >= items.length) {
            return false;
        }

        items[itemCount] = item;
        itemCount++;
        return true;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String summary() {
        String result = "訂單編號：" + orderId
                + "\n顧客編號：" + customer.getId()
                + "\n顧客姓名：" + customer.getName()
                + "\n品項數量：" + itemCount
                + "\n訂單品項：\n";

        double total = 0;

        for (int i = 0; i < itemCount; i++) {
            result += items[i] + "\n";
            total += items[i].getTotal();
        }

        result += "訂單總金額：" + total;

        return result;
    }
}