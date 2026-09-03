import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedReachability {
    static List<Boolean> reachable(Map<String, List<String>> graph,
                                    List<String[]> queries) {
        List<Boolean> result = new ArrayList<>();

        if (graph == null || queries == null) {
            return result;
        }

        for (String[] query : queries) {
            if (query == null || query.length < 2) {
                result.add(false);
                continue;
            }

            String start = query[0];
            String target = query[1];

            if (start == null || target == null
                    || !graph.containsKey(start)
                    || !graph.containsKey(target)) {
                result.add(false);
                continue;
            }

            Queue<String> queue = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();

            queue.offer(start);
            visited.add(start);

            boolean found = false;

            while (!queue.isEmpty()) {
                String current = queue.poll();

                if (current.equals(target)) {
                    found = true;
                    break;
                }

                for (String next : graph.getOrDefault(current, List.of())) {
                    if (graph.containsKey(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            result.add(found);
        }

        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D"));
        graph.put("D", List.of());
        graph.put("E", List.of("F"));
        graph.put("F", List.of());

        List<String[]> queries = List.of(
                new String[]{"A", "D"},
                new String[]{"A", "F"},
                new String[]{"B", "D"},
                new String[]{"D", "A"},
                new String[]{"E", "F"},
                new String[]{"X", "A"}
        );

        System.out.println(reachable(graph, queries));
    }
}