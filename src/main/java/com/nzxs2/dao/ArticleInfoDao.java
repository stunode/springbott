package com.nzxs2.dao;

import com.nzxs2.domin.ArticleInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 2017/9/10.
 */
@Mapper
public interface ArticleInfoDao {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_article_info(title,article_text,article_id) values(#{title},#{articleText},#{articleId})" )
    void insert(ArticleInfo articleInfo);

    @Select("select * from t_article_info " )
    List<ArticleInfo> selectArticleInfos();

    @Select("select * from t_article_info where id = #{articleId}" )
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property ="title",column = "title"),
            @Result(property ="articleText",column="article_text")})
    ArticleInfo selectArticleDetail(Integer articleId);
}
