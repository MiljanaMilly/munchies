package com.munchies.controllers;

import com.munchies.exceptions.RestaurantHasActiveOrdersException;
import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.RestaurantService;
import com.munchies.services.UserService;
import javassist.NotFoundException;
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

    @GetMapping("/employees")
    public ModelAndView employeesPage(ModelAndView model) {
        List<User> usersList = userService.getAllUsers();
        model.addObject("usersList", usersList);
        model.setViewName("admin/employees");
        return model;
    }

    @GetMapping("/restaurants")
    public ModelAndView restaurantsPage(ModelAndView model) {
        List<Restaurant> restList = restaurantService.getAllRest();
        model.addObject("restList", restList);
        model.setViewName("admin/restaurants");
        return model;
    }

    @GetMapping("/createnewrestaurant")
    public ModelAndView createnewrestaurant(ModelAndView mav) {
        mav.addObject("newrest", new Restaurant());
        mav.setViewName("admin/createnewrestaurant");
        return mav;

    }

    @PostMapping("/createnewrestaurant")
    public ModelAndView saveNewRestaurant(@Valid @ModelAttribute("newrest") Restaurant restaurant, BindingResult bindingResult, ModelAndView mav) {
        if (!bindingResult.hasErrors()) {
            restaurantService.saveOne(restaurant);
            mav.setViewName("redirect:/restaurants");
        } else {
            mav.addObject("newrest", restaurant);
            mav.setViewName("admin/createnewrestaurant");
        }
        return mav;

    }

    @GetMapping("/deleterest")
    public ModelAndView deleteRest(@RequestParam("id") Long id, ModelAndView mav) throws NotFoundException, RestaurantHasActiveOrdersException {
        System.out.println(id);
        restaurantService.deleteRestById(id);
        mav.setViewName("redirect:/restaurants");
        return mav;

    }

    @GetMapping("/editrestaurant")
    public ModelAndView editRest(@RequestParam("id") Long id, ModelAndView mav) {
        mav.addObject("editrest", restaurantService.getOne(id).get());
        mav.setViewName("admin/editrestaurant");
        return mav;

    }

    @PostMapping("/editrestaurant")
    public ModelAndView editRestaurant(@Valid @ModelAttribute("editrest") Restaurant restaurant, BindingResult bindingResult, ModelAndView mav) {
        if (!bindingResult.hasErrors()) {
            restaurantService.editOne(restaurant);
            mav.setViewName("redirect:/restaurants");
        } else {
            mav.addObject("editrest", restaurant);
            mav.setViewName("admin/editrestaurant");
        }
        return mav;

    }


}
