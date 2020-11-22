package com.xzh.rw.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通过该接口注释的service使用读模式，其他使用写模式
 *
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Read {
}
