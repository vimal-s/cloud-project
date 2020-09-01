package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final NoteMapper noteMapper;
  private final UserService userService;

  public NoteService(NoteMapper noteMapper, UserService userService) {
    this.noteMapper = noteMapper;
    this.userService = userService;
  }

  public void save(Note note) {
    if (note.getId() != 0) {
      noteMapper.update(note);
      return;
    }

    note.setUserId(userService.getCurrentUserId());
    noteMapper.save(note);
  }

  public List<Note> findAll() {
    return noteMapper.findByUserId(userService.getCurrentUserId());
  }

  public void delete(int id) {
    noteMapper.delete(id);
  }
}
