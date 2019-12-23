package com.hyp.learn.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.utils
 * hyp create at 19-12-23
 **/
public class MybatisUtils {

    private static String resource = "mybatis-config.xml";
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public static SqlSession getSession() {
        return factory.openSession();
    }
}
