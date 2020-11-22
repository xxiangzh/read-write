package com.xzh.rw.controller;

import com.xzh.rw.dao.pojo.Demo;
import com.xzh.rw.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
@Api(tags = "主页")
@RequestMapping("/")
@RestController
public class HomeController {

    @Autowired
    private DemoService demoService;

    @ApiOperation("主页")
    @GetMapping
    public Object home() {
        return "success";
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public Object add(Demo demo) {
        demoService.add(demo);
        return demo;
    }

    @ApiOperation("查询")
    @GetMapping("/get")
    public Object get(Long id) {
        return demoService.get(id);
    }
}
