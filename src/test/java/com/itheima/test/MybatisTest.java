package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream is;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(is);
        session = factory.openSession(true);
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.close();
        is.close();
    }

    @Test
    public void findAllTest() {
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }


    @Test
    public void findUserByName() {
        List<User> users = userDao.findUserByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void findUserByVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> list = userDao.findUserByVo(queryVo);
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    @Test
    public void findUserByCondition() {
        User user = new User();
        user.setUsername("%王%");
        user.setAddress("北京");
        List<User> users = userDao.findUserByCondition(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }


    @Test
    public void findUserByIds() {
        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(42);
        list.add(43);
        list.add(45);
        queryVo.setIds(list);
        List<User> users = userDao.findUserByIds(queryVo);
        for (User user : users) {
            System.out.println(user);
        }

    }


}
