package com.itheima.sevice;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;


public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void add(Article article);

    /**
     * 查询文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);
}
