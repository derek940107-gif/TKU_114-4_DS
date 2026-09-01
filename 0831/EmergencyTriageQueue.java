```java
import java.util.PriorityQueue;
import java.util.NoSuchElementException;

public class EmergencyTriageQueue {

    static class Patient {
        int patientId;
        int severity;
        int arrivalOrder;

        Patient(int patientId, int severity, int arrivalOrder) {
            this.patientId = patientId;
            this.severity = severity;
            this.arrivalOrder = arrivalOrder;
        }
    }

    private final PriorityQueue<Patient> queue;
    private int arrivalOrder = 0;

    public EmergencyTriageQueue() {
        queue = new PriorityQueue<>(
            (a, b) -> {
                if (a.severity != b.severity) {
                    return Integer.compare(b.severity, a.severity);
                }

                if (a.arrivalOrder != b.arrivalOrder) {
                    return Integer.compare(a.arrivalOrder, b.arrivalOrder);
                }

                return Integer.compare(a.patientId, b.patientId);
            }
        );
    }

    public void checkIn(int patientId, int severity) {
        arrivalOrder++;
        queue.offer(new Patient(patientId, severity, arrivalOrder));
    }

    public Patient peekNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return queue.peek();
    }

    public Patient callNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue queue = new EmergencyTriageQueue();

        queue.checkIn(1003, 2);
        queue.checkIn(1001, 3);
        queue.checkIn(1005, 3);
        queue.checkIn(1002, 1);
        queue.checkIn(1004, 3);

        Patient next = queue.peekNext();
        System.out.println(
            "下一位：" +
            next.patientId + "|" +
            next.severity + "|" +
            next.arrivalOrder
        );

        while (!queue.isEmpty()) {
            Patient patient = queue.callNext();

            System.out.println(
                "叫號：" +
                patient.patientId + "|" +
                patient.severity + "|" +
                patient.arrivalOrder
            );
        }

        System.out.println("目前佇列數量：" + queue.size());

        try {
            queue.peekNext();
        } catch (NoSuchElementException e) {
            System.out.println("查看下一位：空佇列");
        }

        try {
            queue.callNext();
        } catch (NoSuchElementException e) {
            System.out.println("叫號：空佇列");
        }
    }
}
```
