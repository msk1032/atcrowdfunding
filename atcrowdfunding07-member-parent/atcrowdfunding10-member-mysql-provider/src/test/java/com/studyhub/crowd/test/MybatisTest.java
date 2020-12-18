package com.studyhub.crowd.test;

import com.studyhub.crowd.entity.po.MemberPO;
import com.studyhub.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private MemberPOMapper memberPOMapper;

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

    @Test
    public void testInsertMember() {

        MemberPO memberPO = new MemberPO(null, "huwei", "123456", "胡伟", "huwei@qq.com", 1, 1, "胡伟", "121231", 1);
        //memberPOMapper.insert(memberPO);
        memberPO.setId(1);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode = passwordEncoder.encode("123456");

        memberPO.setUserPswd(encode);

        memberPOMapper.updateByPrimaryKeySelective(memberPO);
    }

}
