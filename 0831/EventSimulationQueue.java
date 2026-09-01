```java
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    static class Event {
        int id;
        int time;
        String type;
        int sequence;
        boolean cancelled;

        Event(int id, int time, String type, int sequence) {
            this.id = id;
            this.time = time;
            this.type = type;
            this.sequence = sequence;
            this.cancelled = false;
        }

        @Override
        public String toString() {
            return id + "|" + time + "|" + type + "|" + sequence;
        }
    }

    private final PriorityQueue<Event> queue;
    private final Map<Integer, Event> events;
    private int sequence = 0;

    public EventSimulationQueue() {
        queue = new PriorityQueue<>(
            (a, b) -> {
                if (a.time != b.time) {
                    return Integer.compare(a.time, b.time);
                }

                return Integer.compare(a.sequence, b.sequence);
            }
        );

        events = new HashMap<>();
    }

    public void addEvent(int id, int time, String type) {
        sequence++;

        Event event = new Event(id, time, type, sequence);
        queue.offer(event);
        events.put(id, event);
    }

    public boolean cancelEvent(int id) {
        Event event = events.get(id);

        if (event == null || event.cancelled) {
            return false;
        }

        event.cancelled = true;
        return true;
    }

    public void run() {
        while (!queue.isEmpty()) {
            Event event = queue.poll();

            if (event.cancelled) {
                continue;
            }

            System.out.println("執行：" + event);
            events.remove(event.id);
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue simulation = new EventSimulationQueue();

        simulation.addEvent(101, 10, "START");
        simulation.addEvent(102, 5, "LOGIN");
        simulation.addEvent(103, 10, "UPDATE");
        simulation.addEvent(104, 5, "LOGOUT");
        simulation.addEvent(105, 15, "END");

        System.out.println("取消事件 103：" + simulation.cancelEvent(103));
        System.out.println("取消事件 999：" + simulation.cancelEvent(999));

        simulation.run();
    }
}
```
