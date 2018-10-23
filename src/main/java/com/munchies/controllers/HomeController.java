package com.munchies.controllers;

import com.munchies.model.Order;
import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.OrderService;
import com.munchies.services.OrderItemService;
import com.munchies.services.RestaurantService;
import com.munchies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;


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
        //default error login url is /login?error - Web Security Config
        if (error != null) {
            mav.addObject("error", "Invalid username and password!");
        }
        mav.addObject("newuser", user);
        mav.setViewName("login");
        return mav;
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
    public ModelAndView createNewGroupOrder(@RequestParam("id") Long id, ModelAndView mav, Order order) {
        Restaurant restaurant = restaurantService.getOne(id).get();
        order.setRestaurant(restaurant);
        mav.addObject("order", order);
        mav.setViewName("createnewgrouporder");
        return mav;

    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.POST)
    public String submitGroupOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult, OrderItem orderItem, Model mav) {
        Restaurant r = restaurantService.getOne(order.getRestaurant().getId()).get();
    if (!bindingResult.hasErrors()) {
        order.setRestaurant(r);
        Order go = orderService.save(order);
            mav.addAttribute("grouporder", go);
        mav.addAttribute("order", orderItem);
        return "redirect:/newgrouporder?id=" + go.getId();
        } else {
        return "home";
    }
    }

    @RequestMapping(value = "/newgrouporder", method = RequestMethod.GET)
    public ModelAndView getGroupOrderForm(@RequestParam("id") Long id, OrderItem orderItem, ModelAndView mav) {
        Restaurant r = restaurantService.getOne(id).get();
        Order order = orderService.findOne(id);
        orderItem.setOrder(order);
//            orderItem = orderService.saveOne(orderItem);
            mav.addObject("rest", r);
        mav.addObject("o", orderItem);
        mav.addObject("orders", order.getOrderItems());
        mav.addObject("grouporder", order);
            mav.setViewName("newgrouporder");
            return mav;

    }

    @RequestMapping(value = "/newgrouporder", method = RequestMethod.POST)
    public ModelAndView submitOrderForm(@Valid @ModelAttribute("o") OrderItem o, BindingResult bindingResult, OrderItem orderItem, ModelAndView mav) {
        Order order = orderService.findOne(o.getOrder().getId());
        if(!bindingResult.hasErrors()){
            o.setOrder(order);
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.add(o);
            orderItemService.saveOne(o);
            order.setOrderItems(orderItems);
            orderService.save(order);
            mav.addObject("orders", orderItems);
            mav.addObject("o", orderItem);
            mav.setViewName("redirect:/newgrouporder?id=" + order.getId());
        } else{
            mav.addObject("orders", order.getOrderItems());
            mav.addObject("grouporder", order);
            mav.addObject("o", orderItem);
            mav.setViewName("redirect:/newgrouporder?id=" + order.getId());
        }
        return mav;

    }

    @RequestMapping(value = "/viewrestdetails", method = RequestMethod.GET)
    public ModelAndView newGroupOrder(@RequestParam("id") Long id, ModelAndView mav) {
        Restaurant r = restaurantService.getOne(id).get();
        mav.addObject("onerest", r);
        mav.setViewName("viewRestDetails");
        return mav;
    }

    @RequestMapping(value = "/getlistofactiveorders", method = RequestMethod.GET)
    public ModelAndView getActiveOrders() {
        ModelAndView mav = new ModelAndView();
        List<Order> activeOrders = orderService.getActiveOrders();
        if (!activeOrders.isEmpty()) {
            mav.addObject("activeOrders", activeOrders);
            mav.setViewName("/ListOfActiveOrders");
        } else {
            mav.setViewName("/home");
        }
        return mav;


    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "home";

    }


}
