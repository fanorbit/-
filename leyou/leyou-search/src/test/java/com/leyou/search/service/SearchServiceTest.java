package com.leyou.search.service;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

    @Autowired
    SearchService searchService;

    /*@Test
    public void testGetParamAggResult() {
        // 初始化自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加查询条件
        MatchQueryBuilder basicQuery = QueryBuilders.matchQuery("all", "手机").operator(Operator.AND);
        queryBuilder.withQuery(basicQuery);
        Long id = 76l;

        List<Map<String, Object>> specs = searchService.getParamAggResult(id, basicQuery);
        System.out.println("specs = " + specs.toString());
    }*/
}