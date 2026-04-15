package com.library;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void testIssueAndReturnTransitions() {
        Book book = new Book(1, "Test Title", "Test Author");

        assertFalse(book.isIssued());
        assertEquals("Available", book.getStatus());

        assertTrue(book.issueBook());
        assertTrue(book.isIssued());
        assertEquals("Issued", book.getStatus());

        assertFalse(book.issueBook());
        assertTrue(book.returnBook());
        assertFalse(book.isIssued());
        assertEquals("Available", book.getStatus());

        assertFalse(book.returnBook());
    }
}
