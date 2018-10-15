package com.munchies.controllers;

import com.munchies.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String goHome(User user, Model model){
    model.addAttribute("user", user);
        return "login";

    }

    @PostMapping("/login?error")
    public String loginError(@RequestParam(value = "error") String err, Model model) {
        err = "Invalid Username/password";
        model.addAttribute("err", err);

        return "login";
//

    }

    @GetMapping("home")
    public String homePage() {
        return "home";

    }


}
