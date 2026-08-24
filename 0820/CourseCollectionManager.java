import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CourseCollectionManager {

    public static class Enrollment implements Comparable<Enrollment> {
        private String studentId;
        private String courseCode;
        private int score;
        private Set<String> tags;

        public Enrollment(String studentId, String courseCode, int score, Set<String> tags) {
            this.studentId = studentId;
            this.courseCode = courseCode;
            this.score = score;
            this.tags = new HashSet<>();
            if (tags != null) {
                for (String tag : tags) {
                    if (tag != null) {
                        String trimmed = tag.trim();
                        if (!trimmed.isEmpty()) {
                            this.tags.add(trimmed);
                        }
                    }
                }
            }
        }

        public String getStudentId() {
            return studentId;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Set<String> getTags() {
            return tags;
        }

        public String getGrade() {
            if (score >= 90) return "A";
            if (score >= 80) return "B";
            if (score >= 70) return "C";
            if (score >= 60) return "D";
            return "F";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Enrollment that = (Enrollment) o;
            return Objects.equals(studentId, that.studentId) &&
                   Objects.equals(courseCode, that.courseCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseCode);
        }

        @Override
        public int compareTo(Enrollment other) {
            int scoreCompare = Integer.compare(other.score, this.score);
            if (scoreCompare != 0) {
                return scoreCompare;
            }
            int studentCompare = this.studentId.compareTo(other.studentId);
            if (studentCompare != 0) {
                return studentCompare;
            }
            return this.courseCode.compareTo(other.courseCode);
        }

        @Override
        public String toString() {
            return String.format("Enrollment{studentId='%s', courseCode='%s', score=%d, grade='%s', tags=%s}",
                    studentId, courseCode, score, getGrade(), tags);
        }
    }

    public static class CourseManager {
        private List<Enrollment> list;
        private Set<Enrollment> set;
        private Map<String, Enrollment> map;

        public CourseManager() {
            this.list = new ArrayList<>();
            this.set = new HashSet<>();
            this.map = new HashMap<>();
        }

        private String makeKey(String studentId, String courseCode) {
            return studentId + ":" + courseCode;
        }

        public boolean add(Enrollment enrollment) {
            if (enrollment == null) {
                return false;
            }
            String key = makeKey(enrollment.getStudentId(), enrollment.getCourseCode());
            if (map.containsKey(key)) {
                return false;
            }
            list.add(enrollment);
            set.add(enrollment);
            map.put(key, enrollment);
            return true;
        }

        public boolean updateScore(String studentId, int courseCode, int score) {
            return updateScore(studentId, String.valueOf(courseCode), score);
        }

        public boolean updateScore(String studentId, String courseCode, int score) {
            String key = makeKey(studentId, courseCode);
            Enrollment enrollment = map.get(key);
            if (enrollment == null) {
                return false;
            }
            enrollment.setScore(score);
            return true;
        }

        public List<Enrollment> findByTag(String tag) {
            List<Enrollment> result = new ArrayList<>();
            if (tag == null || tag.trim().isEmpty()) {
                return result;
            }
            String targetTag = tag.trim();
            for (Enrollment enrollment : list) {
                if (enrollment.getTags().contains(targetTag)) {
                    result.add(enrollment);
                }
            }
            return result;
        }

        public Map<String, Integer> scoreDistribution() {
            Map<String, Integer> distribution = new HashMap<>();
            distribution.put("A", 0);
            distribution.put("B", 0);
            distribution.put("C", 0);
            distribution.put("D", 0);
            distribution.put("F", 0);

            for (Enrollment enrollment : list) {
                String grade = enrollment.getGrade();
                distribution.put(grade, distribution.get(grade) + 1);
            }
            return distribution;
        }

        public List<Enrollment> top(int count) {
            List<Enrollment> sortedList = new ArrayList<>(list);
            Collections.sort(sortedList);

            if (count <= 0) {
                return new ArrayList<>();
            }
            if (count >= sortedList.size()) {
                return sortedList;
            }
            return sortedList.subList(0, count);
        }

        public void removeBelow(int minimum) {
            Iterator<Enrollment> iterator = list.iterator();
            while (iterator.hasNext()) {
                Enrollment enrollment = iterator.next();
                if (enrollment.getScore() < minimum) {
                    String key = makeKey(enrollment.getStudentId(), enrollment.getCourseCode());
                    map.remove(key);
                    set.remove(enrollment);
                    iterator.remove();
                }
            }
        }

        public void printAll() {
            System.out.println("=== 目前系統資料清單 (筆數: " + list.size() + ") ===");
            for (Enrollment e : list) {
                System.out.println(e);
            }
        }

        public boolean checkConsistency() {
            return list.size() == set.size() && list.size() == map.size();
        }
    }

    public static void main(String[] args) {
        CourseManager manager = new CourseManager();

        System.out.println("=== 測試 1: 建立並加入 6 筆測試資料 ===");
        
        Set<String> t1 = new HashSet<>(List.of("Required", " CS ", ""));
        Set<String> t2 = new HashSet<>(List.of("Elective", "CS"));
        Set<String> t3 = new HashSet<>(List.of("Required", "Math", "  "));
        Set<String> t4 = new HashSet<>(List.of("Elective", " CS "));
        Set<String> t5 = new HashSet<>(List.of("Required", "Math"));
        Set<String> t6 = new HashSet<>(List.of("Elective", "CS"));

        System.out.println("加入 S101 CS101 (85分): " + manager.add(new Enrollment("S101", "CS101", 85, t1)));
        System.out.println("加入 S101 CS102 (92分): " + manager.add(new Enrollment("S101", "CS102", 92, t2))); // 同學號不同課
        System.out.println("加入 S102 CS101 (58分): " + manager.add(new Enrollment("S102", "CS101", 58, t3)));
        System.out.println("加入 S103 CS101 (85分): " + manager.add(new Enrollment("S103", "CS101", 85, t4))); // 同分
        System.out.println("加入 S104 CS101 (72分): " + manager.add(new Enrollment("S104", "CS101", 72, t5)));
        System.out.println("加入 S105 CS101 (45分): " + manager.add(new Enrollment("S105", "CS101", 45, t6)));
        System.out.println("重複加入 S101 CS101: " + manager.add(new Enrollment("S101", "CS101", 90, t1))); // 應失敗

        manager.printAll();

        System.out.println("\n=== 測試 2: 更新成績 (updateScore) ===");
        System.out.println("更新 S102 CS101 為 65 分: " + manager.updateScore("S102", "CS101", 65));
        System.out.println("更新不存在記錄 (S999 CS101): " + manager.updateScore("S999", "CS101", 100));

        System.out.println("\n=== 測試 3: 依標籤查詢 (findByTag) ===");
        System.out.println("標籤為 'CS' 的課程報名:");
        for (Enrollment e : manager.findByTag("CS")) {
            System.out.println(" - " + e);
        }

        System.out.println("\n=== 測試 4: 成績等第分佈 (scoreDistribution) ===");
        Map<String, Integer> dist = manager.scoreDistribution();
        System.out.println("成績分佈統計: " + dist);

        System.out.println("\n=== 測試 5: 排名前 N 名 (top) ===");
        System.out.println("前 3 名:");
        for (Enrollment e : manager.top(3)) {
            System.out.println(" - " + e);
        }
        System.out.println("請求 10 名 (超過總筆數，回傳全部): " + manager.top(10).size() + " 筆");

        System.out.println("\n=== 測試 6: 安全移除低於指定分數之資料 (removeBelow) ===");
        System.out.println("移除分數小於 60 的資料 (移除前筆數: " + manager.list.size() + ")");
        manager.removeBelow(60);
        manager.printAll();

        System.out.println("\n一致性檢查: List, Set, Map 筆數是否均一致? " + manager.checkConsistency());
    }
}