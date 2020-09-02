package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

  @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
  @Results({
    @Result(column = "noteid", property = "id"),
    @Result(column = "notetitle", property = "title"),
    @Result(column = "notedescription", property = "description")
  })
  public List<Note> findByUserId(int userId);

  @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)" +
              "VALUES (#{title}, #{description}, #{userId})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  public void save(Note note);

  @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description}" +
              "WHERE noteid = #{id}")
  void update(Note note);

  @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
  public void delete(int id);
}
