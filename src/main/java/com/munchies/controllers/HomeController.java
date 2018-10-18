package com.munchies.controllers;

import com.munchies.model.GroupOrder;
import com.munchies.model.Restaurant;
import com.munchies.model.Role;
import com.munchies.model.User;
import com.munchies.services.GroupOrderService;
import com.munchies.services.RestaurantService;
import com.munchies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private GroupOrderService groupOrderService;

    @Autowired
    private UserService userService;



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
        mav.addObject("newuser", new User());
        mav.setViewName("login");
        return mav;
    }

    @PostMapping("/login?error")
    public String loginError(Model model) {
        String err = "Invalid Username/password";
        model.addAttribute("err", err);
        return "login";

    }

    @GetMapping(value = "/signup")
    public String signupform(User user, Model model) {
        model.addAttribute("us", user);
        return "signup";

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@Valid @ModelAttribute("us") User user, BindingResult bindingResult, User newUser, ModelAndView mav) {
        if (!bindingResult.hasErrors()) {
            userService.saveUser(user);
            mav.addObject("newuser", newUser);
            mav.setViewName("/login");
        } else {
            mav.setViewName("/signup");
        }
        return mav;


    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.GET)
    public ModelAndView createNewGroupOrder(@RequestParam("id") Long id, ModelAndView mav, GroupOrder groupOrder) {
        Restaurant restaurant = restaurantService.getOne(id);
        groupOrder.setRestaurant(restaurant);
        mav.addObject("groupOrder", groupOrder);
        mav.addObject("rest", restaurant);
        mav.setViewName("createnewgrouporder");
        return mav;

    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.POST)
    public ModelAndView groupOrder(@Valid @ModelAttribute GroupOrder groupOrder, BindingResult bindingResult, ModelAndView mav) {
        if (!bindingResult.hasErrors()) {
            GroupOrder go = groupOrderService.save(groupOrder);
            mav.addObject("grouporder", go);
            mav.setViewName("newgrouporder?id=" + groupOrder.getGroup_order_id());
        } else {
            mav.setViewName("home");
        }
        return mav;
    }

    //    @RequestMapping(value = "/newgrouporder", method = RequestMethod.GET)
//    public ModelAndView groupOrderForm(@ModelAttribute GroupOrder groupOrder, ModelAndView mav) {
//        mav.addObject("grouporder", groupOrder);
//        mav.setViewName("newgrouporder");
//        return mav;
//
//    }
    @RequestMapping(value = "/newgrouporder", method = RequestMethod.GET)
    public ModelAndView sendGroupOrderForm(GroupOrder groupOrder, ModelAndView mav, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id != null) {
            groupOrder = groupOrderService.findOne(Long.parseLong(id));
            mav.addObject("grouporder", groupOrder);
            mav.setViewName("newgrouporder");
        }


        return mav;

    }

    @RequestMapping(value = "/viewrestdetails", method = RequestMethod.GET)
    public ModelAndView newGroupOrder(@RequestParam("id") Long id, ModelAndView mav) {
        Restaurant r = restaurantService.getOne(id);
        mav.addObject("onerest", r);
        mav.setViewName("viewRestDetails");
        return mav;
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "home";

    }


}
