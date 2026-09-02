```java
import java.util.HashMap;
import java.util.Map;

public class LogisticsWeightedGraph {

    private Map<String, Map<String, Integer>> graph;

    public LogisticsWeightedGraph() {
        graph = new HashMap<>();
    }

    public void addVertex(String vertex) {
        graph.putIfAbsent(vertex, new HashMap<>());
    }

    public void addEdge(String from, String to, int weight) {
        checkVertex(from);
        checkVertex(to);
        checkWeight(weight);

        graph.get(from).put(to, weight);
    }

    public void updateEdge(String from, String to, int weight) {
        checkVertex(from);
        checkVertex(to);
        checkWeight(weight);

        if (!graph.get(from).containsKey(to)) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        graph.get(from).put(to, weight);
    }

    public void removeEdge(String from, String to) {
        checkVertex(from);
        checkVertex(to);

        if (!graph.get(from).containsKey(to)) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        graph.get(from).remove(to);
    }

    public int getWeight(String from, String to) {
        checkVertex(from);
        checkVertex(to);

        if (!graph.get(from).containsKey(to)) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        return graph.get(from).get(to);
    }

    public boolean hasEdge(String from, String to) {
        checkVertex(from);
        checkVertex(to);

        return graph.get(from).containsKey(to);
    }

    public Map<String, Integer> getOutgoingEdges(String vertex) {
        checkVertex(vertex);

        return new HashMap<>(graph.get(vertex));
    }

    private void checkVertex(String vertex) {
        if (!graph.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
    }

    private void checkWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
    }

    public void printReport() {
        System.out.println("物流成本網路");

        for (String vertex : graph.keySet()) {
            System.out.println(vertex + "：" + graph.get(vertex));
        }
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph graph = new LogisticsWeightedGraph();

        graph.addVertex("台北");
        graph.addVertex("桃園");
        graph.addVertex("新竹");
        graph.addVertex("台中");

        graph.addEdge("台北", "桃園", 120);
        graph.addEdge("桃園", "新竹", 150);
        graph.addEdge("新竹", "台中", 300);
        graph.addEdge("台北", "台中", 500);

        System.out.println(
                "台北 -> 桃園成本：" +
                graph.getWeight("台北", "桃園")
        );

        graph.updateEdge("台北", "桃園", 100);

        System.out.println(
                "更新後台北 -> 桃園成本：" +
                graph.getWeight("台北", "桃園")
        );

        graph.removeEdge("台北", "台中");

        System.out.println(
                "是否存在台北 -> 台中：" +
                graph.hasEdge("台北", "台中")
        );

        graph.printReport();
    }
}
```
