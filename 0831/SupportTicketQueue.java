```java
import java.util.PriorityQueue;

public class SupportTicketQueue {
    static class Ticket {
        int id;
        int severity;
        int createdOrder;

        Ticket(int id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Ticket> queue = new PriorityQueue<>(
            (a, b) -> {
                if (a.severity != b.severity) {
                    return Integer.compare(b.severity, a.severity);
                }
                return Integer.compare(a.createdOrder, b.createdOrder);
            }
        );

        queue.add(new Ticket(101, 3, 1));
        queue.add(new Ticket(102, 5, 2));
        queue.add(new Ticket(103, 3, 3));
        queue.add(new Ticket(104, 5, 4));
        queue.add(new Ticket(105, 4, 5));

        while (!queue.isEmpty()) {
            Ticket ticket = queue.poll();
            System.out.println(
                ticket.id + "|" +
                ticket.severity + "|" +
                ticket.createdOrder
            );
        }
    }
}
```
