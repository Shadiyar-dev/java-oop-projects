package isp;

public class PrintAndScanPrinter implements Printer, Scanner {
    @Override
    public void print(String content) {
        System.out.println("Басып шығаруда: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Сканерлеуде: " + content);
    }
}
