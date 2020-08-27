package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {

    @FindBy(css = "#nav-credentials .btn-info")
    private WebElement addNewCredentialButton;

    @FindBy(css = "#credentialTable .btn-success")
    private WebElement editCredentialButton;

    @FindBy(css = "#credentialTable .btn-danger")
    private WebElement deleteCredentialButton;

    @FindBy(id = "url")
    private WebElement savedUrl;

    @FindBy(id = "username")
    private WebElement savedUsername;

    @FindBy(id = "password")
    private WebElement savedPassword;

    @FindBy(css = "#credentialModal .btn-primary")
    private WebElement saveCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement url;

    @FindBy(id = "credential-username")
    private WebElement username;

    @FindBy(id = "credential-password")
    private WebElement password;

    private final WebDriver driver;

    public CredentialsPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        focusToCredentialsPage(driver);
        PageFactory.initElements(driver, this);
    }

    private static void focusToCredentialsPage(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("nav-credentials-tab")).click();
        Thread.sleep(1000);
    }

    public void addCredential(String url, String username, String password) throws InterruptedException {
        addNewCredentialButton.click();
        Thread.sleep(1000);
        this.url.sendKeys(url);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        saveCredentialButton.click();
        Thread.sleep(1000);
        focusToCredentialsPage(driver);
    }

    public void editCredential(String url, String username, String password) throws InterruptedException {
        editCredentialButton.click();
        Thread.sleep(1000);
        this.url.clear();
        this.username.clear();
        this.password.clear();
        this.url.sendKeys(url);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        saveCredentialButton.click();
        Thread.sleep(1000);
        focusToCredentialsPage(driver);
    }

    public void deleteCredential() throws InterruptedException {
        deleteCredentialButton.click();
        Thread.sleep(1000);
        focusToCredentialsPage(driver);
    }

    public String getSavedUrl() {
        return savedUrl.getText();
    }

    public String getSavedUsername() {
        return savedUsername.getText();
    }

    public String getSavedPassword() {
        return savedPassword.getText();
    }
}
