package com.xzh.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动
 *
 * @author: 向振华
 * @date: 2019/10/11 10:56
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xzh.rw.dao.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
