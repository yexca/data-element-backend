package com.yexca.service.impl;

import com.alibaba.fastjson.JSON;
import com.yexca.constant.ElasticSearchConstant;
import com.yexca.result.PageResult;
import com.yexca.service.SearchService;
import com.yexca.vo.DataSearchVO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient client;

    /**
     * 搜索
     * @param kw
     * @return
     */
    @Override
    public PageResult search(String kw) {
        // 创建请求
        SearchRequest request = new SearchRequest(ElasticSearchConstant.INDEX_NAME);
        // 搜索内容
        request.source().query(QueryBuilders.matchQuery(
                ElasticSearchConstant.SEARCH_FIELD_NAME,
                kw
        ));
        // 高亮字段设置
        request.source().highlighter(
                new HighlightBuilder()
                        .field("name")
                        .requireFieldMatch(false)
        );
        request.source().highlighter(
                new HighlightBuilder()
                        .field("description")
                        .requireFieldMatch(false)
        );
        // 排序
        request.source().sort(ElasticSearchConstant.SORT_FIELD_NAME, SortOrder.DESC);
        // 搜索
        SearchResponse response = null;
        try {
            response = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 解析响应
        SearchHits searchHits = response.getHits();
        // 搜索的个数
        long total = searchHits.getTotalHits().value;
        // 搜索的结果
        SearchHit[] hits = searchHits.getHits();
        List<DataSearchVO> dataSearchVOList = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            // json to object
            DataSearchVO dataSearchVO = JSON.parseObject(json, DataSearchVO.class);
            // 获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if(!CollectionUtils.isEmpty(highlightFields)) {
                // 获取高亮结果 name
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    String name = highlightField.getFragments()[0].toString();
                    // 覆盖非高亮
                    dataSearchVO.setName(name);
                }
                // 获取高亮结果 description
                highlightField = highlightFields.get("description");
                if (highlightField != null) {
                    String description = highlightField.getFragments()[0].toString();
                    // 覆盖非高亮
                    dataSearchVO.setDescription(description);
                }
            }
            dataSearchVOList.add(dataSearchVO);

        }
        return new PageResult(total, dataSearchVOList);
    }
}
