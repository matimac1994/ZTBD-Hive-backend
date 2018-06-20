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
        statement.execute("CREATE TABLE IF NOT EXISTS student (id int, firsName String, lastName String, age int, city String)");

        return dataSource;

    }

    @Bean(name = "hiveJdbcTemplate")
    public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveJdbcDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
