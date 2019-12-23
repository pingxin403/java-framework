package com.hyp.learn.mybatis.config;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.config
 * hyp create at 19-12-23
 **/
//自定义工厂类
public class MyObjectFactory extends DefaultObjectFactory {
    private static final long serialVersionUID = -5548708841003212961L;
    public <T> T Create(Class<T> type){
        return super.create(type);
    }
    public <T> T Create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs){
        return super.create(type, constructorArgTypes, constructorArgs);
    }
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties) ;
    }
    @Override
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}