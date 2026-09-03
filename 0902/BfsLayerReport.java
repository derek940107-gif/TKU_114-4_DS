import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BfsLayerReport {
    static Map<String, Integer> distances(Map<String, List<String>> graph, String start) {
        Map<String, Integer> result = new LinkedHashMap<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        queue.offer(start);
        visited.add(start);
        result.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = result.get(current);

            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    result.put(next, currentDistance + 1);
                    queue.offer(next);
                }
            }
        }

        for (String vertex : graph.keySet()) {
            result.putIfAbsent(vertex, -1);
        }

        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D"));
        graph.put("C", List.of("A", "D"));
        graph.put("D", List.of("B", "C"));
        graph.put("E", List.of());

        System.out.println(distances(graph, "A"));
        System.out.println(distances(graph, "E"));
        System.out.println(distances(graph, "X"));
    }
}