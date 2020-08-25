package com.udacity.jwdnd.course1.cloudstorage.services;

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
        // todo: find user id first
        note.setUserId(1);
        mapper.save(note);
        logger.info(mapper.findAll().get(0).toString());
    }

    public List<Note> findAll() {
        return mapper.findAll();
    }
}
