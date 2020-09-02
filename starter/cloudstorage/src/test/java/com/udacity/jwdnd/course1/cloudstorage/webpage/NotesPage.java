package com.udacity.jwdnd.course1.cloudstorage.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

  @FindBy(css = "#logoutDiv .btn-secondary")
  private WebElement logoutButton;

  @FindBy(css = "#noteModal .btn-primary")
  private WebElement saveNoteButton;

  @FindBy(id = "note-title")
  private WebElement noteTitle;

  @FindBy(id = "note-description")
  private WebElement noteDescription;

  @FindBy(css = "#nav-notes .btn-info")
  private WebElement createNoteButton;

  @FindBy(css = "#userTable .btn-success")
  private WebElement editNoteButton;

  @FindBy(css = "#userTable .btn-danger")
  private WebElement deleteNoteButton;

  @FindBy(id = "title")
  private WebElement savedNoteTitle;

  @FindBy(id = "description")
  private WebElement savedNoteDescription;

  private final WebDriver driver;

  public NotesPage(WebDriver driver) throws InterruptedException {
    this.driver = driver;
    focusToNotesTab(driver);
    PageFactory.initElements(driver, this);
  }

  private static void focusToNotesTab(WebDriver driver) throws InterruptedException {
    driver.findElement(By.id("nav-notes-tab")).click();
    Thread.sleep(1000);
  }

  public void createNote(String title, String description) throws InterruptedException {
    focusToNotesTab(driver);
    createNoteButton.click();
    Thread.sleep(1000);
    noteTitle.sendKeys(title);
    noteDescription.sendKeys(description);
    saveNoteButton.click();
    Thread.sleep(1000);
  }

  public void editNote(String title, String description) throws InterruptedException {
    focusToNotesTab(driver);
    editNoteButton.click();
    Thread.sleep(1000);
    noteTitle.clear();
    noteTitle.sendKeys(title);
    noteDescription.clear();
    noteDescription.sendKeys(description);
    saveNoteButton.click();
    Thread.sleep(1000);
  }

  public void deleteNote() throws InterruptedException {
    focusToNotesTab(driver);
    deleteNoteButton.click();
  }

  public String getSavedNoteTitle() throws InterruptedException {
    focusToNotesTab(driver);
    return savedNoteTitle.getText();
  }

  public String getSavedNoteDescription() throws InterruptedException {
    focusToNotesTab(driver);
    return savedNoteDescription.getText();
  }

  public void logout() throws InterruptedException {
    focusToNotesTab(driver);
    logoutButton.click();
    Thread.sleep(1000);
  }
}
