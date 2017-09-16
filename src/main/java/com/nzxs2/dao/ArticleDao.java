package com.nzxs2.dao;

import com.nzxs2.domin.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 2017/9/10.
 */
@Mapper
public interface ArticleDao {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_article(title,url) values(#{title},#{url})" )
    void insert( Article article);

    @Update("update t_article set status = 1 where id = #{id}" )
    void updateStatus(@Param("id")Integer id);

    @Select("select * from t_article t where t.status = 0 or t.status is null limit 40" )
    List<Article> selectByStatus();
}
