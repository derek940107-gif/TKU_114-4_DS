import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {
    static List<String> shortestPath(Map<String, List<String>> graph,
                                     String start, String target) {
        if (graph == null || start == null || target == null
                || !graph.containsKey(start) || !graph.containsKey(target)) {
            return List.of();
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(target)) {
                break;
            }

            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(target)) {
            return List.of();
        }

        List<String> path = new ArrayList<>();

        for (String current = target; current != null; current = previous.get(current)) {
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    static int edgeCount(List<String> path) {
        if (path == null || path.size() < 2) {
            return 0;
        }

        return path.size() - 1;
    }

    static void report(Map<String, List<String>> graph,
                       String start, String target) {
        List<String> path = shortestPath(graph, start, target);

        if (path.isEmpty()) {
            System.out.println(start + " -> " + target + ": unreachable");
            return;
        }

        System.out.println(start + " -> " + target);
        System.out.println("Path: " + path);
        System.out.println("Stations: " + path.size());
        System.out.println("Edges: " + edgeCount(path));
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D"));
        graph.put("C", List.of("A", "D"));
        graph.put("D", List.of("B", "C", "E"));
        graph.put("E", List.of("D", "F"));
        graph.put("F", List.of("E"));

        report(graph, "A", "F");
        report(graph, "A", "D");
        report(graph, "A", "A");
        report(graph, "A", "X");
    }
}