package com.leyou.item.mapper;

import com.leyou.item.pojo.Sku;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface SkuMapper extends Mapper<Sku> {
    /*@Insert("insert into tb_sku(spu_id,create_time,last_update_time) values({sku.getSpuId()},{sku.getCreateTime()},{sku.getLastUpdateTime()})")
    void insertSelective(Sku sku);*/
}
