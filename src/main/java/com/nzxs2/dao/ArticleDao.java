package com.nzxs2.dao;

import com.nzxs2.domin.Article;
import com.nzxs2.domin.Resources;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by admin on 2017/9/10.
 */
@Mapper
public interface ArticleDao {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_article(title,url) values(#{title},#{url})" )
    void insert( Article article);
}
