package com.leyou.item.mapper;

import com.leyou.item.pojo.Stock;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface StockMapper extends Mapper<Stock> {
    /*@Insert("insert into tb_stock(sku_id,seckill_total) values({stock.getSkuId()},{stock.getStock()})")
    void insertSelective(Stock stock);*/
}
