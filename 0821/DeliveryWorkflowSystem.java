import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DeliveryWorkflowSystem {

    public static class DeliveryItem {
        private String id;
        private String description;

        public DeliveryItem(String id, String description) {
            this.id = id;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "DeliveryItem{id='" + id + "', description='" + description + "'}";
        }
    }

    private Map<String, DeliveryItem> itemMap;
    private Deque<DeliveryItem> waitingQueue;
    private Deque<DeliveryItem> completedStack;

    public DeliveryWorkflowSystem() {
        this.itemMap = new HashMap<>();
        this.waitingQueue = new ArrayDeque<>();
        this.completedStack = new ArrayDeque<>();
    }

    public boolean addItem(String id, String description) {
        if (id == null || description == null || id.trim().isEmpty()) {
            System.out.println("新增失敗：無效的項目資訊");
            return false;
        }
        if (itemMap.containsKey(id)) {
            System.out.println("新增失敗：重複的系列編號 -> " + id);
            return false;
        }

        DeliveryItem item = new DeliveryItem(id, description);
        itemMap.put(id, item);
        waitingQueue.offerLast(item);
        System.out.println("成功新增物流項目至等待隊列: " + item);
        return true;
    }

    public DeliveryItem processNext() {
        DeliveryItem item = waitingQueue.pollFirst();
        if (item == null) {
            System.out.println("處理失敗：等待隊列中無項目");
            return null;
        }
        completedStack.push(item);
        System.out.println("成功處理物流項目: " + item);
        return item;
    }

    public boolean undo() {
        if (completedStack.isEmpty()) {
            System.out.println("撤銷失敗：無已完成的流程可撤銷");
            return false;
        }
        DeliveryItem item = completedStack.pop();
        waitingQueue.addFirst(item);
        System.out.println("成功撤銷最後完成的流程，已將項目放回等待隊列最前置: " + item);
        return true;
    }

    public DeliveryItem findById(String id) {
        if (id == null || !itemMap.containsKey(id)) {
            System.out.println("查詢結果：找不到系列編號 -> " + id);
            return null;
        }
        DeliveryItem item = itemMap.get(id);
        System.out.println("查詢結果: " + item);
        return item;
    }

    public void printStatistics() {
        System.out.println("\n----------------- 物流工作流程統計報告 -----------------");
        System.out.println("總項目數量 (Map 紀錄): " + itemMap.size());
        System.out.println("等待處理數量 (Queue): " + waitingQueue.size() + " -> " + waitingQueue);
        System.out.println("已完成流程數量 (Stack): " + completedStack.size() + " -> " + completedStack);
        System.out.println("------------------------------------------------------\n");
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        System.out.println("=== 測試 1: 空結構狀態測試 ===");
        system.processNext();
        system.undo();
        system.findById("D001");
        system.printStatistics();

        System.out.println("=== 測試 2: 新增物流項目與阻擋重複 ID ===");
        system.addItem("D001", "台北至台中包裹");
        system.addItem("D002", "高雄至花蓮冷藏箱");
        system.addItem("D003", "新竹至桃園快捷件");
        system.addItem("D001", "重複的D001項目");
        system.printStatistics();

        System.out.println("=== 測試 3: 依系列編號查詢 ===");
        system.findById("D002");
        system.findById("D999");

        System.out.println("\n=== 測試 4: 順序處理流程 (FIFO) ===");
        system.processNext();
        system.processNext();
        system.printStatistics();

        System.out.println("=== 測試 5: 撤銷操作 (LIFO 放回隊列首位) ===");
        system.undo();
        system.printStatistics();

        System.out.println("=== 測試 6: 重新處理與完全清空 ===");
        system.processNext();
        system.processNext();
        system.printStatistics();
    }
}