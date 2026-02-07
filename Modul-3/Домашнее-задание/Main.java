import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SRP мысалы ===");
        testSRP();

        System.out.println("\n=== OCP мысалы ===");
        testOCP();

        System.out.println("\n=== ISP мысалы ===");
        testISP();

        System.out.println("\n=== DIP мысалы ===");
        testDIP();
    }

    // ========== SRP ==========
    static class Order {
        private String productName;
        private int quantity;
        private double price;

        public Order(String productName, int quantity, double price) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public String getProductName() { return productName; }
        public int getQuantity() { return quantity; }
        public double getPrice() { return price; }
    }

    static class OrderCalculator {
        public double calculateTotalPrice(Order order) {
            return order.getQuantity() * order.getPrice() * 0.9;
        }
    }

    static class PaymentProcessor {
        public void processPayment(String paymentDetails) {
            System.out.println("Төлем өңделді: " + paymentDetails);
        }
    }

    static class NotificationServiceSRP {
        public void sendConfirmationEmail(String email) {
            System.out.println("Растау электрондық поштасы жіберілді: " + email);
        }
    }

    static void testSRP() {
        Order order = new Order("Ноутбук", 2, 1000);
        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculateTotalPrice(order);
        System.out.println("Жалпы бағасы: " + total);

        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment("Несие картасы");

        NotificationServiceSRP notification = new NotificationServiceSRP();
        notification.sendConfirmationEmail("колданушы@мысал.com");
    }

    // ========== OCP ==========
    abstract static class Employee {
        private String name;
        private double baseSalary;

        public Employee(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public String getName() { return name; }
        public double getBaseSalary() { return baseSalary; }

        public abstract double calculateSalary();
    }

    static class PermanentEmployee extends Employee {
        public PermanentEmployee(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double calculateSalary() {
            return getBaseSalary() * 1.2;
        }
    }

    static class ContractEmployee extends Employee {
        public ContractEmployee(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double calculateSalary() {
            return getBaseSalary() * 1.1;
        }
    }

    static class InternEmployee extends Employee {
        public InternEmployee(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double calculateSalary() {
            return getBaseSalary() * 0.8;
        }
    }

    static class FreelancerEmployee extends Employee {
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

    static class EmployeeSalaryCalculator {
        public double calculateTotalSalary(List<Employee> employees) {
            double total = 0;
            for (Employee emp : employees) {
                total += emp.calculateSalary();
            }
            return total;
        }
    }

    static void testOCP() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new PermanentEmployee("Жаңа", 50000));
        employees.add(new ContractEmployee("Әйгерім", 40000));
        employees.add(new InternEmployee("Бауыржан", 30000));
        employees.add(new FreelancerEmployee("Әлия", 160, 50));

        // Әр қызметкердің жалақясын жеке-жеке көрсету
        for (Employee emp : employees) {
            System.out.println(emp.getName() + " жалақясы: " + emp.calculateSalary());
        }

        EmployeeSalaryCalculator calculator = new EmployeeSalaryCalculator();
        double totalSalary = calculator.calculateTotalSalary(employees);
        System.out.println("Барлық қызметкерлердің жалпы жалақясы: " + totalSalary);
    }

    // ========== ISP ==========
    interface Printer {
        void print(String content);
    }

    interface Scanner {
        void scan(String content);
    }

    interface Fax {
        void fax(String content);
    }

    static class AllInOnePrinter implements Printer, Scanner, Fax {
        @Override
        public void print(String content) {
            System.out.println("Басып шығаруда: " + content);
        }

        @Override
        public void scan(String content) {
            System.out.println("Сканерлеуде: " + content);
        }

        @Override
        public void fax(String content) {
            System.out.println("Факспен жіберуде: " + content);
        }
    }

    static class BasicPrinter implements Printer {
        @Override
        public void print(String content) {
            System.out.println("Басып шығаруда: " + content);
        }
    }

    static class PrintAndScanPrinter implements Printer, Scanner {
        @Override
        public void print(String content) {
            System.out.println("Басып шығаруда: " + content);
        }

        @Override
        public void scan(String content) {
            System.out.println("Сканерлеуде: " + content);
        }
    }

    static void testISP() {
        BasicPrinter basicPrinter = new BasicPrinter();
        basicPrinter.print("Құжат");

        PrintAndScanPrinter scanPrinter = new PrintAndScanPrinter();
        scanPrinter.print("Құжат");
        scanPrinter.scan("Фото");

        AllInOnePrinter allInOne = new AllInOnePrinter();
        allInOne.print("Құжат");
        allInOne.scan("Фото");
        allInOne.fax("Келісімшарт");
    }

    // ========== DIP ==========
    interface NotificationSender {
        void send(String message);
    }

    static class EmailSender implements NotificationSender {
        @Override
        public void send(String message) {
            System.out.println("Электрондық пошта жіберілді: " + message);
        }
    }

    static class SmsSender implements NotificationSender {
        @Override
        public void send(String message) {
            System.out.println("SMS жіберілді: " + message);
        }
    }

    static class MessengerSender implements NotificationSender {
        @Override
        public void send(String message) {
            System.out.println("Мессенджер хабарламасы жіберілді: " + message);
        }
    }

    static class NotificationServiceDIP {
        private List<NotificationSender> senders;

        public NotificationServiceDIP(List<NotificationSender> senders) {
            this.senders = senders;
        }

        public void addSender(NotificationSender sender) {
            this.senders.add(sender);
        }

        public void sendNotification(String message) {
            for (NotificationSender sender : senders) {
                sender.send(message);
            }
        }
    }

    static void testDIP() {
        List<NotificationSender> senders = new ArrayList<>();
        senders.add(new EmailSender());
        senders.add(new SmsSender());

        NotificationServiceDIP notificationService = new NotificationServiceDIP(senders);
        notificationService.sendNotification("Сәлем Әлем!");

        notificationService.addSender(new MessengerSender());
        notificationService.sendNotification("Жаңа хабарлама!");
    }
}
