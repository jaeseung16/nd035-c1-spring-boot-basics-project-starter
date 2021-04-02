package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private final JavascriptExecutor js;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

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

    @FindBy(id = "userTable")
    private WebElement userTable;

    @FindBy(id = "noteModalLabel")
    private WebElement noteModalLabel;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(id = "close-note-button")
    private WebElement closeNoteButton;

    @FindBy(id = "note-edit-link")
    private List<WebElement> noteEditLinks;

    @FindBy(id = "note-delete-link")
    private List<WebElement> noteDeleteLinks;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "save-credential-button")
    private WebElement saveCredentialButton;

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    @FindBy(id = "credential-edit-link")
    private List<WebElement> credentialEditLinks;

    @FindBy(id = "credential-delete-link")
    private List<WebElement> credentialDeleteLinks;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void navToFiles() {
        navFilesTab.click();
    }

    public void navToNotes() {
        navNotesTab.click();
    }

    public void navToCredentials() {
        navCredentialsTab.click();
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

    public void clickAddNote() {
        js.executeScript("arguments[0].click();", addNoteButton);
    }

    public void addNote() {
        js.executeScript("arguments[0].value='" + "title" + "';", noteTitle);
        js.executeScript("arguments[0].value='" + "description" + "';", noteDescription);
        js.executeScript("arguments[0].click();", saveNoteButton);
    }

    public void clickEditNote() {
        if (!noteEditLinks.isEmpty()) {
            js.executeScript("arguments[0].click();", noteEditLinks.get(0));
        }
    }

    public void editNote() {
        js.executeScript("arguments[0].value='" + "edited title" + "';", noteTitle);
        js.executeScript("arguments[0].value='" + "editied description" + "';", noteDescription);
        js.executeScript("arguments[0].click();", saveNoteButton);
    }

    public void deleteNote() {
        if (!noteDeleteLinks.isEmpty()) {
            js.executeScript("arguments[0].click();", noteDeleteLinks.get(0));
        }
    }

    public int numberOfNotes() {
        return userTable.findElements(By.xpath(".//tbody/tr")).size();
    }

    public void clickAddCredential() {
        js.executeScript("arguments[0].click();", addCredentialButton);
    }

    public void clickEditCredential() {
        if (!credentialEditLinks.isEmpty()) {
            js.executeScript("arguments[0].click();", credentialEditLinks.get(0));
        }
    }

    public void addCredential() {
        js.executeScript("arguments[0].value='" + "test_url" + "';", credentialUrl);
        js.executeScript("arguments[0].value='" + "test_username" + "';", credentialUsername);
        js.executeScript("arguments[0].value='" + "test_password" + "';", credentialPassword);

        js.executeScript("arguments[0].click();", saveCredentialButton);
    }

    public void editCredential() {
        js.executeScript("arguments[0].value='" + "test_url_edited" + "';", credentialUrl);
        js.executeScript("arguments[0].value='" + "test_username_edited" + "';", credentialUsername);
        js.executeScript("arguments[0].value='" + "test_password_edited" + "';", credentialPassword);

        js.executeScript("arguments[0].click();", saveCredentialButton);
    }

    public int numberOfCredentials() {
        return credentialTable.findElements(By.xpath(".//tbody/tr")).size();
    }

    public void deleteCredential() {
        if (!credentialDeleteLinks.isEmpty()) {
            js.executeScript("arguments[0].click();", credentialDeleteLinks.get(0));
        }
    }
}
