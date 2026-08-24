public class RecursiveArrayStatistics {

    public static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return maximumHelper(arr, 0);
    }

    private static int maximumHelper(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        return Math.max(arr[index], maximumHelper(arr, index + 1));
    }

    public static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return minimumHelper(arr, 0);
    }

    private static int minimumHelper(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        return Math.min(arr[index], minimumHelper(arr, index + 1));
    }

    public static int countAbove(int[] arr, int threshold) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return countAboveHelper(arr, threshold, 0);
    }

    private static int countAboveHelper(int[] arr, int threshold, int index) {
        if (index == arr.length) {
            return 0;
        }
        int count = (arr[index] > threshold) ? 1 : 0;
        return count + countAboveHelper(arr, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] numbers = {15, 42, -8, 23, 100, 42, 0};

        System.out.println("=== 測試正常陣列 ===");
        System.out.println("最大值 (Maximum): " + maximum(numbers));
        System.out.println("最小值 (Minimum): " + minimum(numbers));
        System.out.println("大於 20 的元素數量 (Count above 20): " + countAbove(numbers, 20));

        System.out.println("\n=== 測試單一元素陣列 ===");
        int[] single = {7};
        System.out.println("最大值: " + maximum(single));
        System.out.println("最小值: " + minimum(single));
        System.out.println("大於 5 的數量: " + countAbove(single, 5));

        System.out.println("\n=== 測試異常狀況 (null 與空數組) ===");
        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("傳入 null 觸發例外: " + e.getMessage());
        }

        try {
            minimum(new int[]{});
        } catch (IllegalArgumentException e) {
            System.out.println("傳入空數組觸發例外: " + e.getMessage());
        }
    }
}