package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.StaffPageMemberModel;
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

    @Autowired
    public StaffController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("")
    ModelAndView staffOverview() {
        return new ModelAndView("staffOverview");
    }

    @RequestMapping("/members")
    ModelAndView members() {
        List<Member> memberList = memberService.getMembers();
        StaffPageMemberModel staffPageMemberModel = new StaffPageMemberModel();
        staffPageMemberModel.setMemberList(memberList);
        return new ModelAndView("staffMembers", "members", staffPageMemberModel);
    }


    @RequestMapping("/members/add")
    RedirectView addMembers(@ModelAttribute Member member) {
//        Member member = new Member();
//        member.setForename(fname);
//        member.setSurname(sname);
//        member.setLibrarian(librarian);
        memberService.addMember(member);
        List<Member> memberList = memberService.getMembers();
        StaffPageMemberModel staffPageMemberModel = new StaffPageMemberModel();
        staffPageMemberModel.setMemberList(memberList);
        return new RedirectView("/staff");
    }

    @RequestMapping("/members/edit")
    RedirectView editMembers(@RequestBody Member member) {
//        Member member = new Member();
//        member.setForename(fname);
//        member.setSurname(sname);
//        member.setLibrarian(librarian);
        memberService.editMember(member);
        return new RedirectView("/staff");
    }

    @RequestMapping("/books")
    ModelAndView editBooks() {
        return new ModelAndView("staffBooks");
    }

    @RequestMapping("/checkbook")
    ModelAndView checkOutIn() {
        return new ModelAndView("checkin");
    }

}
