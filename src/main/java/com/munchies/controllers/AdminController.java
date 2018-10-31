package com.munchies.controllers;

import com.munchies.dto.RestaurantDto;
import com.munchies.dto.UserDto;
import com.munchies.exceptions.RestaurantExistsException;
import com.munchies.exceptions.RestaurantHasActiveOrdersException;
import com.munchies.model.Restaurant;
import com.munchies.services.RestaurantService;
import com.munchies.storage.StorageException;
import com.munchies.storage.StorageService;
import com.munchies.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StorageService storageService;


    @GetMapping("/employees")
    public ModelAndView employeesPage(ModelAndView model) {
        List<UserDto> usersList = userService.getAllDtoUsers();
        model.addObject("usersList", usersList);
        model.setViewName("admin/employees");
        return model;
    }

    @GetMapping("/restaurants")
    public ModelAndView restaurantsPage(ModelAndView model) {
        List<RestaurantDto> restList = restaurantService.getAllRestListDto();
        model.addObject("restList", restList);
        model.setViewName("admin/restaurants");
        return model;
    }

    @GetMapping("/createnewrestaurant")
    public ModelAndView createnewrestaurant(ModelAndView mav) {
        mav.addObject("newrest", new RestaurantDto());
        mav.setViewName("admin/createnewrestaurant");
        return mav;

    }

    @PostMapping("/createnewrestaurant")
    public ModelAndView saveNewRestaurant(@Valid @ModelAttribute("newrest") RestaurantDto restaurant, BindingResult bindingResult, @RequestParam MultipartFile file,
                                          RedirectAttributes redirectAttributes, ModelAndView mav) throws RestaurantExistsException, StorageException {
        if (!bindingResult.hasErrors()) {
            String fileName = storageService.storeFile(file);
            restaurant.setMenuUrl(fileName);
            RestaurantDto savedRest = restaurantService.saveOne(restaurant);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            mav.setViewName("redirect:/restaurants");
        } else {
            mav.addObject("newrest", restaurant);
            mav.setViewName("admin/createnewrestaurant");
        }
        return mav;
    }

    @GetMapping("/deleterest")
    public ModelAndView deleteRest(@RequestParam("id") Long id, ModelAndView mav) throws NotFoundException, RestaurantHasActiveOrdersException {
        storageService.deleteFilesByRestaurantId(id);
        restaurantService.deleteRestById(id);
        mav.setViewName("redirect:/restaurants");
        return mav;

    }

    @GetMapping("/editrestaurant")
    public ModelAndView editRest(@RequestParam("id") Long id, ModelAndView mav) {
        mav.addObject("editrest", restaurantService.getOneRestDtoNoOrdersMapped(id));
        mav.setViewName("admin/editrestaurant");
        return mav;

    }

    @PostMapping("/editrestaurant")
    public ModelAndView editRestaurant(@Valid @ModelAttribute("editrest") RestaurantDto restaurant, BindingResult bindingResult, @RequestParam MultipartFile file,
                                       RedirectAttributes redirectAttributes, ModelAndView mav) throws StorageException {
        if (!bindingResult.hasErrors()) {
            String fileUrl = storageService.store(file, restaurant.getId());
            restaurant.setMenuUrl(fileUrl);
            Restaurant editedRest = restaurantService.editOne(restaurant);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            mav.setViewName("redirect:/restaurants");
        } else {
            mav.addObject("editrest", restaurant);
            mav.setViewName("admin/editrestaurant");
        }
        return mav;

    }


}
