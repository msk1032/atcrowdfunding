import com.netflix.discovery.converters.Auto;
import com.studyhub.crowd.CrowdProject;
import com.studyhub.crowd.config.OSSProperties;
import com.studyhub.crowd.utils.CrowdUtils;
import com.studyhub.crowd.utils.ResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author haoren
 * @create 2021-01-19 12:34
 */

@SpringBootTest(classes = CrowdProject.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UploadTest {

    @Autowired
    OSSProperties ossProperties;


    @Test
    public void test() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("f.png");
        ResultEntity<String> resultEntity = CrowdUtils.uploadFileToOSS(ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                inputStream,
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                "f.png");

        System.out.println(resultEntity.toString());
    }
}
