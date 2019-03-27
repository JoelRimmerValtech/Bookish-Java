package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.mapper.Nested;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BooksTakenOut {

    private int transactionId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDue;
    private Member member = new Member();
    private Book book = new Book();

    public BooksTakenOut() {
    }

    public int getTransactionId() {
        return transactionId;
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
