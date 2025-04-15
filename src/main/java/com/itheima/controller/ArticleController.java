package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.sevice.ArticleService;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章
     * @param article
     */
    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    /**
     * 查询文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pg = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pg);
    }

    /**
     * 获取文章分类详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article article = articleService.detail(id);
        return Result.success(article);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id){
        articleService.deleteById(id);
        return Result.success();
    }

}
