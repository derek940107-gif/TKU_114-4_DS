```java
import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {

    private String[] stations;
    private int[][] matrix;
    private int edgeCount;

    public MetroMatrixGraph(String[] stations) {
        this.stations = stations.clone();
        this.matrix = new int[stations.length][stations.length];
        this.edgeCount = 0;
    }

    private int getIndex(String station) {
        for (int i = 0; i < stations.length; i++) {
            if (stations[i].equals(station)) {
                return i;
            }
        }

        return -1;
    }

    public void addEdge(String station1, String station2) {
        int a = getIndex(station1);
        int b = getIndex(station2);

        if (a == -1 || b == -1 || a == b) {
            return;
        }

        if (matrix[a][b] == 0) {
            matrix[a][b] = 1;
            matrix[b][a] = 1;
            edgeCount++;
        }
    }

    public List<String> getNeighbors(String station) {
        int index = getIndex(station);
        List<String> result = new ArrayList<>();

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < stations.length; i++) {
            if (matrix[index][i] == 1) {
                result.add(stations[i]);
            }
        }

        return result;
    }

    public int getDegree(String station) {
        int index = getIndex(station);

        if (index == -1) {
            return 0;
        }

        int degree = 0;

        for (int i = 0; i < stations.length; i++) {
            degree += matrix[index][i];
        }

        return degree;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void printReport() {
        System.out.println("捷運矩陣圖");

        for (String station : stations) {
            System.out.println(
                    station +
                    "：鄰站=" + getNeighbors(station) +
                    "，度=" + getDegree(station)
            );
        }

        System.out.println();
        System.out.println("邊數：" + edgeCount);
        System.out.println();
        System.out.println("鄰接矩陣");

        System.out.print("       ");
        for (String station : stations) {
            System.out.printf("%-6s", station);
        }
        System.out.println();

        for (int i = 0; i < stations.length; i++) {
            System.out.printf("%-7s", stations[i]);

            for (int j = 0; j < stations.length; j++) {
                System.out.printf("%-6d", matrix[i][j]);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] stations = {
                "台北",
                "中山",
                "雙連",
                "民權西路",
                "圓山"
        };

        MetroMatrixGraph metro = new MetroMatrixGraph(stations);

        metro.addEdge("台北", "中山");
        metro.addEdge("中山", "雙連");
        metro.addEdge("雙連", "民權西路");
        metro.addEdge("民權西路", "圓山");

        metro.printReport();
    }
}
```
