package com.sygalin.basicproject.configurations;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class AccessConfigurationDatasource {
    @Value("${application.database}")
    private String database;
    @Value("${application.username}")
    private String username;
    @Value("${application.password}")
    private String pwd;
    @Value("${application.url}")
    private String url;
    @Value("${application.dbname}")
    private String dbName;
    @Value("${application.JWT-KEY}")
    private String jwtKey;
    @Bean
    @Primary
    public HikariDataSource hikariDataSource(){
        String driver = switch (database) {
            case "postgreSQL" -> "org.postgresql.Driver";
            case "mySQL" -> "com.mysql.cj.jdbc.Driver";
            case "H2" -> "org.h2.Driver";
            default -> "";
        };
        return    DataSourceBuilder
                .create()
                .url(url+dbName)
                .username(username)
                .password(pwd)
                .driverClassName(driver)
                .type(HikariDataSource.class)
                .build();
    }
}
