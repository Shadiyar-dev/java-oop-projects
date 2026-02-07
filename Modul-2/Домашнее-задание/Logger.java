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
