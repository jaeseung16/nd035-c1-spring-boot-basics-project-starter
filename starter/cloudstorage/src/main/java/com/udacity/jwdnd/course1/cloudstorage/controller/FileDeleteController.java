package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String deleteFile(@RequestParam("id") Integer id, Model model) {
        Integer numberOfDeletedFiles = fileService.deleteFile(id);
        Boolean success = numberOfDeletedFiles == 1;

        model.addAttribute("success", success);
        model.addAttribute("error", !success);
        model.addAttribute("errorMessage", "There was an error deleting a note. Please try again.");
        return "result";
    }
}
