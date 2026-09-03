import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IntegratedStructureAudit {
    enum Structure {
        LIST,
        QUEUE,
        BST,
        HEAP,
        HASH_TABLE,
        GRAPH
    }

    static class TestCase {
        String test;
        Structure expected;
        Structure actual;

        TestCase(String test, Structure expected, Structure actual) {
            this.test = test;
            this.expected = expected;
            this.actual = actual;
        }
    }

    static String diagnose(TestCase testCase) {
        if (testCase.expected == testCase.actual) {
            return "合理";
        }

        return "不合理，建議改用 " + testCase.expected;
    }

    static String solution(TestCase testCase) {
        if (testCase.expected == testCase.actual) {
            return "維持目前資料結構";
        }

        return "將 " + testCase.actual + " 改為 " + testCase.expected;
    }

    static List<Map<String, String>> audit(List<TestCase> tests) {
        List<Map<String, String>> result = new ArrayList<>();

        for (TestCase testCase : tests) {
            Map<String, String> report = new LinkedHashMap<>();

            report.put("Test", testCase.test);
            report.put("Expected", testCase.expected.toString());
            report.put("Actual", testCase.actual.toString());
            report.put("Diagnosis", diagnose(testCase));
            report.put("Solution", solution(testCase));

            result.add(report);
        }

        return result;
    }

    public static void main(String[] args) {
        List<TestCase> tests = List.of(
                new TestCase("依索引取得第 i 個元素", Structure.LIST, Structure.LIST),
                new TestCase("依序處理先進先出的工作", Structure.QUEUE, Structure.LIST),
                new TestCase("依 key 快速查詢資料", Structure.HASH_TABLE, Structure.HASH_TABLE),
                new TestCase("依排序後的 key 查找資料", Structure.BST, Structure.HASH_TABLE),
                new TestCase("取得最高優先權工作", Structure.HEAP, Structure.HEAP),
                new TestCase("維持元素排序並進行範圍查詢", Structure.BST, Structure.LIST),
                new TestCase("檢查元素是否存在且不允許重複", Structure.HASH_TABLE, Structure.LIST),
                new TestCase("後進先出處理工作", Structure.QUEUE, Structure.QUEUE),
                new TestCase("依優先權反覆取出下一個工作", Structure.HEAP, Structure.QUEUE),
                new TestCase("表示地點與道路關係", Structure.GRAPH, Structure.GRAPH),
                new TestCase("BFS 找最少邊路徑", Structure.GRAPH, Structure.GRAPH),
                new TestCase("DFS 找連通關係", Structure.GRAPH, Structure.HASH_TABLE)
        );

        List<Map<String, String>> reports = audit(tests);

        for (int i = 0; i < reports.size(); i++) {
            System.out.println("Test " + (i + 1));
            System.out.println(reports.get(i));
            System.out.println();
        }
    }
}