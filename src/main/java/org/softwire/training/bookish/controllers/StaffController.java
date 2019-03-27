package org.softwire.training.bookish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @RequestMapping("")
    ModelAndView staffOverview() {
        return new ModelAndView("staffOverview");
    }
}
