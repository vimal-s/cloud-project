package com.udacity.jwdnd.course1.cloudstorage.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

  @FindBy(id = "inputUsername")
  private WebElement username;

  @FindBy(id = "inputPassword")
  private WebElement password;

  @FindBy(className = "btn-primary")
  private WebElement submitButton;

  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void login(String username, String password) throws InterruptedException {
    this.username.clear();
    this.username.sendKeys(username);
    this.password.clear();
    this.password.sendKeys(password);
    submitButton.click();
    Thread.sleep(1000);
  }
}
