package org.softwire.training.bookish.models.database;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private int copies;
    private String imageUrl;
    private int publishedYear;
    private String synopsis;

    public Book() { }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
