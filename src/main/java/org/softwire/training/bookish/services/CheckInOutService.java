package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.BooksTakenOut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInOutService extends DatabaseService {

    public List<BooksTakenOut> getCheckInOut() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT books_taken_out.transaction_id transaction_id, " +
                        "                       library_members.forename member_forename, " +
                        "                       library_members.surname member_surname, " +
                        "                       library_books.title book_title, " +
                        "                       books_taken_out.date_due date_due" +
                        "                FROM bookish.books_taken_out " +
                        "                INNER JOIN bookish.library_members " +
                        "                ON books_taken_out.member_id = library_members.member_id " +
                        "                INNER JOIN bookish.library_books " +
                        "                ON books_taken_out.book_id = library_books.book_id")
                        .mapToBean(BooksTakenOut.class).list()
        );
    }

    public void addTransaction(BooksTakenOut booksTakenOut) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO books_taken_out " +
                        "(book_id, member_id, date_due) " +
                        "VALUES (:book_id, :member_id, :date_due)")
                        .bind("book_id",booksTakenOut.getBook().getBookId())
                        .bind("member_id",booksTakenOut.getMember().getMemberId())
                        .bind("date_due", booksTakenOut.getDateDue())
                        .execute()
        );
    }

    public void editBook(BooksTakenOut booksTakenOut) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE books_taken_out SET " +
                        "date_due = :date_due" +
                        " WHERE transaction_id = :transaction_id")
                        .bind("date_due", booksTakenOut.getDateDue())
                        .bind("transaction_id", booksTakenOut.getTransactionId())
                        .execute()
        );
    }

    public void deleteBook(int transactionId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM books_taken_out WHERE transaction_id = :transaction_id")
                        .bind("transaction_id", transactionId)
                        .execute()
        );
    }
}
