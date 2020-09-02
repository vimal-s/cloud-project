package com.udacity.jwdnd.course1.cloudstorage.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {

  @FindBy(css = "#nav-credentials .btn-info")
  private WebElement addCredentialButton;

  @FindBy(css = "#credentialModal .btn-primary")
  private WebElement saveCredentialButton;

  @FindBy(css = "#credentialTable .btn-success")
  private WebElement editCredentialButton;

  @FindBy(css = "#credentialTable .btn-danger")
  private WebElement deleteCredentialButton;

  @FindBy(id = "credential-url")
  private WebElement url;

  @FindBy(id = "credential-username")
  private WebElement username;

  @FindBy(id = "credential-password")
  private WebElement password;

  @FindBy(id = "url")
  private WebElement savedUrl;

  @FindBy(id = "username")
  private WebElement savedUsername;

  @FindBy(id = "password")
  private WebElement savedPassword;

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

  public void addCredential(String url, String username, String password)
      throws InterruptedException {
    focusToCredentialsPage(driver);
    addCredentialButton.click();
    Thread.sleep(1000);
    this.url.sendKeys(url);
    this.username.sendKeys(username);
    this.password.sendKeys(password);
    saveCredentialButton.click();
    Thread.sleep(1000);
  }

  public void editCredential(String url, String username, String password)
      throws InterruptedException {
    focusToCredentialsPage(driver);
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
  }

  public void deleteCredential() throws InterruptedException {
    focusToCredentialsPage(driver);
    deleteCredentialButton.click();
    Thread.sleep(1000);
  }

  public String getSavedUrl() throws InterruptedException {
    focusToCredentialsPage(driver);
    return savedUrl.getText();
  }

  public String getSavedUsername() throws InterruptedException {
    focusToCredentialsPage(driver);
    return savedUsername.getText();
  }

  public String getSavedPassword() throws InterruptedException {
    focusToCredentialsPage(driver);
    return savedPassword.getText();
  }
}
