package com.nzxs2.domin;

import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Date;

/**
 * @Author Ryan
 * @Date 2017/9/10 23:12
 * @Function
 */
@Document(indexName="nzxs",type="article",shards=1,replicas=0,indexStoreType="fs",refreshInterval="-1")
public class ArticleInfo{

    private static final long serialVersionUID = 551589397625941750L;
    private Integer id;
    private String title;
    private String articleText;
    private Integer articleId;
    private Date createTime;
    private Date updateTime;
    private Boolean esStatus;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getEsStatus() {
        return esStatus;
    }

    public void setEsStatus(Boolean esStatus) {
        this.esStatus = esStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
