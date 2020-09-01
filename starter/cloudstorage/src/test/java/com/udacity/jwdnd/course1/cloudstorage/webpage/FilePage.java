package com.udacity.jwdnd.course1.cloudstorage.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilePage {

  @FindBy(id = "fileUpload")
  private WebElement chooseFile;

  @FindBy(css = "#nav-files .btn-dark")
  private WebElement uploadButton;

  @FindBy(css = "#fileTable .btn-success")
  private WebElement downloadButton;

  @FindBy(css = "#fileTable .btn-danger")
  private WebElement deleteButton;

  @FindBy(id = "file-name")
  private WebElement fileName;

  public FilePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  // todo: check how to select file, this works for now
  public void upload(String pathToFile) throws InterruptedException {
    chooseFile.sendKeys(pathToFile);
    uploadButton.click();
    Thread.sleep(1000);
  }

  public void download() throws InterruptedException {
    downloadButton.click();
    Thread.sleep(1000);
  }

  public String getFileName() {
    return fileName.getText();
  }

  public void delete() throws InterruptedException {
    deleteButton.click();
    Thread.sleep(1000);
  }
}
