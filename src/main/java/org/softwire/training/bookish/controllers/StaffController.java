package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.StaffPageMemberModel;
import org.softwire.training.bookish.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping("")
    ModelAndView staffOverview() {
        return new ModelAndView("staffOverview");
    }

    @RequestMapping("/members")
    ModelAndView editMembers() {
        List<Member> memberList = staffService.getMembers();
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
