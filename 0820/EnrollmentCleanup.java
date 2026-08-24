import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {

    public static void main(String[] args) {
        List<String> rawEnrollment = new ArrayList<>(Arrays.asList(
            "Alice", "Bob", null, "  ", "Charlie", "Alice",
            "", "David", "Bob", "  Alice  ", "Eve", null
        ));

        System.out.println("=== 清理前原始名單 ===");
        System.out.println("總筆數: " + rawEnrollment.size());
        System.out.println(rawEnrollment);

        List<String> cleanedList = new ArrayList<>();
        Set<String> seenNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        Iterator<String> iterator = rawEnrollment.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();

            if (element == null) {
                iterator.remove();
                continue;
            }

            String trimmed = element.trim();
            if (trimmed.isEmpty()) {
                iterator.remove();
                continue;
            }

            if (seenNames.contains(trimmed)) {
                duplicateNames.add(trimmed);
            } else {
                seenNames.add(trimmed);
                cleanedList.add(trimmed);
            }
        }

        System.out.println("\n=== 重複名稱報告 ===");
        System.out.println("發現重複的姓名: " + duplicateNames);

        System.out.println("\n=== 清理後有效不重複名單 ===");
        System.out.println("有效總筆數: " + cleanedList.size());
        System.out.println(cleanedList);
    }
}