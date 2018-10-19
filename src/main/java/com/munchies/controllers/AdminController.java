package com.munchies.controllers;

import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.RestaurantService;
import com.munchies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/zaposleni")
    public ModelAndView zaposleniPage(ModelAndView model) {
        List<User> usersList = userService.getAllUsers();
        model.addObject("usersList", usersList);
        model.setViewName("admin/zaposleni");
        return model;
    }

    @GetMapping("/restorani")
    public ModelAndView restoraniPage(ModelAndView model) {
        List<Restaurant> restList = restaurantService.getAllRest();
        model.addObject("restList", restList);
        model.setViewName("admin/restorani");
        return model;
    }

    @GetMapping("/createnewrestaurant")
    public ModelAndView createnewrestaurant(ModelAndView mav, Restaurant restaurant) {
        mav.addObject("newrest", restaurant);
        mav.setViewName("admin/createnewrestaurant");
        return mav;

    }

    @PostMapping("/createnewrestaurant")
    public ModelAndView saveNewRestaurant(@Valid @ModelAttribute("newrest") Restaurant restaurant, BindingResult bindingResult, Restaurant rest, ModelAndView mav) {
        if (!bindingResult.hasErrors()) {
            restaurantService.saveOne(restaurant);
            mav.setViewName("redirect:/restorani");
        } else {
            mav.addObject("newrest", rest);
            mav.setViewName("admin/createnewrestaurant");
        }
        return mav;

    }

    @GetMapping("/deleterest")
    public ModelAndView deleteRest(@RequestParam("id") Long id, ModelAndView mav) {
        restaurantService.deleteRestById(id);
        mav.setViewName("redirect:/restorani");
        return mav;

    }

    @GetMapping("/editrestaurant")
    public ModelAndView editRest(@RequestParam("id") Long id, ModelAndView mav) {
        mav.addObject("editrest", restaurantService.getOne(id));
        System.out.println(id.toString());
        mav.setViewName("admin/editrestaurant");
        return mav;

    }

    @PostMapping("/editrestaurant")
    public ModelAndView editRestaurant(@Valid @ModelAttribute("editrest") Restaurant restaurant, BindingResult bindingResult, ModelAndView mav) {
        if (!bindingResult.hasErrors()) {
            restaurantService.editOne(restaurant);
            mav.setViewName("redirect:/restorani");
        } else {
            mav.addObject("editrest", restaurant);
            mav.setViewName("redirect:/editrestaurant");
        }
        return mav;

    }


}
