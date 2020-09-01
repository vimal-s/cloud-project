package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.webpage.FilePage;
import com.udacity.jwdnd.course1.cloudstorage.webpage.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static com.udacity.jwdnd.course1.cloudstorage.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilesSectionTest {

  private WebDriver driver;
  private FilePage filePage;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setUp() throws InterruptedException {
    driver = new ChromeDriver();
    login();
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  void fileUploadTest() throws InterruptedException {
    uploadFile();
    driver.get(APP_URL);
    assertEquals("Coursera.pdf", filePage.getFileName());
    deleteFile();
  }

  @Test
  void fileDownloadTest() throws InterruptedException {
    fail("Not implemented");
  }

  @Test
  void fileDeletionTest() throws InterruptedException {
    uploadFile();
    deleteFile();
    assertThrows(NoSuchElementException.class, filePage::getFileName);
  }

  private void login() throws InterruptedException {
    driver.get(LOGIN_URL);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
  }

  private void uploadFile() throws InterruptedException {
    driver.get(APP_URL);
    filePage = new FilePage(driver);
    // todo: change file path later
    filePage.upload("D:\\Users\\vimal\\Desktop\\Coursera.pdf");
  }

  private void deleteFile() throws InterruptedException {
    driver.get(APP_URL);
    filePage.delete();
  }
}
