package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noteDelete")
public class NoteDeleteController {
    private NoteService noteService;

    public NoteDeleteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String deleteNote(@RequestParam("id") Integer id, Model model) {
        Integer numberOfDeletedNotes = noteService.deleteNote(id);
        Boolean success = numberOfDeletedNotes == 1;

        model.addAttribute("success", success);
        model.addAttribute("error", !success);
        model.addAttribute("errorMessage", "There was an error deleting a note. Please try again.");
        return "result";
    }
}
