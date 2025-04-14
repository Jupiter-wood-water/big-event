package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User fingByUserName(String username);

    @Insert("insert into user(username, password, create_time, update_time) " +
            "VALUES(#{username},#{password},now(),now())  ")
    void register(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl},update_time=now() where id = #{id} ")
    void updateAvator(String avatarUrl, Integer id);

    @Update("update user set user.password = #{newPwd} where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
