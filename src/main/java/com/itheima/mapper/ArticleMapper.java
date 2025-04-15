package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(title, content, state, cover_img, category_id, create_user, create_time, update_time) " +
            "VALUES(#{title},#{content},#{state},#{coverImg},#{categoryId},#{createUser},#{createTime},#{updateTime}) ")
    void add(Article article);

    List<Article> list(Integer userId, String categoryId, String state);

    @Select("select * from article where id = #{id}")
    Article detail(Integer id);

    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId} where id = #{id}")
    void update(Article article);

    @Delete("delete from article where id = #{id}")
    void deleteById(Integer id);
}
