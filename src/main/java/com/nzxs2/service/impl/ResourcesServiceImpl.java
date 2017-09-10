package com.nzxs2.service.impl;

import com.nzxs2.dao.ResourcesDao;
import com.nzxs2.domin.Resources;
import com.nzxs2.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesDao resourcesDao;


    @Override
    public List<Resources> queryAll(){
        return resourcesDao.queryAll();
    }


}
