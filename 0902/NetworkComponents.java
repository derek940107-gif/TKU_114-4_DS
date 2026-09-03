import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {
    static List<List<String>> components(Map<String, List<String>> graph) {
        List<List<String>> result = new ArrayList<>();

        if (graph == null) {
            return result;
        }

        Set<String> visited = new HashSet<>();

        for (String start : graph.keySet()) {
            if (visited.contains(start)) {
                continue;
            }

            List<String> component = new ArrayList<>();
            Queue<String> queue = new ArrayDeque<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                component.add(current);

                for (String next : graph.getOrDefault(current, List.of())) {
                    if (graph.containsKey(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            result.add(component);
        }

        return result;
    }

    static void report(Map<String, List<String>> graph) {
        List<List<String>> components = components(graph);

        System.out.println("Components:");

        for (int i = 0; i < components.size(); i++) {
            System.out.println((i + 1) + ": " + components.get(i));
        }

        System.out.println("Component count: " + components.size());

        List<String> largest = List.of();

        for (List<String> component : components) {
            if (component.size() > largest.size()) {
                largest = component;
            }
        }

        System.out.println("Largest component: " + largest);
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", List.of("B"));
        graph.put("B", List.of("A", "C"));
        graph.put("C", List.of("B"));

        graph.put("D", List.of("E"));
        graph.put("E", List.of("D"));

        graph.put("F", List.of());

        report(graph);
    }
}