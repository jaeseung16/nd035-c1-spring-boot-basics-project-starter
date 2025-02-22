package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    private final JavascriptExecutor js;

    @FindBy(id = "result-success-link")
    private WebElement resultSuccessLink;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void clickSuccessLink() {
        js.executeScript("arguments[0].click();", resultSuccessLink);
    }
}
