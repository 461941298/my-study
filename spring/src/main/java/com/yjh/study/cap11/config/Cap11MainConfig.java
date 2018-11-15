package com.yjh.study.cap11.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "com.yjh.study.cap11")
@EnableTransactionManagement
public class Cap11MainConfig {

    //创建数据源
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        //c3p0封装了JDBC，dataSource接口实现
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("yjh");
        dataSource.setPassword("651028");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://192.168.1.190:3306/test");
        return dataSource;
    }


    //jdbcTemplate简化数据库操作
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }

    //注册事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
        //事务管理器只对当前数据源起作用，所以要加入数据源
        return new DataSourceTransactionManager(dataSource());
    }
}
