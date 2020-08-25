package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")   // todo: why do I need this?
    public int save(Note note);

    @Select("SELECT * FROM NOTES")
    @Results({
            @Result(column = "noteid", property = "id"),
            @Result(column = "notetitle", property = "title"),
            @Result(column = "notedescription", property = "description")
    })
    public List<Note> findAll();
}
