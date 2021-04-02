package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {
    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private FileService fileService;
    private UserService userService;

    public FileUploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String fileUpload(Authentication authentication, @RequestParam("file") MultipartFile file, Model model) {
        Boolean success = false;
        if (file != null && !file.isEmpty()) {
            try {
                User user = this.userService.getUser(authentication.getName());

                File aFile = new File();
                aFile.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
                aFile.setContenttype(file.getContentType());
                aFile.setFilesize(String.valueOf(file.getSize()));
                aFile.setUserid(user.getUserid());
                aFile.setFiledata(file.getBytes());

                Integer fileid = this.fileService.addFile(aFile);
                success = fileid > 0;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        model.addAttribute("success", success);
        model.addAttribute("error", !success);
        model.addAttribute("errorMessage", "There was an error uploading a file. Please try again.");
        return "result";
    }

}
