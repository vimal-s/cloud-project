package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class BaseController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final FileService fileService;
  private final NoteService noteService;
  private final CredentialService credentialService;

  public BaseController(
      FileService fileService, NoteService noteService, CredentialService credentialService) {
    this.fileService = fileService;
    this.noteService = noteService;
    this.credentialService = credentialService;
  }

  @GetMapping
  public String getAll(File file, Note note, Credential credential, Model model) {
    model.addAttribute("files", fileService.findAll());
    model.addAttribute("notes", noteService.findAll());
    model.addAttribute("credentials", credentialService.findAll());
    return "home";
  }
}
