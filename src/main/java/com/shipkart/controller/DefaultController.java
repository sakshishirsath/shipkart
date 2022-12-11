package com.shipkart.controller;

import com.shipkart.entity.User;
import com.shipkart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    private final UserService userService;

    @Autowired
    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User sessionUser) {
        if (sessionUser != null) {
            User user = userService.getUserById(sessionUser.getId());
            model.addAttribute("user", user);
        }

        return "index";
    }

}
