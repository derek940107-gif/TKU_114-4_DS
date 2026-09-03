import java.util.LinkedHashMap;
import java.util.Map;

public class DataStructureDecisionReport {
    enum Requirement {
        INDEX_ACCESS,
        FIFO,
        LIFO,
        KEY_LOOKUP,
        SORTED_LOOKUP,
        SORTED_RANGE,
        NEXT_PRIORITY,
        UNIQUE_ELEMENTS,
        INSERT_DELETE,
        RELATION_TRAVERSAL,
        SHORTEST_PATH,
        CONNECTED_COMPONENTS
    }

    static String choose(Requirement requirement) {
        return switch (requirement) {
            case INDEX_ACCESS -> "ArrayList";
            case FIFO -> "ArrayDeque as Queue";
            case LIFO -> "ArrayDeque as Stack";
            case KEY_LOOKUP -> "HashMap";
            case SORTED_LOOKUP -> "TreeMap";
            case SORTED_RANGE -> "TreeMap";
            case NEXT_PRIORITY -> "PriorityQueue";
            case UNIQUE_ELEMENTS -> "HashSet";
            case INSERT_DELETE -> "LinkedList";
            case RELATION_TRAVERSAL -> "Graph adjacency list";
            case SHORTEST_PATH -> "Queue + adjacency list";
            case CONNECTED_COMPONENTS -> "Queue + adjacency list";
        };
    }

    static String reason(Requirement requirement) {
        return switch (requirement) {
            case INDEX_ACCESS -> "需要依索引快速存取元素";
            case FIFO -> "需要先進先出";
            case LIFO -> "需要後進先出";
            case KEY_LOOKUP -> "需要依 key 快速查找";
            case SORTED_LOOKUP -> "需要依排序後的 key 查找";
            case SORTED_RANGE -> "需要維持排序並支援範圍查詢";
            case NEXT_PRIORITY -> "需要快速取得最高或最低優先權元素";
            case UNIQUE_ELEMENTS -> "需要儲存不重複元素";
            case INSERT_DELETE -> "需要頻繁插入與刪除";
            case RELATION_TRAVERSAL -> "需要表示頂點與鄰接關係";
            case SHORTEST_PATH -> "無權圖需要逐層探索以找最短路徑";
            case CONNECTED_COMPONENTS -> "需要逐一探索並找出各連通分量";
        };
    }

    static String bigO(Requirement requirement) {
        return switch (requirement) {
            case INDEX_ACCESS -> "Access: O(1)";
            case FIFO -> "Enqueue/Dequeue: O(1)";
            case LIFO -> "Push/Pop: O(1)";
            case KEY_LOOKUP -> "Average Lookup: O(1)";
            case SORTED_LOOKUP -> "Lookup: O(log n)";
            case SORTED_RANGE -> "Lookup: O(log n), Range: O(log n + k)";
            case NEXT_PRIORITY -> "Insert/Poll: O(log n)";
            case UNIQUE_ELEMENTS -> "Average Add/Contains: O(1)";
            case INSERT_DELETE -> "Insert/Delete: O(1)";
            case RELATION_TRAVERSAL -> "Traversal: O(V + E)";
            case SHORTEST_PATH -> "BFS: O(V + E)";
            case CONNECTED_COMPONENTS -> "BFS/DFS: O(V + E)";
        };
    }

    static Map<String, String> report(Requirement requirement) {
        Map<String, String> result = new LinkedHashMap<>();

        result.put("Requirement", requirement.toString());
        result.put("Choice", choose(requirement));
        result.put("Reason", reason(requirement));
        result.put("Big-O", bigO(requirement));

        return result;
    }

    public static void main(String[] args) {
        for (Requirement requirement : Requirement.values()) {
            System.out.println(report(requirement));
        }
    }
}