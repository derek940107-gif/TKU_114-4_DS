import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CollectionChoiceReport {

    public static void scenario1() {
        System.out.println("=== 需求 1：保留搜尋記錄並允許重複 ===");
        System.out.println("【選擇方案】介面: List<String> | 實作: ArrayList<String>");
        System.out.println("【選擇理由】ArrayList 能維護元素插入的先後順序（有序性），且允許重複元素，非常適合記錄歷史操作。\n");

        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java 泛型");
        searchHistory.add("Data Structures");
        searchHistory.add("Java 泛型");
        searchHistory.add("ArrayList vs LinkedList");

        System.out.println("新增搜尋紀錄結果: " + searchHistory);
        System.out.println("取得第 3 筆歷史紀錄: " + searchHistory.get(2));
        System.out.println();
    }

    public static void scenario2() {
        System.out.println("=== 需求 2：儲存不重複的會員號碼 ===");
        System.out.println("【選擇方案】介面: Set<String> | 實作: HashSet<String>");
        System.out.println("【選擇理由】Set 介面能保證元素的唯一性（不重複），HashSet 具備 O(1) 的超高加入與尋找效能。\n");

        Set<String> memberIds = new HashSet<>();
        System.out.println("加入 M001: " + memberIds.add("M001"));
        System.out.println("加入 M002: " + memberIds.add("M002"));
        System.out.println("重複加入 M001: " + memberIds.add("M001"));

        System.out.println("會員號碼集合內容: " + memberIds);
        System.out.println("是否存在 M002: " + memberIds.contains("M002"));
        System.out.println();
    }

    public static void scenario3() {
        System.out.println("=== 需求 3：以學號查詢成績 ===");
        System.out.println("【選擇方案】介面: Map<String, Integer> | 實作: HashMap<String, Integer>");
        System.out.println("【選擇理由】Map 支援 Key-Value 鍵值對對映，以學號（Key）能在 O(1) 時間內直接快速查出成績（Value）。\n");

        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("S101", 85);
        studentScores.put("S102", 92);
        studentScores.put("S103", 78);

        System.out.println("查詢 S102 的成績: " + studentScores.get("S102"));
        System.out.println("更新 S101 的成績為 90");
        studentScores.put("S101", 90);
        System.out.println("所有學生成績 Map 內容: " + studentScores);
        System.out.println();
    }

    public static void scenario4() {
        System.out.println("=== 需求 4：依照順序處理印刷工作 ===");
        System.out.println("【選擇方案】介面: Queue<String> | 實作: LinkedList<String>");
        System.out.println("【選擇理由】Queue 遵循先進先出（FIFO）原則，能完美符合列印工作依送出順序依次處理的需求。\n");

        Queue<String> printQueue = new LinkedList<>();
        printQueue.offer("Doc1_Report.pdf");
        printQueue.offer("Doc2_Invoice.pdf");
        printQueue.offer("Doc3_Slides.pdf");

        System.out.println("列印佇列當前狀態: " + printQueue);
        System.out.println("查看下一個要列印的文件 (peek): " + printQueue.peek());
        System.out.println("處理列印 (poll): " + printQueue.poll());
        System.out.println("處理列印 (poll): " + printQueue.poll());
        System.out.println("剩餘列印佇列: " + printQueue);
        System.out.println();
    }

    public static void scenario5() {
        System.out.println("=== 需求 5：最近多次操作（撤銷/歷史） ===");
        System.out.println("【選擇方案】介面: Deque<String> | 實作: ArrayDeque<String>");
        System.out.println("【選擇理由】Deque（雙端隊列）作為 Stack 使用時支援後進先出（LIFO），ArrayDeque 效能優於 Vector/Stack 類別。\n");

        Deque<String> actionHistory = new ArrayDeque<>();
        actionHistory.push("操作1: 打開文件");
        actionHistory.push("操作2: 輸入內文");
        actionHistory.push("操作3: 修改字型");

        System.out.println("當前操作堆疊 (最新在頂端): " + actionHistory);
        System.out.println("查看最近一次操作 (peek): " + actionHistory.peek());
        System.out.println("撤銷最近一次操作 (pop): " + actionHistory.pop());
        System.out.println("撤銷後剩餘操作堆疊: " + actionHistory);
        System.out.println();
    }

    public static void main(String[] args) {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
        scenario5();
    }
}