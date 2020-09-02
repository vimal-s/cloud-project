package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
  @Results({
          @Result(column = "credentialid", property = "id"),
          @Result(column = "key", property = "key"),
          @Result(column = "userid", property = "userId")
  })
  public List<Credential> findByUserId(int userId);

  @Insert("INSERT INTO CREDENTIALS (url, username, password, key, userid)" +
              "VALUES (#{url}, #{username}, #{password}, #{key}, #{userId})")
  public void save(Credential credential);

  @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}, key = #{key}" +
              "WHERE credentialid = #{id}")
  public void update(Credential credential);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
  public void delete(int id);
}
