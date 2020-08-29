package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private String signupError = "";

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(User user) {
        logger.info("Get Login: " + user.toString());
        return "login";
    }

//    @GetMapping("/login")
//    public String getLoginPag(@RequestParam String error) {
//        logger.info("Get Login: " + user.toString());
//        return "login?error";
//    }

    // todo: I think i don't need this
    @PostMapping("/login")
    public String postLogin(User user) {
        logger.info("Post Login: " + user.toString());
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(User user, Model model) {
        logger.info("Get Signup: " + user.toString());
        model.addAttribute("signupError", signupError);
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(User user, Model model) {
        logger.info("Saving: " + user.toString());
        try {
            userService.save(user);
        } catch (RuntimeException e) {
            // todo: search for how to use this
            signupError = e.getMessage();
            model.addAttribute("signupError", signupError);
            logger.info(e.getMessage());
            return "redirect:/signup?error";
        }
        return "redirect:/signup?success";
    }
}
