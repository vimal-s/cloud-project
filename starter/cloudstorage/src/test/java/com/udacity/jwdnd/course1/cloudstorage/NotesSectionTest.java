package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.webpage.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.webpage.NotesPage;
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
  public void setUp() throws InterruptedException {
    this.driver = new ChromeDriver();
    login();
  }

  @AfterEach
  public void tearDown() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void addNoteTest() throws InterruptedException {
    addNote();
    assertEquals(NOTE_TITLE, notesPage.getSavedNoteTitle());
    assertEquals(NOTE_DESCRIPTION, notesPage.getSavedNoteDescription());
    deleteNote();
  }

  @Test
  public void editNoteTest() throws InterruptedException {
    addNote();
    editNote();
    assertEquals(NOTE_TITLE_2, notesPage.getSavedNoteTitle());
    assertEquals(NOTE_DESCRIPTION_2, notesPage.getSavedNoteDescription());
    deleteNote();
  }

  @Test
  public void deleteNoteTest() throws InterruptedException {
    addNote();
    deleteNote();
    assertThrows(NoSuchElementException.class, notesPage::getSavedNoteTitle);
    assertThrows(NoSuchElementException.class, notesPage::getSavedNoteDescription);
  }

  private void login() throws InterruptedException {
    driver.get(LOGIN_URL);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
    driver.get(APP_URL);
  }

  private void addNote() throws InterruptedException {
    driver.get(APP_URL);
    notesPage = new NotesPage(driver);
    notesPage.createNote(NOTE_TITLE, NOTE_DESCRIPTION);
    driver.get(APP_URL);
  }

  private void editNote() throws InterruptedException {
    driver.get(APP_URL);
    notesPage.editNote(NOTE_TITLE_2, NOTE_DESCRIPTION_2);
    driver.get(APP_URL);
  }

  private void deleteNote() throws InterruptedException {
    driver.get(APP_URL);
    notesPage.deleteNote();
    driver.get(APP_URL);
  }
}
