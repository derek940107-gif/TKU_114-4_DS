import java.util.Arrays;
import java.util.Objects;

public class GenericArrayTools {

    public static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (T item : data) {
            if (Objects.equals(item, target)) {
                count++;
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null || data.length == 0) {
            return;
        }
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) {
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] fruits = {"apple", "banana", "apple", "orange", null};
        System.out.println("Matches for 'apple': " + countMatches(fruits, "apple"));
        System.out.println("Matches for null: " + countMatches(fruits, null));
        System.out.println("Last item: " + last(fruits));

        swap(fruits, 0, 3);
        System.out.println("After swapping index 0 and 3: " + Arrays.toString(fruits));

        Integer[] emptyArray = new Integer[0];
        System.out.println("Last of empty array: " + last(emptyArray));
        swap(emptyArray, 0, 1);

        System.out.println("Count in null array: " + countMatches(null, "test"));
    }
}