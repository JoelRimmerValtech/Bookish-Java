package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.security.BasicPermission;
import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getBooks(String orderBy, String query) {
        String sql;

        switch (orderBy) {
            case "author":
                sql = "SELECT * FROM library_books ORDER BY author ASC";
                break;
            case "alpha":
                sql = "SELECT * FROM library_books ORDER BY title ASC";
                break;
            case "year":
                sql = "SELECt * FROM library_books ORDER BY published_year ASC";
                break;
            default:
                sql = "SELECT * FROM library_books";
                break;
        }

        switch (query) {
            case "":
                break;
            default:
                sql = "SELECT * FROM library_books WHERE title LIKE '%" + query +
                        "%' OR author LIKE '%" + query + "%'";
        }

        String finalSql = sql;
        return jdbi.withHandle(handle ->
                handle.createQuery(finalSql)
                        .mapToBean(Book.class).list()
        );
    }

    public void addBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO library_books " +
                        "(title, author, copies, image_url, published_year, synopsis) " +
                        "VALUES (:title, :author, :copies, :image_url, :published_year, :synopsis)")
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .bind("copies", book.getCopies())
                        .bind("image_url", book.getImageUrl())
                        .bind("published_year", book.getPublishedYear())
                        .bind("synopsis", book.getSynopsis())
                        .execute()
        );
    }

    public void editBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE library_books SET " +
                        "title = :title, author = :author, copies = :copies, image_url = :image_url, " +
                        "published_year = :published_year, synopsis = :synopsis" +
                        " WHERE book_id = :book_id")
                        .bind("book_id",book.getBookId())
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .bind("copies", book.getCopies())
                        .bind("image_url", book.getImageUrl())
                        .bind("published_year", book.getPublishedYear())
                        .bind("synopsis", book.getSynopsis())
                        .execute()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM library_books WHERE book_id = :id")
                        .bind("id", bookId)
                        .execute()
        );
    }
}
