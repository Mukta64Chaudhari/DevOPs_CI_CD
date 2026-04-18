import java.util.ArrayList;
import java.util.stream.Collectors;

public class Library {
    private static Library instance;
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Singleton pattern for web application
    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public boolean addBook(Book b) {
        if (getBookById(b.getId()) != null) {
            return false; // duplicate ID
        }
        books.add(b);
        return true;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Book> searchByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean issueBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            return book.issueBook();
        }
        return false;
    }

    public boolean returnBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            return book.returnBook();
        }
        return false;
    }

    public boolean deleteBook(int id) {
        return books.removeIf(b -> b.getId() == id);
    }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b.getId() + " - " + b.getTitle() + " by " + b.getAuthor() + " [" + b.getStatus() + "]");
        }
    }
}