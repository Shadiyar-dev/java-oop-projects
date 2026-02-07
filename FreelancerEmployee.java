package ocp;

public class FreelancerEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public FreelancerEmployee(String name, int hoursWorked, double hourlyRate) {
        super(name, 0);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}