package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("spec")
public interface SpecificationApi {

    @GetMapping("groups/{cid}")
    ResponseEntity<List<SpecGroup>> querySpecGroups(@PathVariable("cid") Long cid);

    /**
     * 查询规格参数组，及组内参数
     * @param cid
     * @return
     */
    @GetMapping("{cid}")
    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);

    /**
     * 根据条件查询规格参数
     *
     * @param gid
     * @return
     */
    @GetMapping("params")
    List<SpecParam> queryParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching);


}
