public class RecursiveDigitReport {

    public static int digitSum(int n) {
        n = Math.abs(n);
        if (n < 10) {
            return n;
        }
        return (n % 10) + digitSum(n / 10);
    }

    public static int digitCount(int n) {
        n = Math.abs(n);
        if (n < 10) {
            return 1;
        }
        return 1 + digitCount(n / 10);
    }

    public static int countDigit(int n, int target) {
        n = Math.abs(n);
        if (n < 10) {
            return (n == target) ? 1 : 0;
        }
        int currentDigit = n % 10;
        int match = (currentDigit == target) ? 1 : 0;
        return match + countDigit(n / 10, target);
    }

    public static void test(int n, int targetDigit) {
        System.out.println("=== 測試數值: " + n + " ===");
        System.out.println("數字位數總和 (digitSum): " + digitSum(n));
        System.out.println("總位數 (digitCount): " + digitCount(n));
        System.out.println("數字 " + targetDigit + " 出現次數 (countDigit): " + countDigit(n, targetDigit));
        System.out.println();
    }

    public static void main(String[] args) {
        test(50205, 0);
        test(0, 0);
        test(-731, 3);
    }
}