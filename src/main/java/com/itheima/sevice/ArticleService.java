package com.itheima.sevice;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Mapper;


public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void add(Article article);
}
