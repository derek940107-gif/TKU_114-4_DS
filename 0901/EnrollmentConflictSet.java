```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EnrollmentConflictSet {

    private Set<String> enrollments;
    private Set<String> duplicates;
    private Map<String, Set<String>> studentCourses;
    private Map<String, Integer> courseStudents;

    public EnrollmentConflictSet() {
        enrollments = new HashSet<>();
        duplicates = new HashSet<>();
        studentCourses = new HashMap<>();
        courseStudents = new HashMap<>();
    }

    private String createKey(String studentId, String courseId) {
        return studentId + "#" + courseId;
    }

    public void addEnrollment(String studentId, String courseId) {
        String key = createKey(studentId, courseId);

        if (!enrollments.add(key)) {
            duplicates.add(key);
            return;
        }

        studentCourses
                .computeIfAbsent(studentId, k -> new HashSet<>())
                .add(courseId);

        courseStudents.put(
                courseId,
                courseStudents.getOrDefault(courseId, 0) + 1
        );
    }

    public Set<String> getDuplicates() {
        return new HashSet<>(duplicates);
    }

    public Set<String> getStudentCourses(String studentId) {
        return new HashSet<>(
                studentCourses.getOrDefault(studentId, new HashSet<>())
        );
    }

    public int getCourseStudentCount(String courseId) {
        return courseStudents.getOrDefault(courseId, 0);
    }

    public void printDuplicateReport() {
        System.out.println("重複選課記錄");

        if (duplicates.isEmpty()) {
            System.out.println("沒有重複記錄");
            return;
        }

        for (String record : duplicates) {
            String[] data = record.split("#");
            System.out.println(
                    "學號：" + data[0] + "，課號：" + data[1]
            );
        }
    }

    public void printStudentReport() {
        System.out.println("每人課程集合");

        for (Map.Entry<String, Set<String>> entry : studentCourses.entrySet()) {
            System.out.println(
                    entry.getKey() + "：" + entry.getValue()
            );
        }
    }

    public void printCourseReport() {
        System.out.println("每門課修課人數");

        for (Map.Entry<String, Integer> entry : courseStudents.entrySet()) {
            System.out.println(
                    entry.getKey() + "：" + entry.getValue() + " 人"
            );
        }
    }

    public static void main(String[] args) {
        EnrollmentConflictSet system = new EnrollmentConflictSet();

        system.addEnrollment("A001", "CS101");
        system.addEnrollment("A001", "CS102");
        system.addEnrollment("A001", "CS101");

        system.addEnrollment("A002", "CS101");
        system.addEnrollment("A002", "CS103");
        system.addEnrollment("A002", "CS103");

        system.addEnrollment("A003", "CS102");
        system.addEnrollment("A003", "CS103");
        system.addEnrollment("A003", "CS101");

        system.printDuplicateReport();
        System.out.println();

        system.printStudentReport();
        System.out.println();

        system.printCourseReport();
    }
}
```
