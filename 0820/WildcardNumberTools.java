import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardNumberTools {

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Number number : values) {
            if (number != null) {
                sum += number.doubleValue();
            }
        }
        return sum / values.size();
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = Double.NEGATIVE_INFINITY;
        boolean hasNonNull = false;

        for (Number number : values) {
            if (number != null) {
                double val = number.doubleValue();
                if (val > max) {
                    max = val;
                }
                hasNonNull = true;
            }
        }
        return hasNonNull ? max : Double.NaN;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(10, 20, 30, 40, 50);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5, 4.5);
        List<Integer> emptyList = new ArrayList<>();

        System.out.println("Integer Average: " + average(intList));
        System.out.println("Double Average: " + average(doubleList));
        System.out.println("Empty Average: " + average(emptyList));

        System.out.println("Integer Maximum: " + maximum(intList));
        System.out.println("Double Maximum: " + maximum(doubleList));
        System.out.println("Empty Maximum: " + maximum(emptyList));

        List<Number> numberList = new ArrayList<>();
        addRange(numberList, 1, 5);
        System.out.println("Added Range (1 to 5): " + numberList);

        addRange(numberList, 10, 5);
        System.out.println("Added Range (10 to 5): " + numberList);
    }
}