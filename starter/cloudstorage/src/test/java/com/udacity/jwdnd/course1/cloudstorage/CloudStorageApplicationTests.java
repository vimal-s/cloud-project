package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.webpage.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.webpage.NotesPage;
import com.udacity.jwdnd.course1.cloudstorage.webpage.SignupPage;
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
  public void setUp() {
    this.driver = new ChromeDriver();
  }

  @AfterEach
  public void tearDown() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void requestRedirectionTest() throws InterruptedException {
    driver.get(APP_URL);
    assertEquals(LOGIN_PAGE_TITLE, driver.getTitle());
  }

  @Test
  public void signupAccessibilityTest() {
    driver.get(SIGNUP_URL);
    assertEquals(SIGNUP_PAGE_TITLE, driver.getTitle());
  }

  @Test
  public void accessAfterLoginTest() throws InterruptedException {
    driver.get(LOGIN_URL);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);

    driver.get(APP_URL);
    NotesPage notesPage = new NotesPage(driver);

    notesPage.createNote(NOTE_TITLE, NOTE_DESCRIPTION);
    assertEquals(NOTE_TITLE, notesPage.getSavedNoteTitle());
    assertEquals(NOTE_DESCRIPTION, notesPage.getSavedNoteDescription());

    notesPage.deleteNote();

    notesPage.logout();
    assertThrows(NoSuchElementException.class, notesPage::getSavedNoteTitle);
  }
}
