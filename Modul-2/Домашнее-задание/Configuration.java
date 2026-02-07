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
