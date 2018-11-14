package com.yjh.study.dao;

import com.yjh.study.entity.Order;
import com.yjh.study.view.OrderView;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yjh
 * @discrption
 */
public class orderDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        Order order = orderDao.getById(7);
        System.out.println(order);
        sqlSession.close();
    }

    @Test
    public void updateMoney() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.updateMoney(1.4, 7);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insert() {
        Order order = new Order();
        order.setMoney(12.0);
        order.setStatus(2);
        order.setTime(new Date());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.insert(order);
        System.out.println(order);
        sqlSession.commit();
        System.out.println(order);
        sqlSession.close();
    }

    @Test
    public void getAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> views = orderDao.getAll();
        views.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectIf() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> views = orderDao.selectIf(null, 2);
        views.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectIfAndWhere() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> views = orderDao.selectIfAndWhere(null, null);
        views.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void updateIfAndSet() {
        Order order = new Order();
        order.setId(7);
        order.setMoney(17.0);
        order.setStatus(0);
        order.setTime(new Date());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.updateIfAndSet(order);
        System.out.println(order);
        sqlSession.commit();
        System.out.println(order);
        sqlSession.close();
    }

    @Test
    public void insertAndTrim() {
        Order order = new Order();
        order.setMoney(18.0);
        order.setTime(new Date());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.insertAndTrim(order);
        System.out.println(order);
        sqlSession.commit();
        System.out.println(order);
        sqlSession.close();
    }


    @Test
    public void getByMoneyForeach(){
        double[] moneys = new double[]{12, 17};
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> orderViews = orderDao.getByMoneyForeach(moneys);
        orderViews.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void insertByBatch(){
        double[] moneys = new double[]{12, 17};
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> orderViews = orderDao.getByMoneyForeach(moneys);
        List<Order> orders = orderViews.stream().map(OrderView::convert2Order).collect(Collectors.toList());

        orderDao.insertByBatch(orders);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertBatch(){
        double[] moneys = new double[]{12, 17};
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> orderViews = orderDao.getByMoneyForeach(moneys);
        List<Order> orders = orderViews.stream().map(OrderView::convert2Order).collect(Collectors.toList());
        orders.forEach(
                order -> orderDao.insert(order)
        );
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getChooseWhen(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> orderViews = orderDao.getChooseWhen(17.0 , 2);
        orderViews.forEach(System.out::println);
        sqlSession.close();
    }

    //测试一级缓存
    @Test
    public void testCache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        System.out.println(orderDao.getById(7));
        Order order = new Order();
        order.setMoney(18.0);
        order.setTime(new Date());
        orderDao.insertAndTrim(order);
        System.out.println(orderDao.getById(7));
        sqlSession.close();
    }

    //测试二级缓存
    @Test
    public void testCache2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        System.out.println(orderDao.getById(7));
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        OrderDao orderDao2 = sqlSession2.getMapper(OrderDao.class);
        System.out.println(orderDao2.getById(7));
        sqlSession2.close();
    }
}