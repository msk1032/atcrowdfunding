import com.studyhub.crowd.utils.CrowdUtils;
import org.junit.Test;

/**
 * @author haoren
 * @create 2020-12-01 20:44
 */
public class StringTest {

    @Test
    public void testMD5() {
        String source  = "123456";
        String code = CrowdUtils.md5(source);

        System.out.println(code);
    }
}
