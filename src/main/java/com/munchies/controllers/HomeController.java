package com.munchies.controllers;

import com.munchies.dto.OrderDto;
import com.munchies.dto.OrderItemDto;
import com.munchies.dto.RestaurantDto;
import com.munchies.dto.UserDto;
import com.munchies.exceptions.EmailExistsException;
import com.munchies.exceptions.OrderIsNotActiveException;
import com.munchies.exceptions.OrderNotExistsException;
import com.munchies.model.OrderItem;
import com.munchies.model.Restaurant;
import com.munchies.model.User;
import com.munchies.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
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

    /**
     * <h1>Welcome to Home Controller! </h1>
     * This is the main controller for all functionality accessible to unauthenticated users;
     * */

    /**
     * Contains a list of restaurants with restaurant details
     *
     * @return ModelAndView - ModelAndView object containing a list of restaurantDto objects {@link RestaurantDto}
     */
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView goHome() {
        ModelAndView mav = new ModelAndView();
        List<RestaurantDto> restListDto = restaurantService.getAllRestListDto();
        mav.addObject("restaurants", restListDto);
        mav.setViewName("home");
        return mav;
    }

    /**
     *   Contains a log in form with "Remember me" option (Persistent token approach)
     *   Error message appears if username/password are not correct or do not exist
     *   @param user    New User object fed to the form {@link User}
     *   @param error   If a login error occurs form receives an error parameter and a message is shown
     *   @param mav     New ModelAndView object
     *   @return ModelAndView
     *    */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(User user, @RequestParam(value = "error", required = false) String error, ModelAndView mav) {
        //default error login url is /login?error - Web Security Config
        if (error != null) {
            mav.addObject("error", "Invalid username and/or password!");
        }
        mav.addObject("newuser", user);
        mav.setViewName("login");
        return mav;
    }

    /**
     * Page for registering new users
     * @
     *    */
    @GetMapping(value = "/signup")
    public String signupform(UserDto user, Model model) {
        model.addAttribute("us", user);
        return "signup";
    }

    /**
     * POST Sign Up Page - Register a new user
     * Hibernate Validation for all fields
     * FrontEnd JS validation against empty fields
     * */
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

    /**
     * GET Create New Group Order Page- Enter Creator name
     * Simple form for creating the group order
     * Successful creation saves group order to the DB and
     * leads to the Group order Main Page
     * */

    @RequestMapping(value = "/createnewgrouporder/{id}", method = RequestMethod.GET)
    public ModelAndView createNewGroupOrder(@PathVariable("id") Long id, ModelAndView mav, OrderDto order) {
        RestaurantDto restaurant = restaurantService.getOneRestDtoNoOrdersMapped(id);
        order.setRestaurant(restaurant);
        mav.addObject("order", order);
        mav.setViewName("createnewgrouporder");
        return mav;
    }

    /**
     * POST Create New Group order - Creator name is Saved
     *                             + Timeout is set to 10 minutes from current time(Default)
     *                             + chosen Restaurant ID is saved to the Order
     *                             + Order is saved to DB and added to the model
     * */

    @RequestMapping(value = "/createnewgrouporder", method = RequestMethod.POST)
    public String submitGroupOrder(@Valid @ModelAttribute("order") OrderDto order, BindingResult bindingResult, Model mav) {
        RestaurantDto r = restaurantService.getOneRestDtoNoOrdersMapped(order.getRestaurant().getId());
        if (!bindingResult.hasErrors()) {
            order.setRestaurant(r);
            OrderDto go = orderService.save(order);
            go.setOrderUrl("http://localhost:8080/newgrouporder/" + go.getId());
            OrderDto groupOrder = orderService.save(go);
            mav.addAttribute("grouporder", groupOrder);
            mav.addAttribute("order", new OrderItem());
            return "redirect:/newgrouporder/" + go.getId();
        } else {
            mav.addAttribute("order", order);
            return "/createnewgrouporder";
        }
    }

    /**
     * Get New Group Order Page - Main Group order Page
     * Shows basic Group Order information
     * Contains form for ordering individual items
     * Counts down to group order timeout
     * Shows table with current order items, their prices and total amount
     * table with order items refreshes every 2 secs (Ajax Call)
     * */

    @RequestMapping(value = "/newgrouporder/{id}", method = RequestMethod.GET)
    public ModelAndView getGroupOrderForm(@PathVariable("id") Long id, ModelAndView mav) {
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

    /**
     *
     * */

    @RequestMapping(value = "/newgrouporder", method = RequestMethod.POST)
    public ModelAndView submitOrderForm(@Valid @ModelAttribute("o") OrderItemDto o, BindingResult bindingResult, ModelAndView mav) throws OrderIsNotActiveException, OrderNotExistsException {
        OrderDto orderDto = orderService.findOneOrderDtoWithRestWithItems(o.getOrderId());
        if(!bindingResult.hasErrors()){
            orderItemService.saveOne(o);
            mav.setViewName("redirect:/newgrouporder/" + orderDto.getId());
        } else{
            mav.addObject("rest", orderDto.getRestaurant());
            mav.addObject("orders", orderDto.getOrderItems());
            mav.addObject("grouporder", orderDto);
            mav.addObject("o", o);
            mav.setViewName("/newgrouporder");
        }
        return mav;
    }

    @RequestMapping(value = "/viewrestdetails/{id}", method = RequestMethod.GET)
    public ModelAndView newGroupOrder(@PathVariable("id") Long id, ModelAndView mav) {
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

    @GetMapping(value = "/sendGroupOrderEmail/{id}")
    public ModelAndView sendEmail(@PathVariable("id") Long id) throws MessagingException {
        ModelAndView mav = new ModelAndView();
        orderService.sendOrdersEmail(id);
        List<RestaurantDto> restListDto = restaurantService.getAllRestListDto();
        mav.addObject("restaurants", restListDto);
        mav.setViewName("/home");
        return mav;

    }

    @GetMapping(value = "/sortandpage")
    public ModelAndView getPageOfRest(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        Page<Restaurant> restaurants = restaurantService.findAllPagingAndSorting(pageable);
        mav.addObject("page", restaurants);
//        List<RestaurantDto> restList = restaurantService.getAllRestListDto();
//        mav.addObject("restList", restList);
        mav.setViewName("PaginationRestaurants");
        return mav;

    }


}
