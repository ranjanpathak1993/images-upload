package com.app.upload.controller;

import com.app.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/upload-page")
    public String showUploadPage(@RequestParam String number, Model model) {
        model.addAttribute("number", number);
        return "upload";
    }

    @PostMapping("/save-images")
    public String saveImages(@RequestParam String number,
                             @RequestParam("files") MultipartFile[] files,
                             Model model) {

        uploadService.saveImages(number, files);
        model.addAttribute("number", number);
        return "view";
    }

    @GetMapping("/view")
    public String viewImages(@RequestParam String number, Model model) {
        model.addAttribute("images", uploadService.loadImages(number));
        model.addAttribute("number", number);
        return "view";
    }
}
