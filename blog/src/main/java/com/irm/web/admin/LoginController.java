package com.irm.web.admin;

import com.irm.po.User;
import com.irm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping
    public String loginPage() {
        return "admin/login";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password ,
                        HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            System.out.println(user.getNickname());
            session.setAttribute("user", user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
