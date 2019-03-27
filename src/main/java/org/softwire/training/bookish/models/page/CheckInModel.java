package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BooksTakenOut;
import org.softwire.training.bookish.models.database.Member;

import java.util.List;

public class CheckInModel {
    private List<BooksTakenOut> transactions;
    private List<Member> members;
    private List<Book> books;
    private Member member = new Member();
    private Book book = new Book();


    public List<BooksTakenOut> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BooksTakenOut> transactions) {
        this.transactions = transactions;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
