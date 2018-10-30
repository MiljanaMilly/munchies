package com.munchies.controllers;

import com.munchies.model.Restaurant;
import com.munchies.repositories.RestaurantJpaRepository;
import com.munchies.storage.StorageException;
import com.munchies.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @PostMapping("/uploadnewfile/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        try {
            storageService.store(file, id);
        } catch (StorageException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/restaurants";
    }
}
