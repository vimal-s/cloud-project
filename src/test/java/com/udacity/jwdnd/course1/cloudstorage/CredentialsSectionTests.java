package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.webpage.CredentialsPage;
import com.udacity.jwdnd.course1.cloudstorage.webpage.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.udacity.jwdnd.course1.cloudstorage.Constant.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsSectionTests {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private CredentialsPage credentialsPage;
  private WebDriver driver;

  @LocalServerPort
  private Integer port;
  private String loginUrl;
  private String appUrl;

  @BeforeAll
  public static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void setUp() throws InterruptedException {
    loginUrl = DOMAIN + port + LOGIN_ENDPOINT;
    appUrl = DOMAIN + port + APP_ENDPOINT;
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
  public void addCredentialTest() throws InterruptedException {
    addCredential();
    assertEquals(CREDENTIAL_URL, credentialsPage.getSavedUrl());
    assertEquals(CREDENTIAL_USERNAME, credentialsPage.getSavedUsername());
    assertNotEquals(CREDENTIAL_PASSWORD, credentialsPage.getSavedPassword());
    deleteCredential();
  }

  @Test
  public void editCredentialTest() throws InterruptedException {
    addCredential();
    editCredential();
    assertEquals(CREDENTIAL_URL_2, credentialsPage.getSavedUrl());
    assertEquals(CREDENTIAL_USERNAME_2, credentialsPage.getSavedUsername());
    assertNotEquals(CREDENTIAL_PASSWORD_2, credentialsPage.getSavedPassword());
    deleteCredential();
  }

  @Test
  public void deleteCredentialTest() throws InterruptedException {
    addCredential();
    deleteCredential();
    assertThrows(NoSuchElementException.class, credentialsPage::getSavedUrl);
    assertThrows(NoSuchElementException.class, credentialsPage::getSavedUsername);
    assertThrows(NoSuchElementException.class, credentialsPage::getSavedPassword);
  }

  private void login() throws InterruptedException {
    driver.get(loginUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
    driver.get(appUrl);
  }

  private void addCredential() throws InterruptedException {
    driver.get(appUrl);
    credentialsPage = new CredentialsPage(driver);
    credentialsPage.addCredential(CREDENTIAL_URL, CREDENTIAL_USERNAME, CREDENTIAL_PASSWORD);
    driver.get(appUrl);
  }

  private void editCredential() throws InterruptedException {
    driver.get(appUrl);
    credentialsPage.editCredential(CREDENTIAL_URL_2, CREDENTIAL_USERNAME_2, CREDENTIAL_PASSWORD_2);
    driver.get(appUrl);
  }

  private void deleteCredential() throws InterruptedException {
    driver.get(appUrl);
    credentialsPage.deleteCredential();
    driver.get(appUrl);
  }
}
