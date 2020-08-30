package com.leyou.cart.client;

import com.leyou.item.api.GoodsApi;
import com.leyou.item.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

    /**
     * 根据skuId查询Sku
     *
     * @param id
     * @return
     */
    @GetMapping("sku/{id}")
    Sku querySkuById(@PathVariable("id") Long id);
}
