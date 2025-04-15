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

    /**
     * 获取文章分类详情
     * @param id
     * @return
     */
    Article detail(Integer id);

    /**
     * 更新文章
     * @param article
     */
    void update(Article article);

    /**
     * 删除文章
     * @param id
     */
    void deleteById(Integer id);
}
