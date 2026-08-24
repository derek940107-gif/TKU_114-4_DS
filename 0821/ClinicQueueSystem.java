import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class ClinicQueueSystem {

    public static class Patient {
        private String id;
        private String name;

        public Patient(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Patient{id='" + id + "', name='" + name + "'}";
        }
    }

    private Deque<Patient> waitingQueue;
    private List<Patient> completedList;

    public ClinicQueueSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.completedList = new ArrayList<>();
    }

    public void register(Patient patient) {
        if (patient == null) {
            return;
        }
        waitingQueue.offerLast(patient);
        System.out.println("掛號成功: " + patient);
    }

    public boolean cancel(String id) {
        if (id == null || waitingQueue.isEmpty()) {
            System.out.println("取消失敗：無效的病歷號或佇列為空 (" + id + ")");
            return false;
        }

        Iterator<Patient> iterator = waitingQueue.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getId().equals(id)) {
                iterator.remove();
                System.out.println("取消掛號成功: " + p);
                return true;
            }
        }

        System.out.println("取消失敗：未找到病歷號 " + id);
        return false;
    }

    public Patient callNext() {
        Patient next = waitingQueue.pollFirst();
        if (next == null) {
            System.out.println("叫號失敗：目前無等候病患");
        } else {
            completedList.add(next);
            System.out.println("叫號成功，正在就診: " + next);
        }
        return next;
    }

    public Patient peekNext() {
        Patient next = waitingQueue.peekFirst();
        if (next == null) {
            System.out.println("查看下一位：目前無等候病患");
        } else {
            System.out.println("下一位候診病患: " + next);
        }
        return next;
    }

    public void printCompletedList() {
        System.out.println("=== 當日已完成看診清單 (總計: " + completedList.size() + " 人) ===");
        for (int i = 0; i < completedList.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + completedList.get(i));
        }
    }

    public void printWaitingQueue() {
        System.out.println("=== 當前候診隊列 (總計: " + waitingQueue.size() + " 人) ===");
        int idx = 1;
        for (Patient p : waitingQueue) {
            System.out.println("  [" + idx++ + "] " + p);
        }
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 測試 1: 空佇列狀態測試 ===");
        clinic.peekNext();
        clinic.callNext();
        clinic.cancel("P001");

        System.out.println("\n=== 測試 2: 病患掛號 (先進先出) ===");
        clinic.register(new Patient("P001", "Alice"));
        clinic.register(new Patient("P002", "Bob"));
        clinic.register(new Patient("P003", "Charlie"));
        clinic.register(new Patient("P004", "David"));
        clinic.printWaitingQueue();

        System.out.println("\n=== 測試 3: 查看與叫號 ===");
        clinic.peekNext();
        clinic.callNext();
        clinic.printWaitingQueue();

        System.out.println("\n=== 測試 4: 取消指定病歷號 ===");
        clinic.cancel("P003");
        clinic.cancel("P999");
        clinic.printWaitingQueue();

        System.out.println("\n=== 測試 5: 依序完成剩餘看診 ===");
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();

        System.out.println("\n=== 測試 6: 輸出當日已看診完成清單 ===");
        clinic.printCompletedList();
    }
}