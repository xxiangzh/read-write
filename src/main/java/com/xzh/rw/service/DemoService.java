package com.xzh.rw.service;

import com.xzh.rw.config.Read;
import com.xzh.rw.dao.mapper.DemoMapper;
import com.xzh.rw.dao.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public int add(Demo demo) {
        return demoMapper.insert(demo);
    }

    @Read
    public Demo get(long id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
