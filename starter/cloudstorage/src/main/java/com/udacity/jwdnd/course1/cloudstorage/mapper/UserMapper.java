package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM USERS WHERE username = #{username}")
  @Results({@Result(column = "userid", property = "id")})
  public User find(String username);

  @Insert("INSERT INTO USERS (username, password, salt, firstname, lastname)" +
              "VALUES (#{username}, #{password}, #{salt}, #{firstname}, #{lastname})")
  public void save(User user);
}
