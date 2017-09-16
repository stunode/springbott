package com.nzxs2.dao;

import com.nzxs2.domin.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by admin on 2017/9/10.
 */
@Mapper
public interface PageDao {

    @Options(useGeneratedKeys = true)

    @Select("select * from t_nzxs_page t where t.status = 0 limit 10" )
    List<Page> selectByStatus();

    @Update("update t_nzxs_page set status = 1 where id = #{id}" )
    void updateStatus(@Param("id")Integer id);
}
