public class CourseComposition {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("I001", "王老師");

        Course course1 = new Course("CS101", "資料結構", instructor);
        Course course2 = new Course("CS102", "演算法", instructor);

        System.out.println(course1.summary());
        System.out.println(course2.summary());
    }
}

class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Course {
    private String code;
    private String title;
    private Instructor instructor;

    public Course(String code, String title, Instructor instructor) {
        this.code = code;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        return "課程代碼：" + code
                + "，課程標題：" + title
                + "，講師編號：" + instructor.getId()
                + "，講師姓名：" + instructor.getName();
    }
}