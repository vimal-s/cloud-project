package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.NotesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.Not;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(/*webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT*/)
class CloudStorageApplicationTests {

    private final String url = "http://localhost";

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
    public void getLoginPage() throws InterruptedException {
        driver.get(url + ":" + this.port + "/login");
        Assertions.assertEquals("Login Page", driver.getTitle());
    }

    @Test
    public void addedNoteIsDisplayedTest() throws InterruptedException {
        NotesPage notesPage = addNote();
        Assertions.assertEquals("Test Note", notesPage.getSavedNoteTitle());
        Assertions.assertEquals("Test Description", notesPage.getSavedNoteDescription());
        notesPage.deleteNote();
    }

    private NotesPage addNote() throws InterruptedException {
        NotesPage notesPage = getNotesPage();
        notesPage.createNote("Test Note", "Test Description");
        return notesPage;
    }

    private NotesPage getNotesPage() throws InterruptedException {
        driver.get(url + ":" + this.port + NotesPage.endpoint);
        return new NotesPage(driver);
    }

    @Test
    public void afterEditNoteIsChanged() throws InterruptedException {
        NotesPage notesPage = addNote();
        notesPage.editNote("Edited title", "Edited description");
        Assertions.assertEquals("Edited title", notesPage.getSavedNoteTitle());
        Assertions.assertEquals("Edited description", notesPage.getSavedNoteDescription());
        notesPage.deleteNote();
    }

    @Test
    public void afterDeletionNoteIsNotDisplayed() throws InterruptedException {
        NotesPage notesPage = addNote();
        notesPage.deleteNote();
        Assertions.assertThrows(NoSuchElementException.class, notesPage::getSavedNoteTitle);
        Assertions.assertThrows(NoSuchElementException.class, notesPage::getSavedNoteDescription);
    }
}
