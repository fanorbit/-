package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据parentId查询子类目
     *
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    /**
     * 查询分类名称
     * @param ids
     * @return
     */
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> list = this.categoryMapper.selectByIdList(ids);
        List<String> names = new ArrayList<>();
        for (Category category : list) {
            names.add(category.getName());
        }
        return names;
        // return list.stream().map(category -> category.getName()).collect(Collectors.toList());
    }

    /**
     * 根据3级分类id(cid3)，查询1-3级的分类
     *
     * @param id
     * @return
     */
    public Category queryAllByCid(Long id){
        return this.categoryMapper.selectByPrimaryKey(id);
    }
}
