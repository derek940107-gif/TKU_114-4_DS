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

public class CampusNavigationSystem {
    static Map<String, List<String>> campus = new LinkedHashMap<>();

    static void addLocation(String name) {
        campus.putIfAbsent(name, new ArrayList<>());
    }

    static void addPath(String from, String to) {
        if (!campus.containsKey(from) || !campus.containsKey(to)) {
            return;
        }

        campus.get(from).add(to);
        campus.get(to).add(from);
    }

    static List<String> shortestPath(String start, String target) {
        if (!campus.containsKey(start) || !campus.containsKey(target)) {
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

            for (String next : campus.getOrDefault(current, List.of())) {
                if (visited.add(next)) {
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

    static void navigate(String start, String target) {
        List<String> path = shortestPath(start, target);

        if (path.isEmpty()) {
            System.out.println(start + " -> " + target + ": unreachable");
            return;
        }

        System.out.println("Start: " + start);
        System.out.println("Target: " + target);
        System.out.println("Path: " + path);
        System.out.println("Minimum edges: " + (path.size() - 1));
    }

    public static void main(String[] args) {
        addLocation("Library");
        addLocation("Cafeteria");
        addLocation("Science");
        addLocation("Engineering");
        addLocation("Gym");
        addLocation("Dorm");
        addLocation("Parking");

        addPath("Library", "Cafeteria");
        addPath("Library", "Science");
        addPath("Cafeteria", "Engineering");
        addPath("Science", "Engineering");
        addPath("Engineering", "Gym");
        addPath("Gym", "Dorm");
        addPath("Parking", "Dorm");

        System.out.println("Campus Map:");
        System.out.println(campus);

        System.out.println();

        navigate("Library", "Dorm");
        System.out.println();

        navigate("Library", "Gym");
        System.out.println();

        navigate("Library", "Parking");
        System.out.println();

        navigate("Library", "Library");
    }
}