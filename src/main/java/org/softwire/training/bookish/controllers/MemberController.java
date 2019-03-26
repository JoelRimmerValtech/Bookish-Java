package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.BookModel;
import org.softwire.training.bookish.models.page.StaffPageMemberModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/staff/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
    }

    @RequestMapping("")
    ModelAndView members() {
        List<Member> memberList = memberService.getMembers();
        StaffPageMemberModel staffPageMemberModel = new StaffPageMemberModel();
        staffPageMemberModel.setMemberList(memberList);
        return new ModelAndView("staffMembers", "members", staffPageMemberModel);
    }

    @RequestMapping("/add")
    RedirectView addMembers(@ModelAttribute Member member) {
        memberService.addMember(member);
        return new RedirectView("/staff/members");
    }

    @RequestMapping("/edit")
    RedirectView editMembers(@RequestBody Member member) {
        memberService.editMember(member);
        return new RedirectView("/staff/members");
    }

    @RequestMapping("/delete")
    RedirectView deleteMembers(@RequestBody Member member) {
        memberService.deleteMember(member.getMemberId());
        return new RedirectView("/staff/members");
    }

}
