package com.yjh.study.dao;

import com.yjh.study.entity.Home;
import com.yjh.study.entity.Phone;
import com.yjh.study.entity.Role;
import com.yjh.study.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author yjh
 * @discrption
 */
public class UserDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void findByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findByName("yjh");
        System.out.println(user.getName());
        Role role = user.getRole();
        System.out.println(role.getName());
        List<Phone> phones = user.getPhones();
        phones.forEach(phone -> System.out.println(phone.getNumber()));
        sqlSession.close();
    }

    @Test
    public void findByName2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findByName2("yjh");
        User user = users.get(0);
        System.out.println(user.getName());
        Role role = user.getRole();
        System.out.println(role.getName());
        List<Phone> phones = user.getPhones();
        phones.forEach(phone -> System.out.println(phone.getNumber()));
    }

    @Test
    public void findWithPhonesByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findWithPhonesByName("yjh");
        User user = users.get(0);
        System.out.println(user.getRole().getName());
        List<Phone> phones = user.getPhones();
        phones.forEach(phone -> System.out.println(phone.getNumber()));
        sqlSession.close();
    }

    @Test
    public void findAllWithHome() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findAllWithHome();
        for (User user : users) {
            for (Home home : user.getHomes()) {
                System.out.println(user.getName() + " " + home.getName());
            }
        }
    }


}
