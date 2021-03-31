package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
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
@RequestMapping("/credentialSubmit")
public class CredentialSubmitController {
    private Logger logger = LoggerFactory.getLogger(NoteSubmitController.class);

    private CredentialService credentialService;
    private UserService userService;

    public CredentialSubmitController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String credentialSubmit(Authentication authentication, @ModelAttribute("credential") Credential credential, Model model) {
        User user = this.userService.getUser(authentication.getName());

        credential.setUserid(user.getUserid());

        if (credential.getCredentialid() != null) {
            this.credentialService.updateCredential(credential);
        } else {
            Integer credentialid = this.credentialService.addCredential(credential);
        }

        return "redirect:/home";
    }
}
