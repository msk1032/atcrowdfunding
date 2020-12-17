package com.studyhub.crowd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author haoren
 * @create 2020-12-17 22:37
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisTest {
    @Autowired
    private DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testConnection() {

        try {
            Connection connection = dataSource.getConnection();
            logger.debug(connection.toString());

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
