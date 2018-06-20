package com.maciejak.ztbdhive.config;

import org.apache.hive.jdbc.HiveDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;


@Configuration
public class HiveConfig {

    @Value("${hive.url}") String hiveUrl;
    @Value("${hive.driver-class-name}") String hiveDriver;
    @Value("${hive.username}") String hiveUsername;
    @Value("${hive.password}") String hivePassword;
    
    @Value("${hive2.url}") String hiveUrl2;
    @Value("${hive2.driver-class-name}") String hiveDriver2;
    @Value("${hive2.username}") String hiveUsername2;
    @Value("${hive2.password}") String hivePassword2;

    @Bean(name = "hiveJdbcDataSource")
    @Qualifier("hiveJdbcDataSource")
    public DataSource dataSource() throws SQLException {

        System.out.println(hiveUrl);

        HiveDriver driver = new HiveDriver();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl(hiveUrl);
        dataSource.setDriver(driver);
        dataSource.setPassword(hivePassword);
        dataSource.setUsername(hiveUsername);

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("drop table if EXISTS tabelka");
        statement.execute("create table tabelka (key int, value string)");

        return dataSource;

    }

    @Bean(name = "hiveJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveJdbcDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean(name = "hiveJdbcDataSource")
//    @Qualifier("hiveJdbcDataSource")
//    public DataSource dataSource() throws SQLException {
//
//        System.out.println(hiveUrl);
//        try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        Connection conn = null;
//        com.mysql.jdbc.Driver d = new com.mysql.jdbc.Driver();
//        HiveDriver driver = new HiveDriver();
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setUrl(hiveUrl);
//        dataSource.setDriver(d);
//        dataSource.setPassword(hivePassword);
//        dataSource.setUsername(hiveUsername);
//
//        Connection connection = dataSource.getConnection();
//        Statement statement = connection.createStatement();
//        statement.execute("CREATE TABLE IF NOT EXISTS student (id int, firstName varchar(100), lastName varchar(100), age int, city varchar(100));");
//        statement.execute("insert into student (id, firstName, lastName, age, city) values(1, 'Mati', 'Maciejak', 21, 'krk')");
//        return dataSource;
//
//    }
    
}
