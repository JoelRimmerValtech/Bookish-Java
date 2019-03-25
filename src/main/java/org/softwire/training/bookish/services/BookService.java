package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getBooks(String orderBy) {
        switch (orderBy) {
            case "author":
                return jdbi.withHandle(handle ->
                        handle.createQuery("SELECT * FROM library_books ORDER BY author ASC")
                                .mapToBean(Book.class).list()
                );
            case "alpha":
                return jdbi.withHandle(handle ->
                        handle.createQuery("SELECT * FROM library_books ORDER BY title ASC")
                                .mapToBean(Book.class).list()
                );
//            case "date":
//                return jdbi.withHandle(handle ->
//                        handle.createQuery("SELECT * FROM library_books ORDER BY date ASC")
//                                .mapToBean(Book.class).list()
//                );
            default:
                return jdbi.withHandle(handle ->
                        handle.createQuery("SELECT * FROM library_books")
                                .mapToBean(Book.class).list()
                );
        }
    }
}
