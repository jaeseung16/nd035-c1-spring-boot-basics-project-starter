package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup() {
        inputFirstName.sendKeys("Jane");
        inputLastName.sendKeys("Doe");
        inputUsername.sendKeys("test");
        inputPassword.sendKeys("password");
        submitButton.click();
    }

    public Boolean isSuccessMsgDisplayed() {
        return successMsg.isDisplayed();
    }

    public Boolean isErrorMsgDisplayed() {
        return errorMsg.isDisplayed();
    }


}
