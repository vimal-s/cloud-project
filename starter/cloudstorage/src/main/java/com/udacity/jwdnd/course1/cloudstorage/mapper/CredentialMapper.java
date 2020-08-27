package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    // todo: not using @Options annotation this time, lets see
    @Insert("INSERT INTO CREDENTIALS (url, username, password, key, userid) VALUES (#{url}, #{username}, #{password}, #{salt}, #{userId})")
    public void save(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}, key = #{salt} WHERE credentialid = #{id}")
    public void update(Credential credential);

    @Select("SELECT * FROM CREDENTIALS")
    @Results({
            @Result(column = "credentialid", property = "id"),
            @Result(column = "key", property = "salt"),
            @Result(column = "userid", property = "userId")
    })
    public List<Credential> findAll();

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
    public void delete(int id);
}
