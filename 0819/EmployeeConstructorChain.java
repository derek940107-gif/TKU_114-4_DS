abstract class EmployeeBase {
    protected String id;
    protected String name;

    public EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase");
    }

    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private double salary;

    public FullTimeEmployee(String id, String name, double salary) {
        super(id, name);

        if (salary < 0) {
            salary = 0;
        }

        this.salary = salary;
        System.out.println("FullTimeEmployee");
    }

    @Override
    public double calculatePay() {
        return salary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private double hourlyWage;
    private double hours;

    public PartTimeEmployee(String id, String name,
                            double hourlyWage, double hours) {
        super(id, name);

        if (hourlyWage < 0) {
            hourlyWage = 0;
        }

        if (hours < 0) {
            hours = 0;
        }

        this.hourlyWage = hourlyWage;
        this.hours = hours;

        System.out.println("PartTimeEmployee");
    }

    @Override
    public double calculatePay() {
        return hourlyWage * hours;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        FullTimeEmployee employee1 =
                new FullTimeEmployee("E001", "小明", 50000);

        System.out.println("薪資：" + employee1.calculatePay());

        System.out.println();

        PartTimeEmployee employee2 =
                new PartTimeEmployee("E002", "小華", 200, 80);

        System.out.println("薪資：" + employee2.calculatePay());

        System.out.println();

        FullTimeEmployee employee3 =
                new FullTimeEmployee("E003", "小美", -10000);

        PartTimeEmployee employee4 =
                new PartTimeEmployee("E004", "小強", -150, -10);

        System.out.println("負數薪資處理後：" + employee3.calculatePay());
        System.out.println("負數資料處理後：" + employee4.calculatePay());
    }
}