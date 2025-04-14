package com.itheima.sevice.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.sevice.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param category
     */
    @Override
    public void add(Category category) {
        //设置创建时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        //设置创建人
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    /**
     * 获取文章分类列表
     * @return
     */
    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Category> cs = categoryMapper.list(userId);
        return cs;
    }

    /**
     * 获取文章分类
     * @param catagoryId
     * @return
     */
    @Override
    public Category findById(Integer catagoryId) {
        Category category = categoryMapper.findById(catagoryId);
        return category;
    }

    /**
     * 更新文章分类
     * @param category
     * @return
     */
    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.update(category);
    }

    /**
     * 删除文章分类
     * @param id
     */
    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
