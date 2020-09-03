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
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginFunctionalityTests {

  @LocalServerPort
  private Integer port;
  private String signupUrl;
  private String loginUrl;
  private String appUrl;

  private WebDriver driver;
  private NotesPage notesPage;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void setUp() {
    signupUrl = DOMAIN + port + SIGNUP_ENDPOINT;
    loginUrl = DOMAIN + port + LOGIN_ENDPOINT;
    appUrl = DOMAIN + port + APP_ENDPOINT;
    this.driver = new ChromeDriver();
  }

  @AfterEach
  public void tearDown() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void requestRedirectionTest() {
    driver.get(appUrl);
    assertEquals(LOGIN_PAGE_TITLE, driver.getTitle());
  }

  @Test
  public void signupAccessibilityTest() {
    driver.get(signupUrl);
    assertEquals(SIGNUP_PAGE_TITLE, driver.getTitle());
  }

  @Test
  void accessAfterLoginTest() throws InterruptedException {
    login();
    addNote();
    assertEquals(NOTE_TITLE, notesPage.getSavedNoteTitle());
    assertEquals(NOTE_DESCRIPTION, notesPage.getSavedNoteDescription());
    notesPage.deleteNote();
  }

  @Test
  public void accessAfterLogoutTest() throws InterruptedException {
    login();
    addNote();
    notesPage.logout();
    assertEquals(LOGIN_PAGE_TITLE, driver.getTitle());
    assertThrows(NoSuchElementException.class, notesPage::getSavedNoteTitle);
    login();
    notesPage.deleteNote();
  }

  private void login() throws InterruptedException {
    driver.get(loginUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
    driver.get(appUrl);
  }

  private void addNote() throws InterruptedException {
    driver.get(appUrl);
    notesPage = new NotesPage(driver);
    notesPage.createNote(NOTE_TITLE, NOTE_DESCRIPTION);
    driver.get(appUrl);
  }
}
