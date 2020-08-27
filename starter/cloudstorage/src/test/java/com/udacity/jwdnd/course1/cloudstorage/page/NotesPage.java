package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    public static final String endpoint = "/home";

    private final WebDriver driver;

    @FindBy(id = "title")
    private WebElement savedNoteTitle;

    @FindBy(id = "description")
    private WebElement savedNoteDescription;

    @FindBy(css = "#userTable .btn-success")
    private WebElement editNoteButton;

    @FindBy(css = "#userTable .btn-danger")
    private WebElement deleteNoteButton;

    @FindBy(css = "#nav-notes .btn-info")
    private WebElement createNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(css = "#noteModal .btn-primary")
    private WebElement saveNoteButton;

    private static void focusToNotesTab(WebDriver driver) throws InterruptedException {
        WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
        notesTab.click();
        Thread.sleep(1000);
    }

    public NotesPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        Thread.sleep(1000);
        focusToNotesTab(driver);
        PageFactory.initElements(driver, this);
    }

    public void createNote(String title, String description) throws InterruptedException {
        createNoteButton.click();
        Thread.sleep(1000);
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        saveNoteButton.click();
        Thread.sleep(1000);
    }

    public String getSavedNoteTitle() throws InterruptedException {
        focusToNotesTab(driver);
        return savedNoteTitle.getText();
    }

    public String getSavedNoteDescription() throws InterruptedException {
        focusToNotesTab(driver);
        return savedNoteDescription.getText();
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
        Thread.sleep(1000);
    }

}
