package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")   // todo: why do I need this?
    public void save(Note note);

    @Select("SELECT * FROM NOTES")
    @Results({
            @Result(column = "noteid", property = "id"),
            @Result(column = "notetitle", property = "title"),
            @Result(column = "notedescription", property = "description")
    })
    public List<Note> findAll();

    @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
    public void delete(int id);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{id}")
    void update(Note note);
}
