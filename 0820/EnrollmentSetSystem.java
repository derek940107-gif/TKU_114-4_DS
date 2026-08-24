import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EnrollmentSetSystem {

    public static class Enrollment {
        private String studentId;
        private String courseCode;

        public Enrollment(String studentId, String courseCode) {
            this.studentId = studentId;
            this.courseCode = courseCode;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getCourseCode() {
            return courseCode;
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
        public String toString() {
            return "Enrollment{studentId='" + studentId + "', courseCode='" + courseCode + "'}";
        }
    }

    public static void main(String[] args) {
        Set<Enrollment> enrollmentSet = new HashSet<>();

        System.out.println("=== 測試新增報名紀錄 ===");
        
        Enrollment e1 = new Enrollment("S101", "CS101");
        boolean added1 = enrollmentSet.add(e1);
        System.out.println("新增 S101 報名 CS101: " + added1);

        Enrollment e2 = new Enrollment("S101", "CS102");
        boolean added2 = enrollmentSet.add(e2);
        System.out.println("同一人 (S101) 報名不同課程 (CS102): " + added2);

        Enrollment e3 = new Enrollment("S101", "CS101");
        boolean added3 = enrollmentSet.add(e3);
        System.out.println("同一人 (S101) 重複報名同一課程 (CS101): " + added3);

        System.out.println("\n目前報名總數: " + enrollmentSet.size());
        for (Enrollment e : enrollmentSet) {
            System.out.println(" - " + e);
        }

        System.out.println("\n=== 測試以「新建立但內容相同」的物件做搜尋與取消 ===");
        
        Enrollment target = new Enrollment("S101", "CS101");

        boolean contains = enrollmentSet.contains(target);
        System.out.println("使用新建物件測試 contains(S101, CS101): " + contains);

        boolean removed = enrollmentSet.remove(target);
        System.out.println("使用新建物件測試 remove(S101, CS101): " + removed);

        boolean containsAfterRemove = enrollmentSet.contains(target);
        System.out.println("再次測試 contains(S101, CS101): " + containsAfterRemove);

        System.out.println("\n取消後報名總數: " + enrollmentSet.size());
        for (Enrollment e : enrollmentSet) {
            System.out.println(" - " + e);
        }
    }
}