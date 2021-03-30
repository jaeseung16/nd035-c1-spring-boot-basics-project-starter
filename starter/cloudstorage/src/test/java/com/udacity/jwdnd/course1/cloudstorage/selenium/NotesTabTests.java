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
public class NotesTabTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private HomePage homePage;

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

        homePage.navToNotes();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-note-button")));
    }

    @Test
    public void testAddNote() throws Exception {
        int numberOfNotes = homePage.numberOfNotes();

        homePage.clickAddNote();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> webDriver.findElement(By.id("noteModal")));

        homePage.addNote();
        wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.navToNotes();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-note-button")));

        assertEquals(numberOfNotes + 1, homePage.numberOfNotes());
    }

    @Test
    public void testDeleteNote() {
        int numberOfNotes = homePage.numberOfNotes();

        homePage.clickAddNote();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> webDriver.findElement(By.id("noteModal")));

        homePage.addNote();
        wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.navToNotes();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-note-button")));

        homePage.deleteNote();
        wait = new WebDriverWait(driver, 2);
        wait.until(webDriver -> webDriver.findElement(By.id("fileUpload-button")));

        homePage.navToNotes();
        wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> webDriver.findElement(By.id("add-note-button")));

        assertEquals(numberOfNotes, homePage.numberOfNotes());
    }
}
