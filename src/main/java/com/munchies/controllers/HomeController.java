package com.munchies.controllers;

import com.munchies.model.GroupOrder;
import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView goHome(ModelAndView mav, Restaurant restaurant) {
        List<Restaurant> restList = restaurantService.getAllRest();
        mav.addObject("restaurants", restList);
        mav.addObject("rest", restaurant);
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
//
//    @GetMapping("/home")
//    public ModelAndView homePage(ModelAndView mav , Restaurant restaurant) {
//        List<Restaurant> restList = restaurantService.getAllRest();
//        mav.addObject("restaurants", restList);
//        mav.addObject("onerest",restaurant);
//        mav.setViewName("home");
//        return mav;
//
//    }

    @GetMapping(value = "/signup")
    public String signupform(User user, Model model) {
        model.addAttribute(user);
        return "signup";

    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView createNewGroupOrder(@RequestParam(name = "restaurantID") Long id, ModelAndView mav, GroupOrder groupOrder) {
        mav.addObject("groupOrder", groupOrder);
        mav.addObject("rest", restaurantService.getOne(id));
        mav.setViewName("createnewgrouporder");
        return mav;

    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.GET)
    public ModelAndView newGroupOrder(@ModelAttribute Restaurant rest, ModelAndView mav) {
        mav.addObject("rest", rest);
        mav.setViewName("createnewgrouporder");
        return mav;

    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.POST)
    public ModelAndView groupOrder(ModelAndView mav) {
        mav.setViewName("createnewgrouporder");
        return mav;

    }

    @RequestMapping(value = "/viewrestdetails", method = RequestMethod.GET)
    public ModelAndView newGroupOrder(ModelAndView mav) {
        mav.setViewName("viewRestDetails");
        return mav;
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "home";

    }


}
