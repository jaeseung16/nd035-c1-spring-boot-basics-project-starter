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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTabTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private HomePage homePage;
    private ResultPage resultPage;

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
        new LoginPage(driver).login();

        wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage = new HomePage(driver);

        homePage.navToCredentials();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-credential-button")));

        resultPage = new ResultPage(driver);
    }

    @Test
    public void testAddCredential() {
        int numberOfCredentials = homePage.numberOfCredentials();

        homePage.clickAddCredential();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> webDriver.findElement(By.id("credentialModal")));

        homePage.addCredential();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("result-success-link")));

        resultPage.clickSuccessLink();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.navToCredentials();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-credential-button")));

        assertEquals(numberOfCredentials + 1, homePage.numberOfCredentials());
    }

    @Test
    public void testDeleteCredential() {
        int numberOfCredentials = homePage.numberOfCredentials();

        homePage.clickAddCredential();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> webDriver.findElement(By.id("credentialModal")));

        homePage.addCredential();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("result-success-link")));

        resultPage.clickSuccessLink();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.navToCredentials();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-credential-button")));

        homePage.deleteCredential();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("result-success-link")));

        resultPage.clickSuccessLink();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.navToCredentials();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-credential-button")));

        assertEquals(numberOfCredentials, homePage.numberOfCredentials());
    }
}
