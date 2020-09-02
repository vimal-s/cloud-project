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

import static com.udacity.jwdnd.course1.cloudstorage.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CredentialsSectionTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private CredentialsPage credentialsPage;
  private WebDriver driver;

  @BeforeAll
  public static void beforeAll() {
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
  public void addCredentialTest() throws InterruptedException {
    addCredential();
    assertEquals(CREDENTIAL_URL, credentialsPage.getSavedUrl());
    assertEquals(CREDENTIAL_USERNAME, credentialsPage.getSavedUsername());
    assertEquals(CREDENTIAL_PASSWORD, credentialsPage.getSavedPassword());
    deleteCredential();
  }

  @Test
  public void editCredentialTest() throws InterruptedException {
    addCredential();
    editCredential();
    assertEquals(CREDENTIAL_URL_2, credentialsPage.getSavedUrl());
    assertEquals(CREDENTIAL_USERNAME_2, credentialsPage.getSavedUsername());
    assertEquals(CREDENTIAL_PASSWORD_2, credentialsPage.getSavedPassword());
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
    driver.get(LOGIN_URL);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
    driver.get(APP_URL);
  }

  private void addCredential() throws InterruptedException {
    driver.get(APP_URL);
    credentialsPage = new CredentialsPage(driver);
    credentialsPage.addCredential(CREDENTIAL_URL, CREDENTIAL_USERNAME, CREDENTIAL_PASSWORD);
    driver.get(APP_URL);
  }

  private void editCredential() throws InterruptedException {
    driver.get(APP_URL);
    credentialsPage.editCredential(CREDENTIAL_URL_2, CREDENTIAL_USERNAME_2, CREDENTIAL_PASSWORD_2);
    driver.get(APP_URL);
  }

  private void deleteCredential() throws InterruptedException {
    driver.get(APP_URL);
    credentialsPage.deleteCredential();
    driver.get(APP_URL);
  }
}
