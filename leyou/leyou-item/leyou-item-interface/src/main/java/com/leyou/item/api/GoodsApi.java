package com.leyou.item.api;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping()
public interface GoodsApi {

    /**
     * 根据skuId查询Sku
     *
     * @param id
     * @return
     */
    @GetMapping("sku/{id}")
    Sku querySkuById(@PathVariable("id") Long id);

    /**
     * 通过spuId查询商品详情
     *
     * @param spuId
     * @return 商品详情
     */
    @GetMapping("/spu/detail/{spuId}")
    SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 分页查询商品
     *
     * @param key      过滤条件
     * @param saleable 上架或下架
     * @param page     当前页
     * @param rows     每页大小
     * @return 商品SPU的分页信息
     */
    @GetMapping("spu/page")
    PageResult<SpuBo> querySpuBoByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    );

    /**
     * 根据spuId查询sku的集合
     *
     * @param spuId
     * @return sku的集合
     */
    @GetMapping("/sku/list")
    List<Sku> querySkusBySpuId(@RequestParam("id") Long spuId);

    /**
     * 根据spu的id查询spu
     *
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    Spu querySpuById(@PathVariable("id") Long id);
}
