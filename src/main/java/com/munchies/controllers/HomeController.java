package com.munchies.controllers;

import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView goHome(ModelAndView mav) {
        List<Restaurant> restList = restaurantService.getAllRest();
        mav.addObject("restaurants", restList);
        mav.setViewName("home");
        return mav;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(ModelAndView mav) {
        mav.addObject("user", new User());
        mav.setViewName("login");
        return mav;
    }

    @PostMapping("/login?error")
    public String loginError(Model model) {
        String err = "Invalid Username/password";
        model.addAttribute("err", err);
        return "login";

    }

    @GetMapping("/home")
    public String homePage() {
        return "home";

    }

    @GetMapping(value = "/signup")
    public String signupform(User user, Model model) {
        model.addAttribute(user);
        return "signup";

    }

    @GetMapping("/createnewgrouporder/{restaurantId}")
    public ModelAndView createGroupOrder(@PathVariable("restaurantId") Long id, ModelAndView mav) {
        Optional<Restaurant> r = restaurantService.getOne(id);
        mav.addObject("restaurant", r);
        mav.setViewName("createnewgrouporder");
        return mav;


    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "home";

    }


}
