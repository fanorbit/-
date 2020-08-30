package com.leyou.search.controller;

import com.leyou.item.pojo.Category;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.pojo.SearchResult;
import com.leyou.search.service.CategoryService2;
import com.leyou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CategoryService2 categoryService;

    /**
     * 搜索商品
     *
     * @param request
     * @return
     */
    @PostMapping("/page")
    public ResponseEntity<SearchResult> search(@RequestBody SearchRequest request) {
        SearchResult result = this.searchService.search(request);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


    /**
     * 根据3级分类id(cid3)，查询1-3级的分类
     *
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id) {
        List<Category> list = this.categoryService.queryAllByCid3(id);
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
