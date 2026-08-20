interface PricingPolicy {
    double calculatePrice(double originalPrice);
}

class OriginalPricePolicy implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0) {
            return 0;
        }

        return originalPrice;
    }
}

class VipPricingPolicy implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0) {
            originalPrice = 0;
        }

        return originalPrice * 0.85;
    }
}

class FullDiscountPricingPolicy implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0) {
            originalPrice = 0;
        }

        if (originalPrice >= 2000) {
            return originalPrice - 300;
        }

        return originalPrice;
    }
}

interface NotificationChannel {
    boolean send(String orderId, String message);
}

class EmailNotification implements NotificationChannel {
    @Override
    public boolean send(String orderId, String message) {
        System.out.println("Email通知：" + orderId + " - " + message);
        return true;
    }
}

class SmsNotification implements NotificationChannel {
    @Override
    public boolean send(String orderId, String message) {
        System.out.println("簡訊通知：" + orderId + " - " + message);
        return true;
    }
}

class ConsoleNotification implements NotificationChannel {
    @Override
    public boolean send(String orderId, String message) {
        System.out.println("控制台通知：" + orderId + " - " + message);
        return true;
    }
}

class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notificationSent;

    public CheckoutResult(String orderId, double originalPrice,
                           double finalPrice, boolean notificationSent) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationSent = notificationSent;
    }

    public void printResult() {
        System.out.println("訂單號碼：" + orderId);
        System.out.println("原價：" + originalPrice);
        System.out.println("最終價格：" + finalPrice);
        System.out.println("通知狀態：" +
                (notificationSent ? "成功" : "失敗"));
        System.out.println();
    }
}

class CheckoutService {
    private PricingPolicy pricingPolicy;
    private NotificationChannel notificationChannel;

    public CheckoutService(PricingPolicy pricingPolicy,
                           NotificationChannel notificationChannel) {
        this.pricingPolicy = pricingPolicy;
        this.notificationChannel = notificationChannel;
    }

    public CheckoutResult checkout(String orderId, double originalPrice) {
        double finalPrice = pricingPolicy.calculatePrice(originalPrice);

        String message = "訂單完成，付款金額：" + finalPrice + " 元";

        boolean notificationSent =
                notificationChannel.send(orderId, message);

        return new CheckoutResult(
                orderId,
                originalPrice,
                finalPrice,
                notificationSent
        );
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {

        PricingPolicy original = new OriginalPricePolicy();
        PricingPolicy vip = new VipPricingPolicy();
        PricingPolicy discount = new FullDiscountPricingPolicy();

        NotificationChannel email = new EmailNotification();
        NotificationChannel sms = new SmsNotification();
        NotificationChannel console = new ConsoleNotification();

        CheckoutService test1 =
                new CheckoutService(original, email);
        test1.checkout("A001", 1000).printResult();

        CheckoutService test2 =
                new CheckoutService(vip, sms);
        test2.checkout("A002", 1000).printResult();

        CheckoutService test3 =
                new CheckoutService(discount, console);
        test3.checkout("A003", 2500).printResult();

        CheckoutService test4 =
                new CheckoutService(original, sms);
        test4.checkout("A004", 3000).printResult();

        CheckoutService test5 =
                new CheckoutService(vip, email);
        test5.checkout("A005", 3000).printResult();

        CheckoutService test6 =
                new CheckoutService(discount, email);
        test6.checkout("A006", 1500).printResult();
    }
}