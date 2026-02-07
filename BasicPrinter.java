package isp;

public class BasicPrinter implements Printer {
    @Override
    public void print(String content) {
        System.out.println("Басып шығаруда: " + content);
    }
}