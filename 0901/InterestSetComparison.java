```java
import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    public static Set<String> union(Set<String> first, Set<String> secondary) {
        Set<String> result = new HashSet<>(first);
        result.addAll(secondary);
        return result;
    }

    public static Set<String> intersection(Set<String> first, Set<String> secondary) {
        Set<String> result = new HashSet<>(first);
        result.retainAll(secondary);
        return result;
    }

    public static Set<String> firstOnly(Set<String> first, Set<String> secondary) {
        Set<String> result = new HashSet<>(first);
        result.removeAll(secondary);
        return result;
    }

    public static Set<String> secondaryOnly(Set<String> first, Set<String> secondary) {
        Set<String> result = new HashSet<>(secondary);
        result.removeAll(first);
        return result;
    }

    public static void main(String[] args) {
        Set<String> first = new HashSet<>();
        first.add("籃球");
        first.add("電影");
        first.add("音樂");
        first.add("程式設計");

        Set<String> secondary = new HashSet<>();
        secondary.add("電影");
        secondary.add("音樂");
        secondary.add("攝影");
        secondary.add("旅行");

        System.out.println("並集：" + union(first, secondary));
        System.out.println("交集：" + intersection(first, secondary));
        System.out.println("first-only：" + firstOnly(first, secondary));
        System.out.println("secondary-only：" + secondaryOnly(first, secondary));
    }
}
```
