package com.leyou.item.contoller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父id查询子节点
     *
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam("pid") Long pid) {
        if (pid == null || pid.longValue() < 0) {
            // 响应400（参数不合法），相当于ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            // 响应404（资源服务器未找到）
            return ResponseEntity.notFound().build();
        }
        //响应200（查询成功）
        return ResponseEntity.ok(categories);
    }

    /**
     * 查询商品分类名称
     *
     * @param ids 商品分类id
     * @return 商品分类名称
     */
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids) {
        List<String> names = this.categoryService.queryNamesByIds(ids);
        if (CollectionUtils.isEmpty(names)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);
    }

    /**
     * 根据3级分类id(cid3)，查询1-3级的分类
     *
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public Category queryAllByCid(Long id){
        return categoryService.queryAllByCid(id);
    }

}
