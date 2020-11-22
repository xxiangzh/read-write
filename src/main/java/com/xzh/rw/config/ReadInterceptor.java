package com.xzh.rw.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author: 向振华
 * @date: 2020/11/21 14:02
 */
@Aspect
@Component
public class ReadInterceptor implements Ordered {

    @Around("@annotation(read)")
    public Object setRead(ProceedingJoinPoint joinPoint, Read read) throws Throwable {
        try {
            DbContextHolder.setDbType(DbContextHolder.READ);
            return joinPoint.proceed();
        } finally {
            DbContextHolder.clearDbType();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
