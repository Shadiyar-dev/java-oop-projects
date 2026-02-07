package isp;

public class AllInOnePrinter implements Printer, Scanner, Fax {
    @Override
    public void print(String content) {
        System.out.println("Басып шығаруда: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Сканерлеуде: " + content);
    }

    @Override
    public void fax(String content) {
        System.out.println("Факспен жіберуде: " + content);
    }
}