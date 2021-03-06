package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

  @Select("SELECT * FROM FILES WHERE fileid = #{id}")
  @Results({
          @Result(column = "fileid", property = "id"),
          @Result(column = "filename", property = "name"),
          @Result(column = "filesize", property = "size"),
          @Result(column = "filedata", property = "data")
  })
  public File findById(int id);

  @Select("SELECT * FROM FILES WHERE userid = #{userId}")
  @Results({
    @Result(column = "fileid", property = "id"),
    @Result(column = "filename", property = "name"),
    @Result(column = "filesize", property = "size"),
    @Result(column = "filedata", property = "data")
  })
  public List<File> findByUserId(int userId);

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
              "VALUES (#{name}, #{contentType}, #{size}, #{userId}, #{data})")
  public void save(File file);

  @Delete("DELETE FROM FILES WHERE fileid = #{id}")
  public void delete(int id);
}
