package com.munchies.controllers;

import com.munchies.model.GroupOrder;
import com.munchies.model.Order;
import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.GroupOrderService;
import com.munchies.services.OrderService;
import com.munchies.services.RestaurantService;
import com.munchies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView goHome(ModelAndView mav, Restaurant restaurant) {
        List<Restaurant> restList = restaurantService.getAllRest();
        mav.addObject("restaurants", restList);
        mav.addObject("rest", restaurant);
        mav.setViewName("home");
        return mav;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(ModelAndView mav, User user, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            mav.addObject("error", "Invalid username and password!");
        }
        mav.addObject("newuser", user);
        mav.setViewName("login");
        return mav;
    }

//    @PostMapping("/login?error")
//    public String loginError(Model model) {
//        String err = "Invalid Username/password";
//        model.addAttribute("err", err);
//        return "login";
//
//    }

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
    public ModelAndView createNewGroupOrder(@RequestParam("id") Long id, ModelAndView mav, GroupOrder groupOrder,Restaurant restaurant) {
        restaurant = restaurantService.getOne(id);
        groupOrder.setRestaurant(restaurant);
        mav.addObject("groupOrder", groupOrder);
        mav.setViewName("createnewgrouporder");
        return mav;

    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.POST)
    public String submitGroupOrder(@Valid @ModelAttribute("groupOrder")GroupOrder groupOrder, BindingResult bindingResult,Order order, Model mav) {
        Restaurant r = restaurantService.getOne(groupOrder.getRestaurant().getRestaurant_id());
    if (!bindingResult.hasErrors()) {
            groupOrder.setRestaurant(r);
            GroupOrder go = groupOrderService.save(groupOrder);
            mav.addAttribute("grouporder", go);
            mav.addAttribute("order", order);
           return "redirect:/newgrouporder?id=" + go.getGroup_order_id();
        } else {
        return "home";
    }
    }

    @RequestMapping(value = "/newgrouporder", method = RequestMethod.GET)
    public ModelAndView getGroupOrderForm(@RequestParam("id") Long id,Order order, ModelAndView mav) {
        Restaurant r = restaurantService.getOne(id);
        System.out.println(id);
        GroupOrder groupOrder = groupOrderService.findOne(id);
        order.setGroupOrder(groupOrder);
//            order = orderService.saveOne(order);
            mav.addObject("rest", r);
            mav.addObject("o", order);
        mav.addObject("orders", groupOrder.getOrder());
            mav.addObject("grouporder", groupOrder );
            mav.setViewName("newgrouporder");
            return mav;

    }
    @RequestMapping(value = "/newgrouporder", method = RequestMethod.POST)
    public ModelAndView submitOrderForm(@Valid @ModelAttribute("o")Order o,BindingResult bindingResult, Order order, ModelAndView mav){
        GroupOrder groupOrder = groupOrderService.findOne(o.getGroupOrder().getGroup_order_id());
        System.out.println(groupOrder.getGroup_order_id());
        if(!bindingResult.hasErrors()){
            o.setGroupOrder(groupOrder);
            List<Order> orders = groupOrder.getOrder();
            orders.add(o);
            orderService.saveOne(o);
            groupOrder.setOrder(orders);
            groupOrderService.save(groupOrder);
            mav.addObject("orders", orders);
            mav.addObject("o", order);
            mav.setViewName("redirect:/newgrouporder?id=" + groupOrder.getGroup_order_id());
        } else{
            mav.addObject("orders", groupOrder.getOrder());
            mav.addObject("grouporder", groupOrder);
            mav.addObject("o", order);
            mav.setViewName("redirect:/newgrouporder?id=" + groupOrder.getGroup_order_id());
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
