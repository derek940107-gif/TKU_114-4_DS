import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {
    static class ServiceRequest {
        String id;
        String description;
        int priority;

        ServiceRequest(String id, String description, int priority) {
            this.id = id;
            this.description = description;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return id + ":" + description + "(priority=" + priority + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof ServiceRequest)) return false;
            ServiceRequest other = (ServiceRequest) obj;
            return id.equals(other.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    static class RequestSystem {
        private final Map<String, ServiceRequest> requests = new HashMap<>();

        private final PriorityQueue<ServiceRequest> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt((ServiceRequest r) -> r.priority)
                        .reversed()
                        .thenComparing(r -> r.id));

        void add(ServiceRequest request) {
            if (request == null || request.id == null) {
                return;
            }

            cancel(request.id);

            requests.put(request.id, request);
            priorityQueue.offer(request);
        }

        ServiceRequest find(String id) {
            return requests.get(id);
        }

        ServiceRequest processNext() {
            ServiceRequest request = priorityQueue.poll();

            if (request == null) {
                return null;
            }

            requests.remove(request.id);
            return request;
        }

        boolean cancel(String id) {
            ServiceRequest request = requests.remove(id);

            if (request == null) {
                return false;
            }

            priorityQueue.remove(request);
            return true;
        }

        int size() {
            return requests.size();
        }

        int queueSize() {
            return priorityQueue.size();
        }

        void report() {
            System.out.println("HashMap: " + requests);
            System.out.println("PriorityQueue: " + priorityQueue);
            System.out.println("HashMap size: " + requests.size());
            System.out.println("PriorityQueue size: " + priorityQueue.size());
        }
    }

    public static void main(String[] args) {
        RequestSystem system = new RequestSystem();

        system.add(new ServiceRequest("R001", "Network problem", 3));
        system.add(new ServiceRequest("R002", "Printer problem", 1));
        system.add(new ServiceRequest("R003", "Server problem", 5));
        system.add(new ServiceRequest("R004", "Account problem", 2));

        system.report();

        System.out.println();

        System.out.println("Find R003: " + system.find("R003"));

        System.out.println();

        System.out.println("Cancel R002: " + system.cancel("R002"));
        system.report();

        System.out.println();

        System.out.println("Process: " + system.processNext());
        system.report();

        System.out.println();

        System.out.println("Cancel R004: " + system.cancel("R004"));
        system.report();
    }
}   