package com.library;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book() {
    }

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public Book(int id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public String getStatus() {
        return issued ? "Issued" : "Available";
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    // Methods
    public boolean issueBook() {
        if (!issued) {
            issued = true;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (issued) {
            issued = false;
            return true;
        }
        return false;
    }
}