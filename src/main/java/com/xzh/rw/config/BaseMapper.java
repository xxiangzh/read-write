package com.xzh.rw.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper
 *
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
