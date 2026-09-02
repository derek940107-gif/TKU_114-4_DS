```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {

    private Map<String, Set<String>> nextCourses;
    private Map<String, Set<String>> prerequisites;

    public CourseDependencyGraph() {
        nextCourses = new HashMap<>();
        prerequisites = new HashMap<>();
    }

    public void addCourse(String course) {
        nextCourses.putIfAbsent(course, new HashSet<>());
        prerequisites.putIfAbsent(course, new HashSet<>());
    }

    public void addDependency(String prerequisite, String course) {
        if (prerequisite.equals(course)) {
            return;
        }

        addCourse(prerequisite);
        addCourse(course);

        if (nextCourses.get(prerequisite).add(course)) {
            prerequisites.get(course).add(prerequisite);
        }
    }

    public List<String> getPrerequisites(String course) {
        if (!prerequisites.containsKey(course)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(prerequisites.get(course));
    }

    public List<String> getNextCourses(String course) {
        if (!nextCourses.containsKey(course)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(nextCourses.get(course));
    }

    public int inDegree(String course) {
        if (!prerequisites.containsKey(course)) {
            return 0;
        }

        return prerequisites.get(course).size();
    }

    public int outDegree(String course) {
        if (!nextCourses.containsKey(course)) {
            return 0;
        }

        return nextCourses.get(course).size();
    }

    public void printReport() {
        List<String> courses = new ArrayList<>(nextCourses.keySet());
        courses.sort(String::compareTo);

        for (String course : courses) {
            System.out.println("課程：" + course);
            System.out.println("先決條件：" + getPrerequisites(course));
            System.out.println("後續課程：" + getNextCourses(course));
            System.out.println("進度：" + inDegree(course));
            System.out.println("出度：" + outDegree(course));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();

        graph.addCourse("資料結構");
        graph.addCourse("演算法");
        graph.addCourse("資料庫");
        graph.addCourse("作業系統");
        graph.addCourse("人工智慧");

        graph.addDependency("程式設計", "資料結構");
        graph.addDependency("資料結構", "演算法");
        graph.addDependency("資料結構", "作業系統");
        graph.addDependency("資料庫", "人工智慧");
        graph.addDependency("演算法", "人工智慧");

        graph.printReport();
    }
}
```
