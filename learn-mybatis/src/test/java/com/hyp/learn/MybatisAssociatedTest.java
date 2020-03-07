package com.hyp.learn;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn
 * hyp create at 19-12-23
 **/

import com.hyp.learn.mybatis.pojo.Orders;
import com.hyp.learn.mybatis.pojo.Person;
import com.hyp.learn.mybatis.pojo.User;
import com.hyp.learn.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * MyBatis 关联查询映射测试类
 */
public class MybatisAssociatedTest {
    /**
     * 嵌套查询
     */
    @Test
    public void findPersonByldTest() {
        // 1.通过工具类生成SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSession();
        // 2.使用MyBatis 嵌套查询的方式查询id为1的人的信息
        Person person = sqlSession.selectOne("com.hyp.learn.mybatis.dao.PersonMapper.findPersonById", 1);
        // 3.输出查询结果信息
        System.out.println(person);
        // 4.关闭SQlSession
        sqlSession.close();
    }

    /**
     * 嵌套结果
     */
    @Test
    public void findPersonByld2Test() {
        // 1.通过工具类生成SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSession();
        // 2.使用MyBatis 嵌套查询的方式查询id为1的人的信息
        Person person = sqlSession.selectOne("com.hyp.learn.mybatis.dao.PersonMapper.findPersonById2", 1);
        // 3.输出查询结果信息
        System.out.println(person);
        // 4.关闭SQlSession
        sqlSession.close();
    }

    /**
     * 一对多，嵌套查询
     */
    @Test
    public void findUserTest() {
        // 1.通过工具类生成SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSession();
        // 2.查询 id 为 1 的用户信息、
        User user = sqlSession.selectOne("com.hyp.learn.mybatis.dao.UserMapper.findUserWithOrders1", 1);
        // 3.输出查询结果信息
        System.out.println(user);
        // 4.关闭SQlSession
        sqlSession.close();
    }

    /**
     * 多对多
     */
    @Test
    public void findOrdersTest() {
        // 1.通过工具类生成SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSession();
        // 2.查询 id 为 1 的订单信息、
        Orders orders = sqlSession.selectOne("com.hyp.learn.mybatis.dao.OrderMapper.findOrdersWithProduct", 1);
        // 3.输出查询结果信息
        System.out.println(orders);
        // 4.关闭SQlSession
        sqlSession.close();
    }


    /**
     * 多重嵌套
     */
    @Test
    public void findUserWithOrdersAndProductTest(){
        // 1.通过工具类生成SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSession();
        // 2.查询 id 为 1 的订单信息、
        User user = sqlSession.selectOne("com.hyp.learn.mybatis.dao.UserMapper.findUserWithOrdersAndProduct", 1);
        // 3.输出查询结果信息
        System.out.println(user);
        // 4.关闭SQlSession
        sqlSession.close();
    }
}
