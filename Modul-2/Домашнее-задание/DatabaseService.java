class DatabaseService {
    private String connectionString;

    public DatabaseService() {
        this.connectionString = Configuration.getInstance().getDatabaseConnectionString();
    }

    public void connect() {
        System.out.println("Подключение к базе данных: " + connectionString);
    }
}
