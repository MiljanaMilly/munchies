package com.munchies.controllers;

import com.munchies.exceptions.RestaurantExistsException;
import com.munchies.exceptions.RestaurantHasActiveOrdersException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(NotFoundException ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", ex.getMessage());
        mv.setViewName("/error");
        return mv;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestaurantHasActiveOrdersException.class)
    public ModelAndView restaurantHasActiveOrders(RestaurantHasActiveOrdersException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", ex.getMessage());
        mav.setViewName("/error");
        return mav;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestaurantExistsException.class)
    public ModelAndView restaurantAlreadyExists(RestaurantExistsException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", ex.getMessage());
        mav.setViewName("/error");
        return mav;

    }
}
