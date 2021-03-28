package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class HomePage {
    private Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "file-view-link")
    private List<WebElement> fileViewLinks;

    @FindBy(id = "file-delete-link")
    private List<WebElement> fileDeleteLinks;

    @FindBy(id = "fileUpload-button")
    private WebElement fileUploadButton;

    @FindBy(id = "fileTable")
    private WebElement fileTable;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void uploadFile() {
        try {
            File exampleFile = Paths.get(HomePage.class.getResource("/example.txt").toURI()).toFile();
            fileUpload.sendKeys(exampleFile.getAbsolutePath());
            fileUploadButton.click();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteFile() {
        if (!fileDeleteLinks.isEmpty()) {
            fileDeleteLinks.get(0).click();
        }
    }

    public int numberOfFiles() {
        return fileTable.findElements(By.xpath(".//tbody/tr")).size();
    }
}
