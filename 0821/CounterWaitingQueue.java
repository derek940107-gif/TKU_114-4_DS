import java.util.ArrayDeque;
import java.util.Deque;

public class CounterWaitingQueue {

    public static class Customer {
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

        @Override
        public String toString() {
            return "Customer{id='" + id + "', name='" + name + "'}";
        }
    }

    private Deque<Customer> queue;

    public CounterWaitingQueue() {
        this.queue = new ArrayDeque<>();
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            return;
        }
        queue.offerLast(customer);
        System.out.println("顧客加入隊列: " + customer);
    }

    public Customer peekNext() {
        Customer next = queue.peekFirst();
        if (next == null) {
            System.out.println("查看下一位：目前無等候顧客");
        } else {
            System.out.println("下一位等候顧客: " + next);
        }
        return next;
    }

    public Customer serveNext() {
        Customer served = queue.pollFirst();
        if (served == null) {
            System.out.println("服務下一位：目前無等候顧客可服務");
        } else {
            System.out.println("正在服務顧客: " + served);
        }
        return served;
    }

    public int getWaitingCount() {
        int size = queue.size();
        System.out.println("目前等候人數: " + size);
        return size;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        System.out.println("=== 櫃檯等候隊列測試 ===");

        counter.peekNext();
        counter.serveNext();
        counter.getWaitingCount();

        counter.addCustomer(new Customer("C01", "Alice"));
        counter.addCustomer(new Customer("C02", "Bob"));
        counter.addCustomer(new Customer("C03", "Charlie"));

        counter.getWaitingCount();
        counter.peekNext();

        counter.serveNext();
        counter.getWaitingCount();

        counter.serveNext();
        counter.serveNext();
        counter.getWaitingCount();

        counter.serveNext();
    }
}