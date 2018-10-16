package com.munchies.controllers;

import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.RestaurantService;
import com.munchies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/zaposleni")
    public ModelAndView zaposleniPage(ModelAndView model) {
        List<User> usersList = userService.getAllUsers();
        model.addObject("usersList", usersList);
        model.setViewName("zaposleni");
        return model;
    }

    @GetMapping("/restorani")
    public ModelAndView restoraniPage(ModelAndView model) {
        List<Restaurant> restList = restaurantService.getAllRest();
        model.addObject("restList", restList);
        model.setViewName("restorani");
        return model;
    }

}
