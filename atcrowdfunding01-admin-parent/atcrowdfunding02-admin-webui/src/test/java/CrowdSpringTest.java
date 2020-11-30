import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author haoren
 * @create 2020-11-29 21:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-persist-mybatis.xml")
public class CrowdSpringTest {

    //测试数据源
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testMapper() {
        int count = adminMapper.insert(new Admin(null, "tom", "123123", "汤姆", "tom@qq.com", null));

        System.out.println("受影响的行数："+count);
    }

    @Test
    public void testLog() {

        Admin admin = adminMapper.selectByPrimaryKey(1);
        Logger logger = LoggerFactory.getLogger(CrowdSpringTest.class);
    // 按照 Debug 级别打印日志
        logger.debug(admin.toString());

    }

}