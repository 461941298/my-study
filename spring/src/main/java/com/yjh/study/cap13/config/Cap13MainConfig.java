package com.yjh.study.cap13.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author yjh
 * @discrption
 */
@Configuration
@ComponentScan("com.yjh.study.cap13")
public class Cap13MainConfig {

    //创建数据源
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //c3p0封装了JDBC，dataSource接口实现
        dataSource.setUsername("yjh");
        dataSource.setPassword("651028");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.1.190:3306/test");
        return dataSource;
    }

    //注册事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        //事务管理器只对当前数据源起作用，所以要加入数据源
        return new DataSourceTransactionManager(dataSource());

    }

    //注册SqlSessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }

    //注册要扫描的mapper包
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yjh.study.cap13.mapper");
        return mapperScannerConfigurer;
    }
}
