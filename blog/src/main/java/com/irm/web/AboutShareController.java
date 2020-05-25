package com.irm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutShareController {

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
