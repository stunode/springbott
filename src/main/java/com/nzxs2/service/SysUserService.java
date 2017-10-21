package com.nzxs2.service;

import com.nzxs2.domin.SysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Ryan
 * @Date 2017/10/19 23:41
 * @Function
 */
public interface SysUserService {

    List<SysUser> selectByMap(Map<String, Object> map);

    void updateById(SysUser user);
}
