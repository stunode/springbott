package com.damuzhi.dao;

import com.damuzhi.domin.Resources;

import java.util.List;
import java.util.Map;

public interface ResourcesDao {

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String, Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}