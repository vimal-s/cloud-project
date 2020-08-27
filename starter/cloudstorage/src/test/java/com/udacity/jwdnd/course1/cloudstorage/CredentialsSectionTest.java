package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.CredentialsPage;
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
public class CredentialsSectionTest {

    private WebDriver driver;
    private CredentialsPage credentialsPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        this.driver = new ChromeDriver();
        driver.get(DOMAIN + ":" + PORT + APP_ENDPOINT);
        credentialsPage = new CredentialsPage(driver);
        credentialsPage.addCredential(CREDENTIAL_URL, CREDENTIAL_USERNAME, CREDENTIAL_PASSWORD);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addedCredentialGetsDisplayed() throws InterruptedException {
        assertEquals(CREDENTIAL_URL, credentialsPage.getSavedUrl());
        assertEquals(CREDENTIAL_USERNAME, credentialsPage.getSavedUsername());
        assertEquals(CREDENTIAL_PASSWORD, credentialsPage.getSavedPassword());
        credentialsPage.deleteCredential();
    }

    @Test
    public void editingUpdatesCredential() throws InterruptedException {
        credentialsPage.editCredential(CREDENTIAL_URL_2, CREDENTIAL_USERNAME_2, CREDENTIAL_PASSWORD_2);
        assertEquals(CREDENTIAL_URL_2, credentialsPage.getSavedUrl());
        assertEquals(CREDENTIAL_USERNAME_2, credentialsPage.getSavedUsername());
        assertEquals(CREDENTIAL_PASSWORD_2, credentialsPage.getSavedPassword());
        credentialsPage.deleteCredential();
    }

    @Test
    public void deletionRemovesCredential() throws InterruptedException {
        credentialsPage.deleteCredential();
        assertThrows(NoSuchElementException.class, credentialsPage::getSavedUrl);
        assertThrows(NoSuchElementException.class, credentialsPage::getSavedUsername);
        assertThrows(NoSuchElementException.class, credentialsPage::getSavedPassword);
    }
}
