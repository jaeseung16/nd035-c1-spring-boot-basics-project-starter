package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/credentialDelete")
public class CredentialDeleteController {
    private CredentialService credentialService;

    public CredentialDeleteController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
    public String deleteNote(@RequestParam("id") Integer id, Model model) {
        Integer numberOfDeletedCredebtuaks = credentialService.deleteCredential(id);
        Boolean success = numberOfDeletedCredebtuaks == 1;

        model.addAttribute("success", success);
        model.addAttribute("error", !success);
        model.addAttribute("errorMessage", "There was an error deleting a credential. Please try again.");
        return "result";
    }
}
