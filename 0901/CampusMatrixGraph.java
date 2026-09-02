```java
import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {

    private int[][] matrix;
    private int vertices;
    private int edgeCount;

    public CampusMatrixGraph(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException("Vertices must be positive");
        }

        this.vertices = vertices;
        this.matrix = new int[vertices][vertices];
        this.edgeCount = 0;
    }

    public void addEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        if (from == to) {
            return;
        }

        if (matrix[from][to] == 0) {
            matrix[from][to] = 1;
            matrix[to][from] = 1;
            edgeCount++;
        }
    }

    public void removeEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        if (matrix[from][to] == 1) {
            matrix[from][to] = 0;
            matrix[to][from] = 0;
            edgeCount--;
        }
    }

    public int degree(int vertex) {
        checkVertex(vertex);

        int count = 0;

        for (int i = 0; i < vertices; i++) {
            count += matrix[vertex][i];
        }

        return count;
    }

    public List<Integer> neighbors(int vertex) {
        checkVertex(vertex);

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            if (matrix[vertex][i] == 1) {
                result.add(i);
            }
        }

        return result;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public boolean hasEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        return matrix[from][to] == 1;
    }

    private void checkVertex(int vertex) {
        if (vertex < 0 || vertex >= vertices) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }
    }

    public static void main(String[] args) {
        CampusMatrixGraph graph = new CampusMatrixGraph(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        graph.addEdge(0, 1);

        System.out.println("0 的度數：" + graph.degree(0));
        System.out.println("0 的鄰居：" + graph.neighbors(0));
        System.out.println("總邊數：" + graph.edgeCount());

        graph.removeEdge(0, 2);

        System.out.println("刪除 (0,2) 後");
        System.out.println("0 的度數：" + graph.degree(0));
        System.out.println("0 的鄰居：" + graph.neighbors(0));
        System.out.println("總邊數：" + graph.edgeCount());
    }
}
```
