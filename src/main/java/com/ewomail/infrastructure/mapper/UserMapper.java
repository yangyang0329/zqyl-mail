package com.ewomail.infrastructure.mapper;

import com.ewomail.domain.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM i_users")
    List<Users> selectAll();

    @Select("SELECT * FROM i_users WHERE id = #{id}")
    Users selectById(@Param("id") Integer id);

    @Select("SELECT * FROM i_users WHERE email = #{email}")
    Users selectByEmail(@Param("email") String email);

    @Insert("INSERT INTO i_users(domain_id, password, email, maildir, uname, tel, active, limits, limitg, ctime) " + "VALUES(#{domainId}, #{password}, #{email}, #{maildir}, #{uname}, #{tel}, #{active}, #{limits}, #{limitg}, #{ctime})")
    void insert(Users user);

    void batchInsertUsers(List<Users> userList);

    @Update("UPDATE i_users SET domain_id=#{domainId}, password=#{password}, email=#{email}, " + "maildir=#{maildir}, uname=#{uname}, tel=#{tel}, active=#{active}, " + "limits=#{limits}, limitg=#{limitg} WHERE id=#{id}")
    void update(Users user);

    @Delete("DELETE FROM i_users WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
} 