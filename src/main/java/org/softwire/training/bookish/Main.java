package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BooksTakenOut;
import org.softwire.training.bookish.models.database.Member;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "bookish";
        String user = "root";
        String password = "password";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Connection connection = DriverManager.getConnection(connectionString);

        Statement stmt = null;
        String query = "SELECT * FROM bookish.library_books ORDER BY title ASC";

        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int bookID = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            int copies = rs.getInt("copies");
            System.out.println(bookID + "\t" + title +
                    "\t" + author + "\t" + copies);
        }
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);

        List<Book> listOfBooks = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM bookish.library_books ORDER BY title ASC")
                    .mapToBean(Book.class)
                    .list());
        for (Book book : listOfBooks) {
            System.out.print("Book ID: " + book.getBookId()+ ", ");
            System.out.println("Book Title: " + book.getTitle());
        }

//        List<BooksTakenOut> listOfBooksTakenOut = jdbi.withHandle(handle ->
//                handle.createQuery("SELECT books_taken_out.transaction_id t_transaction_id, " +
//                        "                       library_members.member_id m_member_id," +
//                        "                       library_members.forename m_forename, " +
//                        "                       library_members.surname m_surname," +
//                        "                       books_taken_out.book_id t_book_id," +
//                        "                       books_taken_out.date_due t_date_due" +
//                        "                FROM bookish.books_taken_out " +
//                        "                INNER JOIN bookish.library_members " +
//                        "                ON books_taken_out.member_id = library_members.member_id " +
//                        "                ORDER BY date_due ASC")
//                .registerRowMapper(BeanMapper.factory(Member.class, "m"))
//                .registerRowMapper(BeanMapper.factory(BooksTakenOut.class, "t"))
//                .reduceRows(new LinkedList<>(), (list, rowView) -> {
//                    BooksTakenOut booksTakenOut = rowView.getRow(BooksTakenOut.class);
//                    booksTakenOut.setMember(rowView.getRow(Member.class));
//                    list.add(booksTakenOut);
//                    return list;
//                })
//        );
//        for (BooksTakenOut transaction : listOfBooksTakenOut) {
//            System.out.println();
//            System.out.print("Transaction ID: " + transaction.getTransactionId() + ", ");
//            System.out.print("Member: " + transaction.getMember().getForename() + " " + transaction.getMember().getSurname() + ", ");
//            System.out.println("Book ID: " +transaction.getBookId() + ", ");
//            System.out.println("Date due back: " + transaction.getDateDue());
//        }

        List<BooksTakenOut> listOfBooksTakenOutV2 = jdbi.withHandle(handle ->
                handle.createQuery("SELECT books_taken_out.transaction_id transaction_id, " +
                        "                       library_members.forename member_forename, " +
                        "                       library_members.surname member_surname, " +
                        "                       library_books.title book_title, " +
                        "                       books_taken_out.date_due date_due" +
                        "                FROM bookish.books_taken_out " +
                        "                INNER JOIN bookish.library_members " +
                        "                ON books_taken_out.member_id = library_members.member_id " +
                        "                INNER JOIN bookish.library_books " +
                        "                ON books_taken_out.book_id = library_books.book_id" +
                        "                ORDER BY date_due ASC")
                .mapToBean(BooksTakenOut.class)
                .list()
        );

        for (BooksTakenOut transaction : listOfBooksTakenOutV2) {
            System.out.println();
            System.out.print("Transaction ID: " + transaction.getTransactionId() + ", ");
            System.out.print("Member: " + transaction.getMember().getForename() + " " + transaction.getMember().getSurname() + ", ");
            System.out.println("Book: " + transaction.getBook().getTitle() + ", ");
            System.out.println("Date due back: " + transaction.getDateDue());
        }
    }
}
