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
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private final long OneGB = 1073741824;

    private FileService fileService;
    private UserService userService;

    public FileUploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String fileUpload(Authentication authentication, @RequestParam("file") MultipartFile file, Model model) {
        Boolean success = false;
        String errorMessage = "";
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > OneGB) {
                success = false;
                errorMessage = "File cannot be uploaded. File size exceeds 1 GB.";
            } else {
                try {
                    User user = userService.getUser(authentication.getName());

                    String filename = file.getOriginalFilename();

                    if (fileService.isFilenameAvailable(filename)) {
                        File aFile = new File();
                        aFile.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
                        aFile.setContenttype(file.getContentType());
                        aFile.setFilesize(String.valueOf(file.getSize()));
                        aFile.setUserid(user.getUserid());
                        aFile.setFiledata(file.getBytes());

                        Integer fileid = fileService.addFile(aFile);
                        success = fileid > 0;
                    } else {
                        success = false;
                        errorMessage = "There was an error uploading a file. Please try again.";
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    success = false;
                    errorMessage = "There was an error uploading a file. Please try again.";
                }
            }
        }

        model.addAttribute("success", success);
        model.addAttribute("error", !success);
        model.addAttribute("errorMessage", errorMessage);
        return "result";
    }

}
