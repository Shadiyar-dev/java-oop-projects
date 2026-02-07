package ocp;

import java.util.List;

public class EmployeeSalaryCalculator {
    public double calculateTotalSalary(List<Employee> employees) {
        double total = 0;
        for (Employee emp : employees) {
            total += emp.calculateSalary();
        }
        return total;
    }
}