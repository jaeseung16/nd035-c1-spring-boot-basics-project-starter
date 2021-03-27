package com.udacity.jwdnd.course1.cloudstorage.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private SignupPage signupPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/signup");
        signupPage = new SignupPage(driver);
    }

    @Test
    public void testSignupSuccess() {
        signupPage.signup();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

        assertTrue(signupPage.isSuccessMsgDisplayed());
    }

    @Test
    public void testSignupError() {
        signupPage.signup();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

        signupPage.signup();
        wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

        assertTrue(signupPage.isErrorMsgDisplayed());
    }
}
