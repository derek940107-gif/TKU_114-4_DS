```java
import java.util.Arrays;

public class StudentIdHashAnalysis {

    static class HashResult {
        int bucketCount;
        int[] bucketSizes;
        int totalCollisions;
        int maxChainLength;
        double averageChainLength;

        HashResult(int bucketCount, int[] bucketSizes) {
            this.bucketCount = bucketCount;
            this.bucketSizes = bucketSizes;

            int totalEntries = 0;

            for (int count : bucketSizes) {
                totalEntries += count;

                if (count > maxChainLength) {
                    maxChainLength = count;
                }

                if (count > 1) {
                    totalCollisions += count - 1;
                }
            }

            averageChainLength = bucketCount == 0
                    ? 0.0
                    : (double) totalEntries / bucketCount;
        }

        void printReport() {
            System.out.println("桶數：" + bucketCount);
            System.out.println("每個桶筆數：" + Arrays.toString(bucketSizes));
            System.out.println("總碰撞次數：" + totalCollisions);
            System.out.println("最大鍊長：" + maxChainLength);
            System.out.printf("平均鍊長：%.2f%n", averageChainLength);
        }
    }

    public static HashResult analyze(int[] studentIds, int bucketCount) {
        if (studentIds == null) {
            throw new IllegalArgumentException();
        }

        if (bucketCount <= 0) {
            throw new IllegalArgumentException();
        }

        int[] bucketSizes = new int[bucketCount];

        for (int studentId : studentIds) {
            int index = Math.floorMod(studentId, bucketCount);
            bucketSizes[index]++;
        }

        return new HashResult(bucketCount, bucketSizes);
    }

    public static void compare(HashResult first, HashResult second) {
        System.out.println("比較結果");

        if (first.totalCollisions < second.totalCollisions) {
            System.out.println(
                first.bucketCount + " 桶的碰撞次數較少"
            );
        } else if (first.totalCollisions > second.totalCollisions) {
            System.out.println(
                second.bucketCount + " 桶的碰撞次數較少"
            );
        } else {
            System.out.println("兩者碰撞次數相同");
        }

        if (first.maxChainLength < second.maxChainLength) {
            System.out.println(
                first.bucketCount + " 桶的最大鍊較短"
            );
        } else if (first.maxChainLength > second.maxChainLength) {
            System.out.println(
                second.bucketCount + " 桶的最大鍊較短"
            );
        } else {
            System.out.println("兩者最大鍊長相同");
        }
    }

    public static void main(String[] args) {
        int[] studentIds = {
            41100001,
            41100006,
            41100011,
            41100016,
            41100021,
            41100026,
            41100032,
            41100037,
            41100042,
            41100048,
            41100053,
            41100058
        };

        HashResult result1 = analyze(studentIds, 5);
        HashResult result2 = analyze(studentIds, 7);

        result1.printReport();
        System.out.println();

        result2.printReport();
        System.out.println();

        compare(result1, result2);
    }
}
```
