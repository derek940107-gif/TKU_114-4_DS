public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S001", "王小明", 80, 75, 90, 95),
            new CourseGrade("S002", "李小華", 70, 65, 60, 80),
            new CourseGrade("S003", "陳大文", 95, 90, 88, 100),
            new CourseGrade("S004", "林小美", 60, 55, 50, 70),
            new CourseGrade("S005", "張小強", 45, 50, 40, 60)
        };

        double total = 0;
        CourseGrade highest = grades[0];

        System.out.println("所有成績：");

        for (CourseGrade grade : grades) {
            System.out.println(grade);

            total += grade.calculateFinalScore();

            if (grade.calculateFinalScore() > highest.calculateFinalScore()) {
                highest = grade;
            }
        }

        double average = total / grades.length;

        System.out.println();
        System.out.println("平均分：" + average);
        System.out.println("最高分：");
        System.out.println(highest);

        System.out.println("不及格名單：");

        for (CourseGrade grade : grades) {
            if (grade.calculateFinalScore() < 60) {
                System.out.println(grade);
            }
        }
    }
}

class CourseGrade {
    private String studentId;
    private String name;
    private double regular;
    private double midterm;
    private double finalExam;
    private double attendance;

    public CourseGrade(String studentId, String name,
                       double regular, double midterm,
                       double finalExam, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.regular = limitScore(regular);
        this.midterm = limitScore(midterm);
        this.finalExam = limitScore(finalExam);
        this.attendance = limitScore(attendance);
    }

    private double limitScore(double score) {
        if (score < 0) {
            return 0;
        }

        if (score > 100) {
            return 100;
        }

        return score;
    }

    public double calculateFinalScore() {
        return regular * 0.5
                + midterm * 0.2
                + finalExam * 0.2
                + attendance * 0.1;
    }

    public String getLevel() {
        double score = calculateFinalScore();

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "學號：" + studentId
                + "，姓名：" + name
                + "，平時：" + regular
                + "，期中：" + midterm
                + "，期末：" + finalExam
                + "，出席：" + attendance
                + "，總分：" + calculateFinalScore()
                + "，等級：" + getLevel();
    }
}