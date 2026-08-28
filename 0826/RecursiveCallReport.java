public class RecursiveCallReport {

    public static int sum(int[] data, int index) {
        if (data == null || index >= data.length) {
            return 0;
        }

        int recursiveResult = sum(data, index + 1);
        int result = data[index] + recursiveResult;

        System.out.println(
            "index=" + index
            + ", value=" + data[index]
            + ", recursiveResult=" + recursiveResult
            + ", return=" + result
        );

        return result;
    }

    public static void main(String[] args) {
        int[] data1 = {10, 20, 30, 40};
        int[] data2 = {99};
        int[] data3 = {};

        System.out.println("一般數組");
        System.out.println("sum = " + sum(data1, 0));

        System.out.println();

        System.out.println("單一元素");
        System.out.println("sum = " + sum(data2, 0));

        System.out.println();

        System.out.println("空數組");
        System.out.println("sum = " + sum(data3, 0));
    }
}