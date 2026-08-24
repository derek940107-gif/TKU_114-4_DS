import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceCenterWorkflow {

    public static class ServiceTicket {
        private String id;
        private String serviceType;

        public ServiceTicket(String id, String serviceType) {
            this.id = id;
            this.serviceType = serviceType;
        }

        public String getId() {
            return id;
        }

        public String getServiceType() {
            return serviceType;
        }

        @Override
        public String toString() {
            return "ServiceTicket{id='" + id + "', serviceType='" + serviceType + "'}";
        }
    }

    private Map<String, ServiceTicket> ticketMap;
    private Deque<ServiceTicket> waitingQueue;
    private Deque<ServiceTicket> completedStack;
    private Set<String> existingIds;

    public ServiceCenterWorkflow() {
        this.ticketMap = new HashMap<>();
        this.waitingQueue = new ArrayDeque<>();
        this.completedStack = new ArrayDeque<>();
        this.existingIds = new HashSet<>();
    }

    public boolean createTicket(String id, String serviceType) {
        if (id == null || serviceType == null) {
            System.out.println("建立票證失敗：無效的參數");
            return false;
        }
        if (existingIds.contains(id)) {
            System.out.println("建立票證失敗：重複的 ID -> " + id);
            return false;
        }

        ServiceTicket ticket = new ServiceTicket(id, serviceType);
        existingIds.add(id);
        ticketMap.put(id, ticket);
        waitingQueue.offerLast(ticket);
        System.out.println("成功建立票證並加入等待隊列: " + ticket);
        return true;
    }

    public ServiceTicket processNext() {
        ServiceTicket ticket = waitingQueue.pollFirst();
        if (ticket == null) {
            System.out.println("處理失敗：當前無等待處理的票證");
            return null;
        }
        completedStack.push(ticket);
        System.out.println("成功處理票證: " + ticket);
        return ticket;
    }

    public boolean cancelWaiting(String id) {
        if (id == null || !existingIds.contains(id)) {
            System.out.println("取消失敗：找不到票證 ID -> " + id);
            return false;
        }

        ServiceTicket target = ticketMap.get(id);
        if (completedStack.contains(target)) {
            System.out.println("取消失敗：票證已處理完成，無法取消 -> " + id);
            return false;
        }

        if (waitingQueue.remove(target)) {
            ticketMap.remove(id);
            existingIds.remove(id);
            System.out.println("成功取消等待中的票證: " + target);
            return true;
        }

        System.out.println("取消失敗：票證不在等待佇列中 -> " + id);
        return false;
    }

    public boolean undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("撤銷失敗：無已完成的工單可撤銷");
            return false;
        }

        ServiceTicket lastCompleted = completedStack.pop();
        waitingQueue.addFirst(lastCompleted);
        System.out.println("成功撤銷最後完成的工單，放回佇列最前置: " + lastCompleted);
        return true;
    }

    public ServiceTicket findById(String id) {
        if (id == null || !ticketMap.containsKey(id)) {
            System.out.println("查詢結果：找不到 ID -> " + id);
            return null;
        }
        ServiceTicket ticket = ticketMap.get(id);
        System.out.println("查詢結果: " + ticket);
        return ticket;
    }

    public void printSummary() {
        System.out.println("\n----------------- 服務中心系統狀態 -----------------");
        System.out.println("系統總票證數 (Map): " + ticketMap.size());
        System.out.println("等待隊列人數 (Queue): " + waitingQueue.size() + " -> " + waitingQueue);
        System.out.println("已完成數量 (Stack): " + completedStack.size() + " -> " + completedStack);
        System.out.println("----------------------------------------------------\n");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();

        System.out.println("=== 測試 1: 空隊列處理與無效操作 ===");
        center.processNext();
        center.undoLastCompletion();
        center.cancelWaiting("T999");
        center.findById("T999");
        center.printSummary();

        System.out.println("=== 測試 2: 建立票證與測試重複 ID 阻擋 ===");
        center.createTicket("T001", "開戶服務");
        center.createTicket("T002", "存款業務");
        center.createTicket("T003", "貸款諮詢");
        center.createTicket("T001", "重複的T001");
        center.printSummary();

        System.out.println("=== 測試 3: 取消不存在與存在於等待佇列的 ID ===");
        center.cancelWaiting("T999");
        center.cancelWaiting("T002");
        center.printSummary();

        System.out.println("=== 測試 4: 順序處理工單 ===");
        center.processNext();
        center.processNext();
        center.printSummary();

        System.out.println("=== 測試 5: 嘗試取消已完成的工單 ===");
        center.cancelWaiting("T001");

        System.out.println("\n=== 測試 6: 連續兩個撤銷操作 (Undo) ===");
        center.undoLastCompletion();
        center.undoLastCompletion();
        center.undoLastCompletion();
        center.printSummary();

        System.out.println("=== 測試 7: 依 ID 查詢票證 ===");
        center.findById("T001");
        center.findById("T002");
    }
}