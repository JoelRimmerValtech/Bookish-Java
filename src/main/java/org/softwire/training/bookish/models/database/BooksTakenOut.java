package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.mapper.Nested;

import java.util.Date;

public class BooksTakenOut {

    private int transactionId;
    private int bookId;
    private int memberId;
    private Date dateDue;

    private Member member;
    private Book book;

    public BooksTakenOut() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public Date getDateDue() {
        return dateDue;
    }

    @Nested("member")
    public Member getMember() {
        return member;
    }

    @Nested("book")
    public Book getBook() {
        return book;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
