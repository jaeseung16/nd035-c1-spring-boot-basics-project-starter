package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;

    HomeController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String homeView(Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());

        model.addAttribute("files", this.fileService.getFiles(user.getUserid()));
        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserid()));
        model.addAttribute("note", new Note());
        model.addAttribute("credential", new Credential());

        return "home";
    }

}
