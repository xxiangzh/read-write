package com.xzh.rw.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
@Slf4j
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DbContextHolder.getDbType();
        if (typeKey.equals(DbContextHolder.WRITE)) {
            log.info("使用：{}", DbContextHolder.WRITE);
            return typeKey;
        }
        //使用随机数决定使用哪个读库
        int sum = getRandom(1, 2);
        log.info("使用：{}{}", DbContextHolder.READ, sum);
        return DbContextHolder.READ + sum;
    }

    /**
     * 获取范围内随机一个值
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
