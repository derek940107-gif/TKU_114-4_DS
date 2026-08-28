import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> enrollmentMapR26;

    public Q06_EnrollmentIndex() {
        enrollmentMapR26 = new HashMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() ||
            studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students = enrollmentMapR26.computeIfAbsent(
            courseCode, k -> new HashSet<>()
        );

        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() ||
            studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students = enrollmentMapR26.get(courseCode);

        if (students == null || !students.remove(studentId)) {
            return false;
        }

        if (students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }

        return true;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return 0;
        }

        Set<String> students = enrollmentMapR26.get(courseCode.trim());

        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return new ArrayList<>();
        }

        Set<String> students = enrollmentMapR26.get(courseCode.trim());

        if (students == null) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>(students);
        Collections.sort(result);

        return result;
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();

        if (studentId == null || studentId.trim().isEmpty()) {
            return result;
        }

        studentId = studentId.trim();

        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new java.util.TreeMap<>();

        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }

        return result;
    }
}