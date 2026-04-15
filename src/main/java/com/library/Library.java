package com.library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Library {
    private static final String DEFAULT_BOOKS_FILE = "library_books.json";
    private static final String BOOKS_FILE_PATH = System.getenv("BOOKS_FILE") != null
            ? System.getenv("BOOKS_FILE")
            : System.getProperty("books.file", DEFAULT_BOOKS_FILE);
    private static Library instance;
    private ArrayList<Book> books;
    private final Gson gson = new Gson();

    public Library() {
        books = new ArrayList<>();
        loadBooks();
    }

    // Singleton pattern for web application
    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    static synchronized void resetInstance() {
        instance = null;
    }

    public synchronized boolean addBook(Book b) {
        if (getBookById(b.getId()) != null) {
            return false; // duplicate ID
        }
        books.add(b);
        saveBooks();
        return true;
    }

    public synchronized ArrayList<Book> getAllBooks() {
        return new ArrayList<>(books);
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

    public synchronized boolean issueBook(int id) {
        Book book = getBookById(id);
        if (book != null && book.issueBook()) {
            saveBooks();
            return true;
        }
        return false;
    }

    public synchronized boolean returnBook(int id) {
        Book book = getBookById(id);
        if (book != null && book.returnBook()) {
            saveBooks();
            return true;
        }
        return false;
    }

    public synchronized boolean deleteBook(int id) {
        boolean removed = books.removeIf(b -> b.getId() == id);
        if (removed) {
            saveBooks();
        }
        return removed;
    }

    private void loadBooks() {
        File dataFile = new File(BOOKS_FILE_PATH);
        if (!dataFile.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(dataFile)) {
            Type collectionType = new TypeToken<ArrayList<Book>>() {}.getType();
            ArrayList<Book> loadedBooks = gson.fromJson(reader, collectionType);
            if (loadedBooks != null) {
                books = loadedBooks;
            }
        } catch (IOException e) {
            System.err.println("Failed to load books from " + BOOKS_FILE_PATH + ": " + e.getMessage());
        }
    }

    private void saveBooks() {
        File dataFile = new File(BOOKS_FILE_PATH);
        File parent = dataFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.err.println("Failed to save books to " + BOOKS_FILE_PATH + ": " + e.getMessage());
        }
    }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b.getId() + " - " + b.getTitle() + " by " + b.getAuthor() + " [" + b.getStatus() + "]");
        }
    }
}