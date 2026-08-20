abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract double calculatePay();

    public String getName() {
        return name;
    }
}

class MonthlyEmployee extends Employee {
    private double monthlySalary;

    public MonthlyEmployee(String name, double monthlySalary) {
        super(name);

        if (monthlySalary < 0) {
            monthlySalary = 0;
        }

        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyWage;
    private double hours;

    public HourlyEmployee(String name, double hourlyWage, double hours) {
        super(name);

        if (hourlyWage < 0) {
            hourlyWage = 0;
        }

        if (hours < 0) {
            hours = 0;
        }

        this.hourlyWage = hourlyWage;
        this.hours = hours;
    }

    @Override
    public double calculatePay() {
        return hourlyWage * hours;
    }
}

class SalesEmployee extends Employee {
    private double baseSalary;
    private double sales;
    private double bonusRate;

    public SalesEmployee(String name, double baseSalary,
                         double sales, double bonusRate) {
        super(name);

        if (baseSalary < 0) {
            baseSalary = 0;
        }

        if (sales < 0) {
            sales = 0;
        }

        if (bonusRate < 0) {
            bonusRate = 0;
        }

        this.baseSalary = baseSalary;
        this.sales = sales;
        this.bonusRate = bonusRate;
    }

    @Override
    public double calculatePay() {
        return baseSalary + sales * bonusRate;
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = new Employee[6];

        employees[0] = new MonthlyEmployee("小明", 50000);
        employees[1] = new MonthlyEmployee("小華", 45000);
        employees[2] = new HourlyEmployee("小美", 200, 160);
        employees[3] = new HourlyEmployee("小強", 220, 150);
        employees[4] = new SalesEmployee("小芳", 30000, 200000, 0.05);
        employees[5] = new SalesEmployee("小傑", 28000, 300000, 0.08);

        double maxPay = 0;
        Employee maxEmployee = null;

        for (Employee employee : employees) {
            double pay = employee.calculatePay();

            System.out.println(
                employee.getName() + " 的薪資：" + pay + " 元"
            );

            if (pay > maxPay) {
                maxPay = pay;
                maxEmployee = employee;
            }
        }

        System.out.println();
        System.out.println(
            "最高薪資：" + maxEmployee.getName() +
            "，薪資：" + maxPay + " 元"
        );
    }
}