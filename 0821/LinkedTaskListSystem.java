public class LinkedTaskListSystem {

    public static class Task {
        private String id;
        private String title;

        public Task(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Task{id='" + id + "', title='" + title + "'}";
        }
    }

    public static class TaskNode {
        private Task task;
        private TaskNode next;

        public TaskNode(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public static class TaskLinkedList {
        private TaskNode head;
        private int count;

        public TaskLinkedList() {
            this.head = null;
            this.count = 0;
        }

        public boolean addFirst(Task task) {
            if (task == null || containsId(task.getId())) {
                System.out.println("新增失敗（頭部）：任務無效或 ID 重複 -> " + (task != null ? task.getId() : "null"));
                return false;
            }
            TaskNode newNode = new TaskNode(task);
            newNode.next = head;
            head = newNode;
            count++;
            System.out.println("成功新增至頭部: " + task);
            return true;
        }

        public boolean addLast(Task task) {
            if (task == null || containsId(task.getId())) {
                System.out.println("新增失敗（尾部）：任務無效或 ID 重複 -> " + (task != null ? task.getId() : "null"));
                return false;
            }
            TaskNode newNode = new TaskNode(task);
            if (head == null) {
                head = newNode;
            } else {
                TaskNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            count++;
            System.out.println("成功新增至尾部: " + task);
            return true;
        }

        public Task findById(String id) {
            if (id == null) {
                return null;
            }
            TaskNode current = head;
            while (current != null) {
                if (current.task.getId().equals(id)) {
                    System.out.println("找到任務: " + current.task);
                    return current.task;
                }
                current = current.next;
            }
            System.out.println("找不到任務 ID: " + id);
            return null;
        }

        public boolean insertAfter(String existingId, Task task) {
            if (existingId == null || task == null) {
                return false;
            }
            if (containsId(task.getId())) {
                System.out.println("插入失敗：新任務 ID 重複 -> " + task.getId());
                return false;
            }

            TaskNode current = head;
            while (current != null) {
                if (current.task.getId().equals(existingId)) {
                    TaskNode newNode = new TaskNode(task);
                    newNode.next = current.next;
                    current.next = newNode;
                    count++;
                    System.out.println("成功將 " + task + " 插入至 " + existingId + " 之後");
                    return true;
                }
                current = current.next;
            }
            System.out.println("插入失敗：找不到指定的既存 ID -> " + existingId);
            return false;
        }

        public boolean removeById(String id) {
            if (id == null || head == null) {
                System.out.println("刪除失敗：串列為空或 ID 無效 -> " + id);
                return false;
            }

            if (head.task.getId().equals(id)) {
                Task removedTask = head.task;
                head = head.next;
                count--;
                System.out.println("成功刪除頭部任務: " + removedTask);
                return true;
            }

            TaskNode current = head;
            while (current.next != null) {
                if (current.next.task.getId().equals(id)) {
                    Task removedTask = current.next.task;
                    current.next = current.next.next;
                    count--;
                    System.out.println("成功刪除任務: " + removedTask);
                    return true;
                }
                current = current.next;
            }

            System.out.println("刪除失敗：找不到任務 ID -> " + id);
            return false;
        }

        public int size() {
            return count;
        }

        public void printAll() {
            System.out.print("當前任務串列 (大小: " + count + "): ");
            if (head == null) {
                System.out.println("[空串列]");
                return;
            }
            TaskNode current = head;
            while (current != null) {
                System.out.print(current.task + (current.next != null ? " -> " : ""));
                current = current.next;
            }
            System.out.println();
        }

        private boolean containsId(String id) {
            if (id == null) return false;
            TaskNode current = head;
            while (current != null) {
                if (current.task.getId().equals(id)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 測試 1: 空串列操作測試 ===");
        taskList.printAll();
        taskList.findById("T001");
        taskList.removeById("T001");

        System.out.println("\n=== 測試 2: 新增元素 (addFirst / addLast) 與重複 ID 阻擋 ===");
        taskList.addFirst(new Task("T002", "撰寫報告"));
        taskList.addFirst(new Task("T001", "開會準備"));
        taskList.addLast(new Task("T004", "回覆信件"));
        taskList.addFirst(new Task("T001", "重複的T001"));
        taskList.printAll();

        System.out.println("\n=== 測試 3: 指定位置插入 (insertAfter) ===");
        taskList.insertAfter("T002", new Task("T003", "程式審查"));
        taskList.insertAfter("T999", new Task("T005", "找不到的既存ID"));
        taskList.printAll();

        System.out.println("\n=== 測試 4: 搜尋任務 (findById) ===");
        taskList.findById("T003");
        taskList.findById("T999");

        System.out.println("\n=== 測試 5: 刪除中間節點 (remove Middle) ===");
        taskList.removeById("T002");
        taskList.printAll();

        System.out.println("\n=== 測試 6: 刪除頭部節點 (remove Head) ===");
        taskList.removeById("T001");
        taskList.printAll();

        System.out.println("\n=== 測試 7: 刪除尾部節點 (remove Tail) ===");
        taskList.removeById("T004");
        taskList.printAll();

        System.out.println("\n=== 測試 8: 清空剩餘節點與找不到 ID 刪除 ===");
        taskList.removeById("T003");
        taskList.printAll();
        taskList.removeById("T003");
    }
}