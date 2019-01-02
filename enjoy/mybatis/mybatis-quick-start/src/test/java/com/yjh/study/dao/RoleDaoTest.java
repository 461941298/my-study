package com.yjh.study.dao;

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
public class RoleDaoTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void findByName(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        Role role = roleDao.findById(1);
        System.out.println(role.getName());
    }
}
