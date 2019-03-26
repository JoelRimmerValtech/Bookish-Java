package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.BookModel;
import org.softwire.training.bookish.models.page.StaffPageMemberModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final MemberService memberService;
    private final BookService bookService;

    @Autowired
    public StaffController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView staffOverview() {
        return new ModelAndView("staffOverview");
    }


    //////////////////////////////      BOOKS ROUTES        ////////////////////////////////////////
    @RequestMapping("/books")
    ModelAndView books() {
        List<Book> bookList = bookService.getBooks("");
        BookModel bookModel = new BookModel();
        bookModel.setBookList(bookList);
        return new ModelAndView("staffBooks", "books", bookModel);
    }

    @RequestMapping("/books/add")
    RedirectView addBooks(@ModelAttribute Book book) {
        bookService.addBook(book);
        return new RedirectView("/staff/books");
    }

    @RequestMapping("/books/edit")
    RedirectView editBooks(@RequestBody Book book) {
        bookService.editBook(book);
        return new RedirectView("/staff/members");
    }

    @RequestMapping("/books/delete")
    RedirectView deleteBooks(@RequestBody Book book) {
        bookService.deleteBook(book.getBookId());
        return new RedirectView("/staff/books");
    }
    //////////////////////////////      BOOKS ROUTES        ////////////////////////////////////////

    @RequestMapping("/checkbook")
    ModelAndView checkOutIn() {
        return new ModelAndView("checkin");
    }


    //////////////////////////////      MEMBERS ROUTES        ////////////////////////////////////////
    @RequestMapping("/members")
    ModelAndView members() {
        List<Member> memberList = memberService.getMembers();
        StaffPageMemberModel staffPageMemberModel = new StaffPageMemberModel();
        staffPageMemberModel.setMemberList(memberList);
        return new ModelAndView("staffMembers", "members", staffPageMemberModel);
    }

    @RequestMapping("/members/add")
    RedirectView addMembers(@ModelAttribute Member member) {
        memberService.addMember(member);
        return new RedirectView("/staff/members");
    }

    @RequestMapping("/members/edit")
    RedirectView editMembers(@RequestBody Member member) {
        memberService.editMember(member);
        return new RedirectView("/staff/members");
    }

    @RequestMapping("/members/delete")
    RedirectView deleteMembers(@RequestBody Member member) {
        memberService.deleteMember(member.getMemberId());
        return new RedirectView("/staff/members");
    }
    //////////////////////////////      MEMBERS ROUTES        ////////////////////////////////////////



}
