package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fileDelete")
public class FileDeleteController {

    private FileService fileService;

    public FileDeleteController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String deleteFile(@RequestParam("id") Integer id) {
        fileService.deleteFile(id);
        return "redirect:/home";
    }
}
