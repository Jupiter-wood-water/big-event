package com.itheima.sevice;

import com.itheima.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    /**
     * 添加分类
     * @param category
     */
    void add(Category category);

    /**
     * 获取文章分类列表
     * @return
     */
    List<Category> list();

    /**
     * 获取文章分类
     * @param catagoryId
     * @return
     */
    Category findById(Integer catagoryId);

    /**
     * 更新文章分类
     * @param category
     * @return
     */
    void update(Category category);

    /**
     * 删除文章分类
     * @param id
     */
    void delete(Integer id);
}
