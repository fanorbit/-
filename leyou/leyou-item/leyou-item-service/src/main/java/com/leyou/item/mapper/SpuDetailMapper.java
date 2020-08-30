package com.leyou.item.mapper;

import com.leyou.item.pojo.SpuDetail;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface SpuDetailMapper extends Mapper<SpuDetail> {
    /*@Insert("insert into tb_spu_detail(spu_id) values({spuDetail.getSpuId()})")
    void insertSelective(SpuDetail spuDetail);*/

}
