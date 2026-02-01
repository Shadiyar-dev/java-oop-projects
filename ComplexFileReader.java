import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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