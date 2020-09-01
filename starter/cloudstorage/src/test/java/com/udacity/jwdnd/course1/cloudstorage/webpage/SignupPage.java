package com.udacity.jwdnd.course1.cloudstorage.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

  private WebDriver driver;

  @FindBy(id = "inputFirstName")
  private WebElement firstname;

  @FindBy(id = "inputLastName")
  private WebElement lastname;

  @FindBy(id = "inputUsername")
  private WebElement username;

  @FindBy(id = "inputPassword")
  private WebElement password;

  @FindBy(className = "btn-primary")
  private WebElement submitButton;

  public SignupPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void signup(String firstname, String lastname, String username, String password)
      throws InterruptedException {
    this.firstname.clear();
    this.firstname.sendKeys(firstname);
    this.lastname.clear();
    this.lastname.sendKeys(lastname);
    this.username.clear();
    this.username.sendKeys(username);
    this.password.clear();
    this.password.sendKeys(password);
    submitButton.click();
    Thread.sleep(1000);
  }
}
