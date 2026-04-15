package com.library;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LibraryTest {
    private static final String TEST_BOOKS_FILE = "target/test-books.json";

    @Before
    public void setUp() {
        System.setProperty("books.file", TEST_BOOKS_FILE);
        Library.resetInstance();
        new File(TEST_BOOKS_FILE).delete();
    }

    @After
    public void tearDown() {
        new File(TEST_BOOKS_FILE).delete();
        Library.resetInstance();
    }

    @Test
    public void testAddSearchIssueReturnAndDeleteBook() {
        Library library = Library.getInstance();

        Book book = new Book(101, "Effective Java", "Joshua Bloch");
        assertTrue(library.addBook(book));
        assertFalse(library.addBook(book));

        ArrayList<Book> allBooks = library.getAllBooks();
        assertEquals(1, allBooks.size());
        assertEquals(book.getTitle(), allBooks.get(0).getTitle());

        Book found = library.getBookById(101);
        assertNotNull(found);
        assertEquals("Joshua Bloch", found.getAuthor());

        ArrayList<Book> results = library.searchByTitle("Effective");
        assertEquals(1, results.size());

        assertTrue(library.issueBook(101));
        assertTrue(library.getBookById(101).isIssued());

        assertTrue(library.returnBook(101));
        assertFalse(library.getBookById(101).isIssued());

        assertTrue(library.deleteBook(101));
        assertNull(library.getBookById(101));
    }
}
