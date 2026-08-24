import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void processList(List<Integer> list) {
        System.out.println("--- 測試列表實現: " + list.getClass().getSimpleName() + " ---");

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println("尾端新增 10, 20, 30, 40 後: " + list);

        list.add(2, 25);
        System.out.println("在索引 2 插入 25 後: " + list);

        int target = 30;
        int index = list.indexOf(target);
        System.out.println("搜尋元素 " + target + " 的索引位置: " + index);

        list.remove(0);
        list.remove(Integer.valueOf(40));
        System.out.println("移除索引 0 與值 40 後: " + list);

        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        System.out.println("目前列表元素總和: " + sum);
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        processList(arrayList);
        processList(linkedList);

        printPerformanceComparison();
    }

    private static void printPerformanceComparison() {
        System.out.println("=== ArrayList 與 LinkedList 內部成本與效能差異說明 ===");
        System.out.println("1. 隨機存取 (Random Access - get/set):");
        System.out.println("   - ArrayList: O(1)。基於連續記憶體陣列，可透過索引直接存取。");
        System.out.println("   - LinkedList: O(n)。基於雙向鏈結串列，必須從頭或尾節點逐一尋訪。");
        System.out.println();
        System.out.println("2. 中間插入與刪除 (Insert/Delete in Middle):");
        System.out.println("   - ArrayList: O(n)。找到位置後，後續元素皆需向前或向後移動 (Array Copy)。");
        System.out.println("   - LinkedList: O(n)。雖變更指標連結為 O(1)，但先尋訪至該節點仍需 O(n)。若已有 Iterator 則為 O(1)。");
        System.out.println();
        System.out.println("3. 尾端新增 (Append):");
        System.out.println("   - ArrayList: 均攤 O(1)。當陣列容量不足時需觸發擴容與複製。");
        System.out.println("   - LinkedList: O(1)。直接更新尾節點 (tail) 的指標即可。");
        System.out.println();
        System.out.println("4. 記憶體開銷 (Memory Overhead):");
        System.out.println("   - ArrayList: 較低。連續記憶體空間，額外開銷僅為預留但未使用的陣列容量。");
        System.out.println("   - LinkedList: 較高。每個元素節點額外包含前後節點 (prev, next) 的物件引用與記憶體標頭。");
    }
}