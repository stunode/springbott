package com.damuzhi.service.impl;

import com.damuzhi.dao.ResourcesDao;
import com.damuzhi.domin.Resources;
import com.damuzhi.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesDao resourcesDao;


    @Override
    public List<Resources> queryAll(){
        return resourcesDao.queryAll();
    }


}
