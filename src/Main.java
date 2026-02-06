// Order класы тек деректерді сақтау үшін
public class Order {
    private String productName;
    private int quantity;
    private double price;

    // конструктор, геттер/сеттер
    public Order(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

// Баға есептеу үшін бөлек клас
public class PriceCalculator {
    public double calculateTotalPrice(Order order) {
        return order.getQuantity() * order.getPrice() * 0.9;
    }
}

// Төлемді өңдеу үшін бөлек клас
public class PaymentProcessor {
    public void processPayment(String paymentDetails) {
        System.out.println("Payment processed using: " + paymentDetails);
    }
}

// Хабарландыру жіберу үшін бөлек клас
public class NotificationService {
    public void sendConfirmationEmail(String email) {
        System.out.println("Confirmation email sent to: " + email);
    }
}
