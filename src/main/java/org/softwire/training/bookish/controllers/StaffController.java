package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.StaffPageMemberModel;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final MemberService memberService;

    @Autowired
    public StaffController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("")
    ModelAndView staffOverview() {
        return new ModelAndView("staffOverview");
    }

    @RequestMapping("/members")
    ModelAndView editMembers() {
        List<Member> memberList = memberService.getMembers();
        StaffPageMemberModel staffPageMemberModel = new StaffPageMemberModel();
        staffPageMemberModel.setMemberList(memberList);
        return new ModelAndView("members", "members", staffPageMemberModel);
    }

    @RequestMapping("/books")
    ModelAndView editBooks() {
        return new ModelAndView("books");
    }

    @RequestMapping("/checkbook")
    ModelAndView checkOutIn() {
        return new ModelAndView("checkin");
    }

}
