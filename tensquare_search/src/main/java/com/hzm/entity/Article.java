package com.hzm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Data
@Document(indexName = "tensquare_article",type = "article")//es中的文档
public class Article implements Serializable {
    @Id
    private String id;

    //index:是否索引，就是看该域是否能被搜索
    //analyzer：是否分词，就是说搜索的时候是整体匹配还是单词匹配，ik分词器还有一种算法是ik_smart
    //searchAnalyzer：是否存储，就是是否在页面中展示
    //analyzer和searchAnalyzer的分词算法必须匹配，即analyzer规定了怎么分词去查询，查询的字段也要怎么分词去匹配
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    private String state;


}
