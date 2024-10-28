package com.project.projectbackculture.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectionDatabaseTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void TestDatabaseConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertTrue(connection.isValid(2),
                    "No se pudo conectar a la base datos");
        }
    }

}