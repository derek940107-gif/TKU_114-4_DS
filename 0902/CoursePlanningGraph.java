import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {
    static boolean reachable(Map<String, List<String>> graph,
                             String start, String target) {
        if (graph == null || start == null || target == null
                || !graph.containsKey(start) || !graph.containsKey(target)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        return dfs(graph, start, target, visited);
    }

    static boolean dfs(Map<String, List<String>> graph,
                       String current, String target,
                       Set<String> visited) {
        if (current.equals(target)) {
            return true;
        }

        if (!visited.add(current)) {
            return false;
        }

        for (String next : graph.getOrDefault(current, List.of())) {
            if (graph.containsKey(next) && dfs(graph, next, target, visited)) {
                return true;
            }
        }

        return false;
    }

    static List<List<String>> allRoutes(Map<String, List<String>> graph,
                                        String start, String target) {
        List<List<String>> result = new ArrayList<>();

        if (graph == null || start == null || target == null
                || !graph.containsKey(start) || !graph.containsKey(target)) {
            return result;
        }

        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        path.add(start);
        visited.add(start);

        findRoutes(graph, start, target, visited, path, result);

        return result;
    }

    static void findRoutes(Map<String, List<String>> graph,
                           String current, String target,
                           Set<String> visited,
                           List<String> path,
                           List<List<String>> result) {
        if (current.equals(target)) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (String next : graph.getOrDefault(current, List.of())) {
            if (graph.containsKey(next) && !visited.contains(next)) {
                visited.add(next);
                path.add(next);

                findRoutes(graph, next, target, visited, path, result);

                path.remove(path.size() - 1);
                visited.remove(next);
            }
        }
    }

    static void report(Map<String, List<String>> graph,
                       String start, String target) {
        System.out.println(start + " -> " + target);
        System.out.println("Reachable: " + reachable(graph, start, target));
        System.out.println("Routes: " + allRoutes(graph, start, target));
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("Programming", List.of("Math"));
        graph.put("DataStructure", List.of("Programming"));
        graph.put("Algorithm", List.of("DataStructure", "Math"));
        graph.put("Database", List.of("Programming"));
        graph.put("AI", List.of("Algorithm", "Database"));
        graph.put("Math", List.of());

        report(graph, "AI", "Math");
        System.out.println();

        report(graph, "AI", "Programming");
        System.out.println();

        report(graph, "Database", "Math");
        System.out.println();

        report(graph, "Math", "AI");
    }
}