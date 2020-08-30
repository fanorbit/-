package com.leyou.search.service;

//import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.search.client.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService2 {
    @Autowired
    CategoryClient categoryClient;

    public List<Category> queryAllByCid3(Long id) {
        Category c3 = this.categoryClient.queryAllByCid(id);
        Category c2 = this.categoryClient.queryAllByCid(c3.getParentId());
        Category c1 = this.categoryClient.queryAllByCid(c2.getParentId());
        return Arrays.asList(c1, c2, c3);
    }

    /*@Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryAllByCid3(Long id) {
        Category c3 = this.categoryMapper.selectByPrimaryKey(id);
        Category c2 = this.categoryMapper.selectByPrimaryKey(c3.getParentId());
        Category c1 = this.categoryMapper.selectByPrimaryKey(c2.getParentId());
        return Arrays.asList(c1,c2,c3);
    }*/
}
