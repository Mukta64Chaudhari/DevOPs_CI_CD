public class Main {
    public static void main(String[] args) {

        Library lib = new Library();

        Book b1 = new Book(1, "Java Basics", "ABC");
        Book b2 = new Book(2, "DSA", "XYZ");

        lib.addBook(b1);
        lib.addBook(b2);

        lib.showBooks();

        b1.issueBook();
        b1.returnBook();
    }
}