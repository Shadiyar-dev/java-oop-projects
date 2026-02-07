import java.util.ArrayList;
import java.util.List;

public class OnlineShop {
    public static void main(String[] args) {
        System.out.println("=== Интернет-дүкен заказын басқару жүйесі ===\n");

        Product laptop = new Product("Ноутбук", 1000.0);
        Product phone = new Product("Телефон", 500.0);
        Product headphones = new Product("Құлаққап", 100.0);

        Order order = new Order("ORD001");
        order.addProduct(laptop, 1);
        order.addProduct(phone, 2);
        order.addProduct(headphones, 1);

        IPayment creditCardPayment = new CreditCardPayment("1234-5678-9012-3456");
        order.setPaymentMethod(creditCardPayment);

        IDelivery courierDelivery = new CourierDelivery();
        order.setDeliveryMethod(courierDelivery);

        List<INotification> notifications = new ArrayList<>();
        notifications.add(new EmailNotification("musteri@email.kz"));
        notifications.add(new SmsNotification("+77771234567"));
        order.setNotifications(notifications);

        System.out.println("Заказ ақпараты:");
        System.out.println("Заказ нөмірі: " + order.getOrderNumber());
        System.out.println("Тауарлар саны: " + order.getItemCount());

        double initialTotal = order.calculateInitialTotal();
        System.out.println("Бастапқы құны: " + initialTotal + " ₸");

        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.addDiscount(new PercentageDiscount(10));
        discountCalculator.addDiscount(new FixedAmountDiscount(50));

        double discount = discountCalculator.calculateTotalDiscount(order);
        double finalTotal = initialTotal - discount;

        System.out.println("Жеңілдік сомасы: " + discount + " ₸");
        System.out.println("Төленетін сома: " + finalTotal + " ₸");

        System.out.println("\nЗаказ өңделуде...");
        order.processOrder();

        System.out.println("\n=== Басқа төлем әдістерімен сынақ ===");

        IPayment paypalPayment = new PayPalPayment("musteri@email.kz");
        IPayment bankTransferPayment = new BankTransferPayment("KZ1234567890");

        Order order2 = new Order("ORD002");
        order2.addProduct(laptop, 1);
        order2.setPaymentMethod(paypalPayment);
        order2.setDeliveryMethod(new PostDelivery());
        order2.processOrder();

        System.out.println("\n=== Басқа жеткізу әдістерімен сынақ ===");

        Order order3 = new Order("ORD003");
        order3.addProduct(phone, 1);
        order3.setPaymentMethod(bankTransferPayment);
        order3.setDeliveryMethod(new PickUpPointDelivery("Алматы, Тәуелсіздік көшесі 1"));
        order3.processOrder();

        System.out.println("\n=== Жаңа жеңілдік түрін қосу ===");
        discountCalculator.addDiscount(new QuantityDiscount(3, 5));

        double newDiscount = discountCalculator.calculateTotalDiscount(order);
        System.out.println("Жаңа жеңілдік сомасы: " + newDiscount + " ₸");

        System.out.println("\n=== Жүйе демонстрациясы аяқталды ===");
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return product.getPrice() * quantity; }
}

class Order {
    private String orderNumber;
    private List<OrderItem> items;
    private IPayment paymentMethod;
    private IDelivery deliveryMethod;
    private List<INotification> notifications;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
        this.items = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public String getOrderNumber() { return orderNumber; }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
        System.out.println(quantity + " дана " + product.getName() + " заказға қосылды");
    }

    public int getItemCount() {
        return items.size();
    }

    public double calculateInitialTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void setPaymentMethod(IPayment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDeliveryMethod(IDelivery deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setNotifications(List<INotification> notifications) {
        this.notifications = notifications;
    }

    public void processOrder() {
        System.out.println("\n--- " + orderNumber + " заказы өңделуде ---");

        if (paymentMethod != null) {
            double amount = calculateInitialTotal();
            paymentMethod.processPayment(amount);
        } else {
            System.out.println("Қате: төлем әдісі таңдалмаған");
        }

        if (deliveryMethod != null) {
            deliveryMethod.deliverOrder(this);
        } else {
            System.out.println("Қате: жеткізу әдісі таңдалмаған");
        }

        sendNotifications("Сіздің " + orderNumber + " заказыңыз қабылданды және өңделуде");

        System.out.println("--- Заказ өңдеу аяқталды ---\n");
    }

    private void sendNotifications(String message) {
        for (INotification notification : notifications) {
            notification.sendNotification(message);
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }
}

interface IPayment {
    void processPayment(double amount);
}

class CreditCardPayment implements IPayment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Несие картасымен төлем: " + amount + " ₸");
        System.out.println("Карта нөмірі: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4));
        // Нақты төлем логикасы осында болады
    }
}

class PayPalPayment implements IPayment {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal арқылы төлем: " + amount + " ₸");
        System.out.println("PayPal email: " + email);
    }
}

class BankTransferPayment implements IPayment {
    private String accountNumber;

    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Банк аударымымен төлем: " + amount + " ₸");
        System.out.println("Есептік жазба нөмірі: " + accountNumber);
    }
}

interface IDelivery {
    void deliverOrder(Order order);
}

class CourierDelivery implements IDelivery {
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Курьер арқылы жеткізу: заказ " + order.getOrderNumber() + " үйге жеткізіледі");
    }
}

class PostDelivery implements IDelivery {
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Пошта арқылы жеткізу: заказ " + order.getOrderNumber() + " пошта арқылы жіберіледі");
    }
}

class PickUpPointDelivery implements IDelivery {
    private String pickupLocation;

    public PickUpPointDelivery(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Қабылдау пунктіне жеткізу: заказ " + order.getOrderNumber() +
                " " + pickupLocation + " мекенжайында қабылдауға дайын");
    }
}

interface INotification {
    void sendNotification(String message);
}

class EmailNotification implements INotification {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Email хабарламасы " + email + " поштасына жіберілді: " + message);
    }
}

class SmsNotification implements INotification {
    private String phoneNumber;

    public SmsNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS хабарламасы " + phoneNumber + " нөміріне жіберілді: " + message);
    }
}

interface IDiscount {
    double calculateDiscount(Order order);
}

class PercentageDiscount implements IDiscount {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Order order) {
        double total = order.calculateInitialTotal();
        return total * (percentage / 100);
    }
}

class FixedAmountDiscount implements IDiscount {
    private double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double calculateDiscount(Order order) {
        return amount; // Тұрақты сома
    }
}

class QuantityDiscount implements IDiscount {
    private int minQuantity;
    private double percentage;

    public QuantityDiscount(int minQuantity, double percentage) {
        this.minQuantity = minQuantity;
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Order order) {
        int totalItems = 0;
        for (OrderItem item : order.getItems()) {
            totalItems += item.getQuantity();
        }

        if (totalItems >= minQuantity) {
            return order.calculateInitialTotal() * (percentage / 100);
        }
        return 0;
    }
}

class DiscountCalculator {
    private List<IDiscount> discounts;

    public DiscountCalculator() {
        this.discounts = new ArrayList<>();
    }

    public void addDiscount(IDiscount discount) {
        discounts.add(discount);
        System.out.println("Жаңа жеңілдік түрі қосылды");
    }

    public double calculateTotalDiscount(Order order) {
        double totalDiscount = 0;
        for (IDiscount discount : discounts) {
            totalDiscount += discount.calculateDiscount(order);
        }
        return totalDiscount;
    }
}

class CryptocurrencyPayment implements IPayment {
    private String walletAddress;

    public CryptocurrencyPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Криптовалютамен төлем: " + amount + " ₸");
        System.out.println("Әмиян мекенжайы: " + walletAddress);
    }
}

class ExpressDelivery implements IDelivery {
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Жедел жеткізу: заказ " + order.getOrderNumber() + " 24 сағат ішінде жеткізіледі");
    }
}

class PushNotification implements INotification {
    private String deviceToken;

    public PushNotification(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Push хабарламасы " + deviceToken + " құрылғысына жіберілді: " + message);
    }
}
