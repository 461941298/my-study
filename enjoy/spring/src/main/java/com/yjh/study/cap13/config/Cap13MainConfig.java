package com.yjh.study.cap13.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author yjh
 * @discrption
 */
@Configuration
@ComponentScan("com.yjh.study.cap13")
@EnableTransactionManagement
public class Cap13MainConfig {

    //创建数据源
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("yjh");
        dataSource.setPassword("651028");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.1.190:3306/test");
        return dataSource;
    }


    //事务 和 SqlSessionFactory 都要求依赖一个DataSource， 这个DataSource只能通过参数传入
    //不能直接调用上面的 dataSource 方法，否则不能使用容器中的DataSource。
    //注册事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        //事务管理器只对当前数据源起作用，所以要加入数据源
        return new DataSourceTransactionManager(dataSource);
    }

    //注册SqlSessionFactory
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //这里设置加载mybatis的xml配置
        sqlSessionFactoryBean.setConfigLocation(new InputStreamResource(Resources.getResourceAsStream("mybatis-config.xml")));
        return sqlSessionFactoryBean;
    }

    //注册要扫描的mapper包
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yjh.study.cap13.mapper");
        return mapperScannerConfigurer;
    }
}
