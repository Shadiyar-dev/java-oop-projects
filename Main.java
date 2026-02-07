import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

// ========== DRY, KISS, YAGNI Классы ==========

enum LogLevel {
    INFO,
    WARNING,
    ERROR
}

class Logger {
    public void log(LogLevel level, String message) {
        String prefix;
        switch (level) {
            case ERROR:
                prefix = "ERROR";
                break;
            case WARNING:
                prefix = "WARNING";
                break;
            case INFO:
                prefix = "INFO";
                break;
            default:
                prefix = "UNKNOWN";
                break;
        }

        System.out.println(prefix + ": " + message);
    }

    public void logError(String message) {
        System.out.println("ERROR: " + message);
    }

    public void logWarning(String message) {
        System.out.println("WARNING: " + message);
    }

    public void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
}

class Configuration {
    private String databaseConnectionString;

    private Configuration() {
        databaseConnectionString = "Server=myServer;Database=myDb;User Id=myUser;Password=myPass;";
    }

    private static Configuration instance;

    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getDatabaseConnectionString() {
        return databaseConnectionString;
    }
}

class DatabaseService {
    private String connectionString;

    public DatabaseService() {
        this.connectionString = Configuration.getInstance().getDatabaseConnectionString();
    }

    public void connect() {
        System.out.println("Подключение к базе данных: " + connectionString);
    }
}

class LoggingService {
    private String connectionString;

    public LoggingService() {
        this.connectionString = Configuration.getInstance().getDatabaseConnectionString();
    }

    public void log(String message) {
        System.out.println("Запись лога: " + message + ", подключение: " + connectionString);
    }
}

class KissExamples {

    public void processNumbers(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }

        for (int number : numbers) {
            if (number > 0) {
                System.out.println(number);
            }
        }
    }

    public void processNumbersOld(int[] numbers) {
        if (numbers != null) {
            if (numbers.length > 0) {
                for (int number : numbers) {
                    if (number > 0) {
                        System.out.println(number);
                    }
                }
            }
        }
    }

    public void printPositiveNumbers(int[] numbers) {
        for (int number : numbers) {
            if (number > 0) {
                System.out.println(number);
            }
        }
    }

    public void printPositiveNumbersComplex(int[] numbers) {
        Arrays.stream(numbers)
                .filter(n -> n > 0)
                .sorted()
                .forEach(System.out::println);
    }

    public int divide(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    public int divideWithException(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}

class User {
    private String name;
    private String email;
    private String address;

    public User(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}

class UserRepository {
    public void saveUser(User user) {
        System.out.println("Сохранение пользователя " + user.getName() + " в БД");
    }
}

class EmailService {
    public void sendEmail(String email, String message) {
        System.out.println("Отправка письма на " + email + ": " + message);
    }
}

interface ReportGenerator {
    void generateReport(String data);
}

class SimpleReportGenerator implements ReportGenerator {
    @Override
    public void generateReport(String data) {
        System.out.println("Генерация стандартного отчета: " + data);
    }
}

class LabelPrinter {
    public void printAddressLabel(String address) {
        System.out.println("Печать ярлыка для адреса: " + address);
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

// ========== Файл оқу класстары ==========

class SimpleFileReader {

    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}

class ComplexFileReader {
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    public String readFile(String filePath, boolean useBuffer, int bufferSize) throws IOException {
        if (useBuffer) {
            return readWithBuffer(filePath, bufferSize);
        } else {
            return readWithoutBuffer(filePath);
        }
    }

    private String readWithBuffer(String filePath, int bufferSize) throws IOException {
        char[] buffer = new char[bufferSize];
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath), bufferSize)) {
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, charsRead);
            }
        }
        return content.toString();
    }

    private String readWithoutBuffer(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }
        }
        return content.toString();
    }
}