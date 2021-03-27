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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;

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
        new SignupPage(driver).signup();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccess() {
        loginPage.login();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        String url = wait.until(WebDriver::getCurrentUrl);

        assertEquals("http://localhost:" + port +"/home", url);
    }

    @Test
    public void testLoginFailure() {
        loginPage.wrongLogin();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

        assertTrue(loginPage.isErrorMsgDisplayed());
    }

    @Test
    public void testClickSignup() {
        loginPage.clickSignup();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        String url = wait.until(WebDriver::getCurrentUrl);

        assertEquals("http://localhost:" + port +"/signup", url);
    }
}
