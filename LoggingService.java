class LoggingService {
    private String connectionString;

    public LoggingService() {
        this.connectionString = Configuration.getInstance().getDatabaseConnectionString();
    }

    public void log(String message) {
        System.out.println("Запись лога: " + message + ", подключение: " + connectionString);
    }
}