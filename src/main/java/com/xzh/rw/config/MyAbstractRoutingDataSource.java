package com.xzh.rw.config;

import java.util.concurrent.ThreadLocalRandom;
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
        int sum = ThreadLocalRandom.current().nextInt(1) + 1;
        log.info("使用：{}{}", DbContextHolder.READ, sum);
        return DbContextHolder.READ + sum;
    }
}
