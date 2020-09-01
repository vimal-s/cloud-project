package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class NoteController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final NoteService service;

  public NoteController(NoteService service) {
    this.service = service;
  }

  @PostMapping("/note")
  public String saveNote(Note note, Model model) {
    logger.info("post id: " + note);
    service.save(note);
    //        model.addAttribute("notes", service.findAll());
//    return "redirect:http://localhost:8080/home";
    return "result";
  }

  @GetMapping("/note/{id}")
  public String deleteNote(@PathVariable int id, Note note, Model model) {
    logger.info("id is: " + id);
    service.delete(id);
    //        model.addAttribute("notes", service.findAll());
//    return "redirect:http://localhost:8080/home";
    return "result";
  }
}
