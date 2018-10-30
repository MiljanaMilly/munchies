package com.munchies.controllers;

import com.munchies.model.Restaurant;
import com.munchies.repositories.RestaurantJpaRepository;
import com.munchies.storage.StorageException;
import com.munchies.storage.StorageFileNotFoundException;
import com.munchies.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;


    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

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

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename) throws StorageFileNotFoundException {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
