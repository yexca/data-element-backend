package com.yexca.utils;

import com.alibaba.fastjson.JSON;
import com.yexca.constant.ElasticSearchConstant;
import com.yexca.entity.ESData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

@Data
@AllArgsConstructor
@Slf4j
public class ElasticSearchUtil {
    private String server;

    public SearchResponse search(String kw) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(server)));;
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
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return response;
    }

    public void insert(String id, ESData esData){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(server)));;

        // doc to JSON
        String json = JSON.toJSONString(esData);
        // request
        IndexRequest request = new IndexRequest(ElasticSearchConstant.INDEX_NAME)
                .id(id);
        // JSON
        request.source(json, XContentType.JSON);
        // 发送请求
        try {
            client.index(request, RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(server)));;

        DeleteRequest request = new DeleteRequest(ElasticSearchConstant.INDEX_NAME, id);
        // 发送请求
        try {
            client.delete(request, RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String id, ESData esData){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(server)));;

        // 准备Request
        IndexRequest request = new IndexRequest(ElasticSearchConstant.INDEX_NAME).id(id);
        // JSON
        String json = JSON.toJSONString(esData);
        // 发送请求
        try {
            client.index(request, RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
