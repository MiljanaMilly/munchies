package com.munchies.controllers;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.dto.RestaurantDto;
import com.munchies.dto.UserDto;
import com.munchies.exceptions.EmailExistsException;
import com.munchies.exceptions.OrderIsNotActiveException;
import com.munchies.exceptions.OrderNotExistsException;
import com.munchies.model.OrderItem;
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
    public ModelAndView goHome(ModelAndView mav) {
        List<RestaurantDto> restListDto = restaurantService.getAllRestListDto();
        mav.addObject("restaurants", restListDto);
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(User user, @RequestParam(value = "error", required = false) String error,ModelAndView mav) {
        //default error login url is /login?error - Web Security Config
        if (error != null) {
            mav.addObject("error", "Invalid username and/or password!");
        }
        mav.addObject("newuser", user);
        mav.setViewName("login");
        return mav;
    }

    @GetMapping(value = "/signup")
    public String signupform(UserDto user, Model model) {
        model.addAttribute("us", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@Valid @ModelAttribute("us") UserDto user, BindingResult bindingResult, UserDto newUser, ModelAndView mav) throws EmailExistsException {
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
    public ModelAndView createNewGroupOrder(@RequestParam("id") Long id, ModelAndView mav, OrderDto order) {
        RestaurantDto restaurant = restaurantService.getOneRestDtoNoOrdersMapped(id);
        order.setRestaurant(restaurant);
        mav.addObject("order", order);
        mav.setViewName("createnewgrouporder");
        return mav;
    }

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.POST)
    public String submitGroupOrder(@Valid @ModelAttribute("order") OrderDto order, BindingResult bindingResult, Model mav) {
        RestaurantDto r = restaurantService.getOneRestDtoNoOrdersMapped(order.getRestaurant().getId());
    if (!bindingResult.hasErrors()) {
        order.setRestaurant(r);
        OrderDto go = orderService.save(order);
        mav.addAttribute("grouporder", go);
        mav.addAttribute("order", new OrderItem());
        return "redirect:/newgrouporder?id=" + go.getId();
        } else {
        mav.addAttribute("order", order);
        return "/createnewgrouporder";
    }
    }

    @RequestMapping(value = "/newgrouporder", method = RequestMethod.GET)
    public ModelAndView getGroupOrderForm(@RequestParam("id") Long id, ModelAndView mav) {
        OrderDto order = orderService.findOneOrderDtoWithRestWithItems(id);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderId(order.getId());
        mav.addObject("rest", order.getRestaurant());
        mav.addObject("o", orderItemDto);
        mav.addObject("orders", order.getOrderItems());
        mav.addObject("grouporder", order);
        mav.setViewName("newgrouporder");
        return mav;

    }

    @RequestMapping(value = "/newgrouporder", method = RequestMethod.POST)
    public ModelAndView submitOrderForm(@Valid @ModelAttribute("o") OrderItemDto o, BindingResult bindingResult, ModelAndView mav) throws OrderIsNotActiveException, OrderNotExistsException {
        OrderDto orderDto = orderService.findOneOrderDtoWithRestWithItems(o.getOrderId());
        if(!bindingResult.hasErrors()){
            orderItemService.saveOne(o);
            mav.setViewName("redirect:/newgrouporder?id=" + orderDto.getId());
        } else{
            mav.addObject("orders", orderDto.getOrderItems());
            mav.addObject("grouporder", orderDto);
            mav.addObject("o", new OrderItem());
            mav.setViewName("redirect:/newgrouporder?id=" + orderDto.getId());
        }
        return mav;
    }

    @RequestMapping(value = "/viewrestdetails", method = RequestMethod.GET)
    public ModelAndView newGroupOrder(@RequestParam("id") Long id, ModelAndView mav) {
        RestaurantDto r = restaurantService.getOneRestDtoNoOrdersMapped(id);
        mav.addObject("onerest", r);
        mav.setViewName("viewRestDetails");
        return mav;
    }

    @RequestMapping(value = "/getlistofactiveorders", method = RequestMethod.GET)
    public ModelAndView getActiveOrders() {
        ModelAndView mav = new ModelAndView();
        List<OrderDto> activeOrders = orderService.getActiveOrders();
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
