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
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

@Data
@AllArgsConstructor
@Slf4j
public class ElasticSearchUtil {
//    private String server;
    private RestHighLevelClient client;

    /**
     * 判断索引库是否存在
     * @return
     */
    public boolean existsIndex() {
        // 创建请求
        GetIndexRequest request = new GetIndexRequest(ElasticSearchConstant.INDEX_NAME);
        // 发送请求
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建索引库
     */
    public void createIndex() {
        // 创建请求
        CreateIndexRequest request = new CreateIndexRequest(ElasticSearchConstant.INDEX_NAME);
        // 请求参数
        request.source(ElasticSearchConstant.MAPPING_TEMPLATE, XContentType.JSON);
        // 发送请求
        try {
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 搜索关键词并结果高亮提示
     * @param kw
     * @return
     */
    public SearchResponse search(String kw) {

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

    /**
     * 插入新文档
     * @param id
     * @param esData
     */
    public void insert(String id, ESData esData){
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

    /**
     * 删除文档
     * @param id
     */
    public void delete(String id){
        DeleteRequest request = new DeleteRequest(ElasticSearchConstant.INDEX_NAME, id);
        // 发送请求
        try {
            client.delete(request, RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建或更新文档
     * @param id
     * @param esData
     */
    public void update(String id, ESData esData){
        // 准备Request
        IndexRequest request = new IndexRequest(ElasticSearchConstant.INDEX_NAME).id(id);
        // JSON
        String json = JSON.toJSONString(esData);
        // 将数据放入请求体
        request.source(json, XContentType.JSON);
        // 发送请求
        try {
            client.index(request, RequestOptions.DEFAULT);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
