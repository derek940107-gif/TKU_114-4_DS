import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {

    public static void main(String[] args) {
        String[] rawData = {
            "Java", "Data Structures", "Algorithms", "Java",
            "Database", "Data Structures", "Java", "Web Development"
        };

        List<String> tagList = new ArrayList<>();
        Set<String> tagSet = new HashSet<>();
        Map<String, Integer> tagMap = new HashMap<>();

        for (String tag : rawData) {
            tagList.add(tag);
            tagSet.add(tag);
            tagMap.put(tag, tagMap.getOrDefault(tag, 0) + 1);
        }

        System.out.println("=== 課程標籤數據報告 ===");
        System.out.println("1. 原始序列 (List): " + tagList);
        System.out.println("2. 不重複標籤 (Set): " + tagSet);
        System.out.println("3. 標籤出現次數 (Map):");
        for (Map.Entry<String, Integer> entry : tagMap.entrySet()) {
            System.out.println("   - " + entry.getKey() + ": " + entry.getValue() + " 次");
        }

        System.out.println("\n=== 各資料結構用途說明 ===");
        System.out.println("- List<String> (ArrayList): 保持元素的插入順序與完整歷程，允許重複值，適合用來記錄原始輸入流或時間序列資料。");
        System.out.println("- Set<String> (HashSet): 自動去重且提供高效率的檢索速度，適合用來快速過濾重複項目、獲取唯一的標籤清單或檢查特定元素是否存在。");
        System.out.println("- Map<String, Integer> (HashMap): 以鍵值對 (Key-Value) 儲存資料， Key 保持唯一性，適合用於頻率統計、快速鍵值查表與彙總分類。");
    }
}