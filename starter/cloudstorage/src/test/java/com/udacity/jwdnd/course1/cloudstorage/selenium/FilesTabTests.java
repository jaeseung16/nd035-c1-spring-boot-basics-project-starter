package com.udacity.jwdnd.course1.cloudstorage.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilesTabTests {

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

        resultPage = new ResultPage(driver);
    }

    @Test
    public void uploadFile() {
        int numberOfFiles = homePage.numberOfFiles();

        homePage.uploadFile();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("result-success-link")));

        resultPage.clickSuccessLink();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        assertEquals(numberOfFiles + 1, homePage.numberOfFiles());
    }

    @Test
    public void deleteFile() {
        int numberOfFiles = homePage.numberOfFiles();

        homePage.uploadFile();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("result-success-link")));

        resultPage.clickSuccessLink();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.deleteFile();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("result-success-link")));

        resultPage.clickSuccessLink();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        assertEquals(numberOfFiles, homePage.numberOfFiles());
    }
}
