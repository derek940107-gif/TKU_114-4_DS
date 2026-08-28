import java.util.List;

public class Q03_EmployeePayroll {

    public static abstract class Employee {
        protected final String id;
        protected final String name;

        protected Employee(String id, String name) {
            if (id == null || id.trim().isEmpty() ||
                name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract int monthlyPay();

        public String summary() {
            return id + "|" + name + "|" + monthlyPay();
        }
    }

    public static class SalariedEmployee extends Employee {
        private final int salary;

        public SalariedEmployee(String id, String name, int salary) {
            super(id, name);
            this.salary = Math.max(salary, 0);
        }

        @Override
        public int monthlyPay() {
            return salary;
        }
    }

    public static class HourlyEmployee extends Employee {
        private final int hours;
        private final int hourlyRate;

        public HourlyEmployee(String id, String name, int hours, int hourlyRate) {
            super(id, name);
            this.hours = Math.max(hours, 0);
            this.hourlyRate = Math.max(hourlyRate, 0);
        }

        @Override
        public int monthlyPay() {
            if (hours <= 160) {
                return hours * hourlyRate;
            }

            double regularPay = 160 * hourlyRate;
            double overtimePay = (hours - 160) * hourlyRate * 1.5;

            return (int) (regularPay + overtimePay);
        }
    }

    public static int totalPayroll(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }

        int payrollTraceK4 = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                payrollTraceK4 += employee.monthlyPay();
            }
        }

        return payrollTraceK4;
    }
}