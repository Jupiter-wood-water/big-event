package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(title, content, state, cover_img, category_id, create_user, create_time, update_time) " +
            "VALUES(#{title},#{content},#{state},#{coverImg},#{categoryId},#{createUser},#{createTime},#{updateTime}) ")
    void add(Article article);

    List<Article> list(Integer userId, String categoryId, String state);
}
