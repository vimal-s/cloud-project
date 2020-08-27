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

    private final NoteMapper mapper;

    public NoteService(NoteMapper mapper) {
        this.mapper = mapper;
    }

    public void save(Note note) {
        if (note.getId() != 0) {
            mapper.update(note);
            return;
        }
        // todo: find user id first
        note.setUserId(1);
        mapper.save(note);
    }

    public List<Note> findAll() {
        return mapper.findAll();
    }

    public void delete(int id) {
        mapper.delete(id);
    }
}
