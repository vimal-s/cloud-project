package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.webpage.FilePage;
import com.udacity.jwdnd.course1.cloudstorage.webpage.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static com.udacity.jwdnd.course1.cloudstorage.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilesSectionTests {

  @LocalServerPort
  private Integer port;
  private String loginUrl;
  private String appUrl;
  private WebDriver driver;
  private FilePage filePage;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setUp() throws InterruptedException {
    loginUrl = DOMAIN + port + LOGIN_ENDPOINT;
    appUrl = DOMAIN + port + APP_ENDPOINT;
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", chromePrefs);
    driver = new ChromeDriver(options);
    login();
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  void uploadFileTest() throws InterruptedException, IOException {
    uploadFile();
    assertEquals("test-document.txt", filePage.getFileName());
    deleteFile();
  }

  @Test
  void downloadFileTest() throws IOException, InterruptedException {
    uploadFile();
    downloadFile();

    File folder = new File(System.getProperty("user.dir"));
    boolean anyMatch = Arrays.stream(folder.listFiles()).anyMatch(file -> {
      if (file.isFile()) {
        if (file.getName().equals("test-document.txt")) {
          file.delete();
          return true;
        }
      }
      return false;
    });

    assertTrue(anyMatch, "Downloaded document is not found");
    deleteFile();
  }


  @Test
  void deleteFileTest() throws InterruptedException, IOException {
    uploadFile();
    deleteFile();
    assertThrows(NoSuchElementException.class, filePage::getFileName);
  }

  private void login() throws InterruptedException {
    driver.get(loginUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
    driver.get(appUrl);
  }

  private void uploadFile() throws InterruptedException, IOException {
    driver.get(appUrl);
    filePage = new FilePage(driver);
    Resource res = new DefaultResourceLoader().getResource("test-document.txt");
    filePage.upload(Paths.get(res.getURI()).toString());
    driver.get(appUrl);
  }

  private void downloadFile() throws InterruptedException {
    driver.get(appUrl);
    filePage.downloadFile();
    driver.get(appUrl);
  }

  private void deleteFile() throws InterruptedException {
    driver.get(appUrl);
    filePage.deleteFile();
    driver.get(appUrl);
  }
}
