package com.project.projectbackculture.config.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
public class ConnectionDatabase {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.userName}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(this.databaseUrl);
        dataSource.setUsername(this.databaseUsername);
        dataSource.setPassword(this.databasePassword);
        log.info("Conexion establecida");
        return dataSource;
    }

}

