package com.hyp.learn.mybatis.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.interceptor
 * hyp create at 19-12-27
 **/
/**
 * 完成插件签名：
 *		告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({//需要拦截的方法
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ), @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class MyPageInterceptor implements Interceptor {
    /**
     * intercept：拦截：
     * 		拦截目标对象的目标方法的执行；
     */

    // 用于覆盖被拦截对象的原有方法（在调用代理对象Plugin 的invoke()方法时被调用）
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("将逻辑分页改为物理分页");
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0]; // MappedStatement
        BoundSql boundSql = ms.getBoundSql(args[1]); // Object parameter
        RowBounds rb = (RowBounds) args[2]; // RowBounds
        // RowBounds为空，无需分页
        if (rb == RowBounds.DEFAULT) {
            return invocation.proceed();
        }// 在SQL后加上limit语句
        String sql = boundSql.getSql();
        String limit = String.format("LIMIT %d,%d", rb.getOffset(), rb.getLimit());
        sql = sql + " " + limit;

        // 自定义sqlSource
        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings());

        // 修改原来的sqlSource
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms, sqlSource);

        // 执行被拦截方法
        return invocation.proceed();
    }
    /**
     * plugin：
     * 		包装目标对象的：包装：为目标对象创建一个代理对象
     */
    // target 是被拦截对象，这个方法的作用是给被拦截对象生成一个代理对象，并返回它
    @Override
    public Object plugin(Object target) {
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
        System.out.println("MyPageInterceptor...plugin:mybatis将要包装的对象"+target);
        return Plugin.wrap(target, this);
    }

    /**
     * setProperties：
     * 		将插件注册时 的property属性设置进来
     */
    // 设置参数
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息："+properties);
    }
}
