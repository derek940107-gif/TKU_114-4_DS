```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class CourseGradeMap {

    private Map<String, List<Integer>> courses;

    public CourseGradeMap() {
        courses = new HashMap<>();
    }

    public void addGrade(String courseId, int grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }

        courses.computeIfAbsent(courseId, k -> new ArrayList<>()).add(grade);
    }

    public double getAverage(String courseId) {
        List<Integer> grades = courses.get(courseId);

        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        int sum = 0;

        for (int grade : grades) {
            sum += grade;
        }

        return (double) sum / grades.size();
    }

    public int getHighest(String courseId) {
        List<Integer> grades = courses.get(courseId);

        if (grades == null || grades.isEmpty()) {
            return -1;
        }

        int highest = grades.get(0);

        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }

        return highest;
    }

    public void printReport() {
        TreeSet<String> sortedCourses = new TreeSet<>(courses.keySet());

        System.out.println("課程成績統計報告");

        for (String courseId : sortedCourses) {
            List<Integer> grades = courses.get(courseId);

            System.out.printf(
                "%s：人數=%d，平均=%.2f，最高=%d%n",
                courseId,
                grades.size(),
                getAverage(courseId),
                getHighest(courseId)
            );
        }
    }

    public List<Integer> getGrades(String courseId) {
        return courses.getOrDefault(courseId, new ArrayList<>());
    }

    public int getCourseCount() {
        return courses.size();
    }

    public static void main(String[] args) {
        CourseGradeMap map = new CourseGradeMap();

        map.addGrade("CS101", 85);
        map.addGrade("CS101", 92);
        map.addGrade("CS101", 78);

        map.addGrade("DS102", 90);
        map.addGrade("DS102", 88);
        map.addGrade("DS102", 95);

        map.addGrade("AI103", 76);
        map.addGrade("AI103", 84);
        map.addGrade("AI103", 91);

        map.printReport();
    }
}
```
