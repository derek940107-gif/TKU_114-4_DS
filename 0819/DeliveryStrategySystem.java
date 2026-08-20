interface DeliveryMethod {
    double calculateFee(double amount);

    String getInvoiceDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public double calculateFee(double amount) {
        return 100;
    }

    @Override
    public String getInvoiceDescription() {
        return "宅配";
    }
}

class SupermarketPickup implements DeliveryMethod {
    @Override
    public double calculateFee(double amount) {
        return 60;
    }

    @Override
    public String getInvoiceDescription() {
        return "超市取貨";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public double calculateFee(double amount) {
        return 0;
    }

    @Override
    public String getInvoiceDescription() {
        return "自取";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void printOrder(double amount) {
        double deliveryFee = deliveryMethod.calculateFee(amount);
        double total = amount + deliveryFee;

        System.out.println("商品金額：" + amount + " 元");
        System.out.println("運送方式：" + deliveryMethod.getInvoiceDescription());
        System.out.println("運費：" + deliveryFee + " 元");
        System.out.println("總金額：" + total + " 元");
        System.out.println();
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService order = new OrderService(new HomeDelivery());
        order.printOrder(1000);

        order.setDeliveryMethod(new SupermarketPickup());
        order.printOrder(1000);

        order.setDeliveryMethod(new SelfPickup());
        order.printOrder(1000);
    }
}