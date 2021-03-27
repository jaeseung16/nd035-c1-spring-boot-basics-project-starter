package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    @FindBy(id = "logout-msg")
    private WebElement logoutMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login() {
        inputUsername.sendKeys("test");
        inputPassword.sendKeys("password");
        submitButton.click();
    }

    public void wrongLogin() {
        inputUsername.sendKeys("test");
        inputPassword.sendKeys("pass");
        submitButton.click();
    }

    public Boolean isErrorMsgDisplayed() {
        return errorMsg.isDisplayed();
    }

    public void clickSignup() {
        signupLink.click();
    }
}
