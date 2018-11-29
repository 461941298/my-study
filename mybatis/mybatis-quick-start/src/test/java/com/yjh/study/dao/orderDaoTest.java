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
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.InputStream;
import java.sql.*;
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
    public void jdbc() throws ClassNotFoundException {
        //1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2. 数据库连接信息
        String url = "jdbc:mysql://192.168.1.190:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
        String username = "yjh";
        String password = "651028";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            //3. 获取链接
            connection = DriverManager.getConnection(url, username, password);
            //4. 编写sql
            String sql = "select * from `order` where id = ?";
            //5. 创建statement
            preparedStatement = connection.prepareStatement(sql);
            //6. 设置条件参数
            preparedStatement.setInt(1, 1);
            //7. 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            //8. 解析查询结果
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                double money = resultSet.getDouble("money");
                Date createTime = resultSet.getDate("time");

                Order order = new Order();
                order.setId(id);
                order.setMoney(money);
                order.setTime(createTime);

                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

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
    public void getByMoneyForeach() {
        double[] moneys = new double[]{12, 17};
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> orderViews = orderDao.getByMoneyForeach(moneys);
        orderViews.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void insertByBatch() {
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
    public void insertBatch() {
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
    public void getChooseWhen() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        List<OrderView> orderViews = orderDao.getChooseWhen(17.0, 2);
        orderViews.forEach(System.out::println);
        sqlSession.close();
    }

    //测试一级缓存
    @Test
    public void testCache() {
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
    public void testCache2() {
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