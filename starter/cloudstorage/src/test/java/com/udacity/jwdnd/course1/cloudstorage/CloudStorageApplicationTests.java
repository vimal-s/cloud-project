package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.NotesPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static com.udacity.jwdnd.course1.cloudstorage.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        // todo: what is the use of this?
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CloudStorageApplicationTests {

    //	@LocalServerPort
    private int port = 8080;
    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void requestGetsRedirectedTest() throws InterruptedException {
        driver.get(DOMAIN + ":" + PORT + APP_ENDPOINT);
        assertEquals(LOGIN_PAGE_TITLE, driver.getTitle());
    }

    @Test
    public void signupPageAccessibleTest() {
        driver.get(DOMAIN + ":" + PORT + SIGNUP_ENDPOINT);
        assertEquals(SIGNUP_PAGE_TITLE, driver.getTitle());
    }

    @Test
    public void accessPossibleTest() throws InterruptedException {
        driver.get(DOMAIN + ":" + PORT + SIGNUP_ENDPOINT);
        Thread.sleep(2000);
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(SIGNUP_FIRSTNAME, SIGNUP_LASTNAME, SIGNUP_USERNAME, SIGNUP_PASSWORD);

        driver.get(DOMAIN + ":" + PORT + LOGIN_ENDPOINT);
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
        Thread.sleep(2000);

        NotesPage notesPage = new NotesPage(driver);
        notesPage.createNote(NOTE_TITLE, NOTE_DESCRIPTION);
        assertEquals(NOTE_TITLE, notesPage.getSavedNoteTitle());
        assertEquals(NOTE_DESCRIPTION, notesPage.getSavedNoteDescription());
        notesPage.deleteNote();

        notesPage.logout();
        assertThrows(NoSuchElementException.class, notesPage::getSavedNoteTitle);
    }
}
