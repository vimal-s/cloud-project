package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NoteMapper mapper;
    private final NoteService service;

    public NoteController(NoteMapper mapper, NoteService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/home")
    public String getNotes(Note note, Model model) {
        model.addAttribute("notes", mapper.findAll());
        return "home";
    }

    @PostMapping("/home")
    public String saveNote(Note note, Model model) {
        service.save(note);
        model.addAttribute("notes", mapper.findAll());
        return "home";
    }
}
