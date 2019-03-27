package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.BooksTakenOut;
import org.softwire.training.bookish.models.page.CheckInModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CheckInOutService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/staff/checkin")
public class CheckInController {

    private final CheckInOutService checkInOutService;
    private final MemberService memberService;
    private final BookService bookService;

    @Autowired
    public CheckInController(CheckInOutService checkInOutService, MemberService memberService, BookService bookService) {
        this.checkInOutService = checkInOutService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView checkIn() {
        CheckInModel model = new CheckInModel();
        model.setTransactions(checkInOutService.getCheckInOut());
        model.setBooks(bookService.getBooks(""));
        model.setMembers(memberService.getMembers());

        return new ModelAndView("checkin", "model", model);
    }

    @RequestMapping("/add")
    RedirectView addCheckIn(@ModelAttribute BooksTakenOut booksTakenOut) {
        checkInOutService.addTransaction(booksTakenOut);
        return new RedirectView("/staff/checkin");
    }

    @RequestMapping("/edit")
    RedirectView editCheckIn(@RequestBody BooksTakenOut booksTakenOut) {
        checkInOutService.editBook(booksTakenOut);
        return new RedirectView("/staff/checkin");
    }

    @RequestMapping("/delete")
    RedirectView deleteCheckIn(@RequestBody BooksTakenOut booksTakenOut) {
        checkInOutService.deleteBook(booksTakenOut.getTransactionId());
        return new RedirectView("/staff/checkin");
    }


}
