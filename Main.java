import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация принципов DRY, KISS и YAGNI ===\n");

        System.out.println("1. Принцип DRY:");
        Logger logger = new Logger();
        logger.log(LogLevel.INFO, "Тестовое сообщение INFO");
        logger.log(LogLevel.WARNING, "Тестовое сообщение WARNING");
        logger.log(LogLevel.ERROR, "Тестовое сообщение ERROR");

        System.out.println("\nНарушение DRY (дублирование):");
        logger.logInfo("Старый способ INFO");
        logger.logWarning("Старый способ WARNING");
        logger.logError("Старый способ ERROR");

        DatabaseService dbService = new DatabaseService();
        dbService.connect();

        LoggingService loggingService = new LoggingService();
        loggingService.log("Тестовый лог");

        System.out.println("\n\n2. Принцип KISS:");
        KissExamples kissExamples = new KissExamples();
        int[] numbers = {-5, 3, 0, 8, -2, 7};

        System.out.println("Упрощенный метод (KISS):");
        kissExamples.processNumbers(numbers);

        System.out.println("\nСложный метод (не KISS):");
        kissExamples.processNumbersOld(numbers);

        System.out.println("\nПоложительные числа простым способом:");
        kissExamples.printPositiveNumbers(numbers);

        System.out.println("\nПоложительные числа сложным способом:");
        kissExamples.printPositiveNumbersComplex(numbers);

        System.out.println("\nДеление 10 на 2: " + kissExamples.divide(10, 2));
        System.out.println("Деление 10 на 0 (простой способ): " + kissExamples.divide(10, 0));
        System.out.println("Деление 10 на 0 (с исключением): " + kissExamples.divideWithException(10, 0));

        System.out.println("\n\n3. Принцип YAGNI:");

        User user = new User("Иван Иванов", "ivan@example.com", "ул. Примерная, 123");

        UserRepository repository = new UserRepository();
        repository.saveUser(user);

        EmailService emailService = new EmailService();
        emailService.sendEmail(user.getEmail(), "Добро пожаловать в нашу систему!");

        ReportGenerator simpleGenerator = new SimpleReportGenerator();
        simpleGenerator.generateReport("Данные для отчета");

        LabelPrinter labelPrinter = new LabelPrinter();
        labelPrinter.printAddressLabel(user.getAddress());

        System.out.println("\n\n4. Все принципы вместе:");
        Application application = new Application();
        application.processUserData(user);

        System.out.println("\n=== Демонстрация завершена ===");
    }
}

class Application {
    private final Logger logger = new Logger();

    public void processUserData(User user) {
        if (user == null) {
            logger.log(LogLevel.ERROR, "Пользователь не может быть null");
            return;
        }

        logger.log(LogLevel.INFO, "Обработка пользователя: " + user.getName());

        UserRepository repository = new UserRepository();
        repository.saveUser(user);

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            EmailService emailService = new EmailService();
            emailService.sendEmail(user.getEmail(), "Добро пожаловать!");
        }

        logger.log(LogLevel.INFO, "Обработка завершена успешно");
    }

    public void generateReports(List<String> data) {
        for (String item : data) {
            ReportGenerator generator = new SimpleReportGenerator();
            generator.generateReport(item);
        }
    }
}