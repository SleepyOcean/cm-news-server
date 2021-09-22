package com.netease.framework.aspectj;

import com.netease.common.annotation.DataSource;
import com.netease.common.config.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 多数据源处理
 *
 * @author netease
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Around("@annotation(dataSource) || @within(dataSource)")
    public Object around(ProceedingJoinPoint point, DataSource dataSource) throws Throwable
    {
        DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }
}
