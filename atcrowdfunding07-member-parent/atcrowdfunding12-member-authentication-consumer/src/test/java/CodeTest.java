import com.studyhub.crowd.CrowdAuthConsumer;
import com.studyhub.crowd.config.EmailConfigProperties;
import com.studyhub.crowd.utils.CrowdUtils;
import com.studyhub.crowd.utils.ResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haoren
 * @create 2021-01-17 13:32
 */
@SpringBootTest(classes = {CrowdAuthConsumer.class})
@RunWith(SpringRunner.class)
public class CodeTest {
    @Autowired
    EmailConfigProperties properties;

    Logger logger = LoggerFactory.getLogger(CodeTest.class);

    @Test
    public void testSendEmail() {
        String code = CrowdUtils.messageCode();
        System.out.println(code);
        String info = "【尚筹网】尊敬的用户你好，你的验证码：" + code + "，15分钟内有效。如非本人操作，请忽略。";
        try {
            ResultEntity<String> stringResultEntity = CrowdUtils.SendEmail(properties.getSendEmailAccount(),
                    properties.getSendEmailPassword(),
                    properties.getSendEmailSMTPServer(),
                    properties.getSmtpPort(),
                    "2769563356@qq.com",
                    info);

            logger.info(stringResultEntity.getResult() );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testGetCode() {
        String code = CrowdUtils.messageCode();

        logger.info(code);
    }


}
