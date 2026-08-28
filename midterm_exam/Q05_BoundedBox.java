import java.util.ArrayList;
import java.util.List;

public class Q05_BoundedBox<T extends Comparable<T>> {
    private final int capacity;
    private final List<T> values;

    public Q05_BoundedBox(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.values = new ArrayList<>();
    }

    public boolean add(T value) {
        if (value == null || isFull()) {
            return false;
        }

        values.add(value);
        return true;
    }

    public int size() {
        return values.size();
    }

    public boolean isFull() {
        return values.size() >= capacity;
    }

    public T minimum() {
        if (values.isEmpty()) {
            return null;
        }

        T min = values.get(0);

        for (T value : values) {
            if (value.compareTo(min) < 0) {
                min = value;
            }
        }

        return min;
    }

    public T maximum() {
        if (values.isEmpty()) {
            return null;
        }

        T max = values.get(0);

        for (T value : values) {
            if (value.compareTo(max) > 0) {
                max = value;
            }
        }

        return max;
    }

    public int countGreaterThan(T threshold) {
        if (threshold == null) {
            return 0;
        }

        int count = 0;

        for (T value : values) {
            if (value.compareTo(threshold) > 0) {
                count++;
            }
        }

        return count;
    }

    public List<T> snapshot() {
        return new ArrayList<>(values);
    }
}