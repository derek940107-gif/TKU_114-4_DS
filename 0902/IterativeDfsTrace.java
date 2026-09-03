import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {
    static List<String> trace(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        ArrayDeque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);
        System.out.println("push " + start + " -> Stack=" + stack + ", visited=" + visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("pop " + current + " -> Stack=" + stack + ", visited=" + visited);

            if (!visited.add(current)) {
                continue;
            }

            result.add(current);

            List<String> neighbors = graph.getOrDefault(current, List.of());

            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String next = neighbors.get(i);

                if (graph.containsKey(next) && !visited.contains(next)) {
                    stack.push(next);
                    System.out.println("push " + next + " -> Stack=" + stack + ", visited=" + visited);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D"));
        graph.put("D", List.of("A"));
        graph.put("E", List.of());

        System.out.println(trace(graph, "A"));
        System.out.println(trace(graph, "E"));
        System.out.println(trace(graph, "X"));
    }
}