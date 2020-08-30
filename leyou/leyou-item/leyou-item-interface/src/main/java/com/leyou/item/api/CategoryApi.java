package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {

    /**
     * 查询商品分类名称
     *
     * @param ids 商品分类id
     * @return 商品分类名称
     */
    @GetMapping("names")
    public List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);

    /**
     * 根据3级分类id(cid3)，查询1-3级的分类
     *
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public Category queryAllByCid(Long id);
}
