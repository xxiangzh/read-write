package com.xzh.rw.config;

import lombok.extern.slf4j.Slf4j;

/**
 * 切换读/写模式
 * 原理是利用ThreadLocal保存当前线程是否处于读模式
 *
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
@Slf4j
public class DbContextHolder {

    public static final String WRITE = "write";
    public static final String READ = "read";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDbType(String dbType) {
        if (dbType == null) {
            throw new NullPointerException();
        }
        log.info("设置dbType为：{}", dbType);
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        return contextHolder.get() == null ? WRITE : contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
