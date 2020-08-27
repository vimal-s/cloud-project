package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.NotesPage;
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

@SpringBootTest
public class NotesSectionTest {

    private WebDriver driver;
    private NotesPage notesPage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        this.driver = new ChromeDriver();
        driver.get(DOMAIN + ":" + PORT + APP_ENDPOINT);
        notesPage = new NotesPage(driver);
        notesPage.createNote(NOTE_TITLE, NOTE_DESCRIPTION);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addedNoteIsDisplayed() throws InterruptedException {
        assertEquals(NOTE_TITLE, notesPage.getSavedNoteTitle());
        assertEquals(NOTE_DESCRIPTION, notesPage.getSavedNoteDescription());
        notesPage.deleteNote();
    }

    @Test
    public void afterEditNoteIsChanged() throws InterruptedException {
        notesPage.editNote(NOTE_TITLE_2, NOTE_DESCRIPTION_2);
        assertEquals(NOTE_TITLE_2, notesPage.getSavedNoteTitle());
        assertEquals(NOTE_DESCRIPTION_2, notesPage.getSavedNoteDescription());
        notesPage.deleteNote();
    }

    @Test
    public void afterDeletionNoteIsNotDisplayed() throws InterruptedException {
        notesPage.deleteNote();
        assertThrows(NoSuchElementException.class, notesPage::getSavedNoteTitle);
        assertThrows(NoSuchElementException.class, notesPage::getSavedNoteDescription);
    }
}
