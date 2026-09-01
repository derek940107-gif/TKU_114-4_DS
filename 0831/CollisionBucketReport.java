```java
import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    public static void main(String[] args) {
        int[] keys = {10, 15, -5, 20, 10, -10, 7, 12};
        int bucketCount = 5;

        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int key : keys) {
            int index = Math.floorMod(key, bucketCount);
            buckets.get(index).add(key);
        }

        for (int i = 0; i < bucketCount; i++) {
            List<Integer> bucket = buckets.get(i);
            int collisionCount = Math.max(0, bucket.size() - 1);

            System.out.println(
                "Bucket " + i +
                ": " + bucket +
                ", collisions=" + collisionCount +
                ", chainEnd=" +
                (bucket.isEmpty() ? "empty" : bucket.get(bucket.size() - 1))
            );
        }
    }
}
```
