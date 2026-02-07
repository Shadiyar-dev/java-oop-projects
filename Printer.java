package isp;

public interface Printer {
    void print(String content);
}

interface Scanner {
    void scan(String content);
}

interface Fax {
    void fax(String content);
}