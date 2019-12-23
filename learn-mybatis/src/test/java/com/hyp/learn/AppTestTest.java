package com.hyp.learn;

import com.hyp.learn.mybatis.dao.UserMapper;
import com.hyp.learn.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Unit test for simple AppTest.
 */
public class AppTestTest {
    /**
     * 生命周期(作用范围)
     * 1. sqlsession:方法级别
     * 2. sqlsessionFactory:全局范围(应用级别)
     * 3. sqlsessionFactoryBuilder:方法级别
     */

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void create() throws IOException, SQLException {
// 加载全局配置文件（同时把映射文件也加载了）
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testFindUserById() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);


        User user = userMapper.findUserById(1);
        System.out.println("findbyId" + user);

        User o = new User();
        o.setUsername("李四");
        o.setSex("未知");
        Integer i = userMapper.saveUser(o);
        System.out.println("添加了" + i + "个用户");

        user.setAddress("金星");

        Integer j = userMapper.updateUser(user);
        System.out.println("修改了" + j + "个用户");

        Integer t = userMapper.deleteUser(user.getId());
        System.out.println("删除了" + t + "条记录");

        List<User> users =
                userMapper.selectAllUser();

        System.out.println(users);

        System.out.println("找到包括‘李’的记录"+userMapper.findUsersByName("李"));
    }


}
