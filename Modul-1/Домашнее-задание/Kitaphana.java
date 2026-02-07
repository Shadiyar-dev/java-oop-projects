import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int copies;

    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = copies;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getCopies() { return copies; }

    public void addCopy() { copies++; }

    public boolean removeCopy() {
        if (copies > 0) {
            copies--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" " + author + " (ISBN: " + isbn + ") - " + copies + " дана";
    }
}

class Reader {
    private String name;
    private String readerId;
    private List<Book> borrowedBooks;

    public Reader(String name, String readerId) {
        this.name = name;
        this.readerId = readerId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getReaderId() { return readerId; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() < 5) {
            borrowedBooks.add(book);
            return true;
        }
        System.out.println("Оқырман " + name + " лимиттен асып кетті (5)");
        return false;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            return true;
        }
        System.out.println("Оқырман " + name + " \"" + book.getTitle() + "\" кітабын алмаған");
        return false;
    }

    @Override
    public String toString() {
        return "Оқырман: " + name + " (ID: " + readerId + "), кітаптар: " + borrowedBooks.size();
    }
}

public class Kitaphana {
    private List<Book> books;
    private List<Reader> readers;
    private Map<Reader, List<Book>> borrowedRecords;

    public Kitaphana() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.borrowedRecords = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Кітап қосылды: " + book.getTitle());
    }

    public boolean removeBook(String isbn) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                for (Reader reader : readers) {
                    if (reader.getBorrowedBooks().contains(book)) {
                        System.out.println("\"" + book.getTitle() + "\" кітабын жою мүмкін емес, ол берілген");
                        return false;
                    }
                }
                iterator.remove();
                System.out.println("Кітап жойылды: " + book.getTitle());
                return true;
            }
        }
        System.out.println("Кітап табылмады");
        return false;
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
        borrowedRecords.put(reader, new ArrayList<>());
        System.out.println("Оқырман тіркелді: " + reader.getName());
    }

    public boolean removeReader(String readerId) {
        Iterator<Reader> iterator = readers.iterator();
        while (iterator.hasNext()) {
            Reader reader = iterator.next();
            if (reader.getReaderId().equals(readerId)) {
                if (!reader.getBorrowedBooks().isEmpty()) {
                    System.out.println(reader.getName() + " оқырманын жою мүмкін емес, кітаптары бар");
                    return false;
                }
                iterator.remove();
                borrowedRecords.remove(reader);
                System.out.println("Оқырман жойылды: " + reader.getName());
                return true;
            }
        }
        System.out.println("Оқырман табылмады");
        return false;
    }

    public boolean lendBook(String isbn, String readerId) {
        Book bookToLend = null;
        Reader targetReader = null;

        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.getCopies() > 0) {
                bookToLend = book;
                break;
            }
        }

        if (bookToLend == null) {
            System.out.println("Кітап қолжетімді емес");
            return false;
        }

        for (Reader reader : readers) {
            if (reader.getReaderId().equals(readerId)) {
                targetReader = reader;
                break;
            }
        }

        if (targetReader == null) {
            System.out.println("Оқырман табылмады");
            return false;
        }

        if (bookToLend.removeCopy() && targetReader.borrowBook(bookToLend)) {
            borrowedRecords.get(targetReader).add(bookToLend);
            System.out.println("\"" + bookToLend.getTitle() + "\" кітабы берілді");
            return true;
        }

        return false;
    }

    public boolean returnBook(String isbn, String readerId) {
        Book bookToReturn = null;
        Reader targetReader = null;

        for (Reader reader : readers) {
            if (reader.getReaderId().equals(readerId)) {
                targetReader = reader;
                break;
            }
        }

        if (targetReader == null) {
            System.out.println("Оқырман табылмады");
            return false;
        }

        for (Book book : targetReader.getBorrowedBooks()) {
            if (book.getIsbn().equals(isbn)) {
                bookToReturn = book;
                break;
            }
        }

        if (bookToReturn == null) {
            System.out.println("Бұл кітап алынбаған");
            return false;
        }

        if (targetReader.returnBook(bookToReturn)) {
            bookToReturn.addCopy();
            borrowedRecords.get(targetReader).remove(bookToReturn);
            System.out.println("\"" + bookToReturn.getTitle() + "\" кітабы қайтарылды");
            return true;
        }

        return false;
    }

    public void displayBooks() {
        System.out.println("\n=== КІТАПТАР ===");
        if (books.isEmpty()) {
            System.out.println("Кітап жоқ");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void displayReaders() {
        System.out.println("\n=== ОҚЫРМАНДАР ===");
        if (readers.isEmpty()) {
            System.out.println("Оқырман жоқ");
        } else {
            for (Reader reader : readers) {
                System.out.println(reader);
                if (!reader.getBorrowedBooks().isEmpty()) {
                    System.out.println("  Алынған кітаптар:");
                    for (Book book : reader.getBorrowedBooks()) {
                        System.out.println("    - " + book.getTitle());
                    }
                }
            }
        }
    }

    // Басты метод
    public static void main(String[] args) {
        System.out.println("=== КІТАПХАНА БАСҚАРУ ЖҮЙЕСІ ===\n");

        Kitaphana kitaphana = new Kitaphana();

        Book k1 = new Book("Абай жолы", "Мұхтар Әуезов", "978-601-01-1001-5", 3);
        Book k2 = new Book("Қыз Жібек", "Ғабит Мүсірепов", "978-601-01-1002-6", 2);
        Book k3 = new Book("Қара сөздер", "Абай Құнанбаев", "978-601-01-1003-7", 4);
        Book k4 = new Book("Бөлінген көңіл", "Сәбит Мұқанов", "978-601-01-1004-8", 1);
        Book k5 = new Book("Тәуелсіздік толғауы", "Олжас Сүлейменов", "978-601-01-1005-9", 2);

        kitaphana.addBook(k1);
        kitaphana.addBook(k2);
        kitaphana.addBook(k3);
        kitaphana.addBook(k4);
        kitaphana.addBook(k5);

        Reader o1 = new Reader("Айгүл Сатыбалдина", "OKR001");
        Reader o2 = new Reader("Бекзат Төлегенов", "OKR002");
        Reader o3 = new Reader("Қадыр Жолдыбаев", "OKR003");

        kitaphana.registerReader(o1);
        kitaphana.registerReader(o2);
        kitaphana.registerReader(o3);

        kitaphana.displayBooks();
        kitaphana.displayReaders();

        kitaphana.lendBook("978-601-01-1001-5", "OKR001");
        kitaphana.lendBook("978-601-01-1002-6", "OKR002");
        kitaphana.lendBook("978-601-01-1003-7", "OKR003");

        kitaphana.displayBooks();
        kitaphana.displayReaders();

        kitaphana.returnBook("978-601-01-1001-5", "OKR001");

        kitaphana.displayBooks();
        kitaphana.displayReaders();
    }

}
