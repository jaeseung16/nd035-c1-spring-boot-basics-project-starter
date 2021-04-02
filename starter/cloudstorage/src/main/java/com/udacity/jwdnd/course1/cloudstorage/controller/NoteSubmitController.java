package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noteSubmit")
public class NoteSubmitController {
    private Logger logger = LoggerFactory.getLogger(NoteSubmitController.class);

    private NoteService noteService;
    private UserService userService;

    public NoteSubmitController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String noteSubmit(Authentication authentication, @ModelAttribute("note") Note note, Model model) {
        User user = this.userService.getUser(authentication.getName());
        note.setUserid(user.getUserid());

        Boolean success;
        if (note.getNoteid() != null) {
            Integer numberOfUpdatedRows = noteService.updateNote(note);
            success = numberOfUpdatedRows == 1;
        } else {
            Integer noteid = noteService.addNote(note);
            success = noteid > 0;
        }

        model.addAttribute("success", success);
        model.addAttribute("error", !success);
        model.addAttribute("errorMessage", "There was an error submitting a note. Please try again.");
        return "result";
    }
}
