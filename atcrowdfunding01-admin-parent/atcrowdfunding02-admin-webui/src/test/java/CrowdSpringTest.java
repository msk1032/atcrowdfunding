import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.entity.Role;
import com.studyhub.crowd.mapper.AdminMapper;
import com.studyhub.crowd.mapper.RoleMapper;
import com.studyhub.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@ContextConfiguration({"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdSpringTest {

    //测试数据源
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testEncodePassword() {

        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void testInsertRole() {
        for(int i = 0; i < 100; i++) {
            roleMapper.insert(new Role(null, "role-"+(i+1)));
        }
    }

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

    @Test
    public void testTx() {
        adminService.saveAdmin(new Admin(null, "tom", "123123", "汤姆", "tom@qq.com", null));
    }

    @Test
    public void testSaveAdminMulti() {
        for (int i = 0; i < 100; i++) {
            adminMapper.insert(new Admin(null, "loginAcct" + i, "userPswd" + i, "userName" + i, "email" + i + "@qq.com", null));
        }
    }

}