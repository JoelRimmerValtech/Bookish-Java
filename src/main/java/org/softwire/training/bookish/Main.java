package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;

import java.sql.*;
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
            System.out.println(book.getTitle());
        }



    }
}
