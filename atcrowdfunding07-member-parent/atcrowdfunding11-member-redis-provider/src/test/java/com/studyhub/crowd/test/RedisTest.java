package com.studyhub.crowd.test;

import com.studyhub.crowd.utils.CrowdUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author haoren
 * @create 2020-12-18 21:05
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate template;

    private Logger logger = LoggerFactory.getLogger(RedisTest.class);


    @Test
    public void testRedis() {
        ValueOperations<String, String> ops = template.opsForValue();

        ops.append("apple", "red");

        ops.get("apple");

        logger.debug(ops.get("apple"));
    }

    @Test
    public void testTime() {

        ValueOperations<String, String> ops = template.opsForValue();

        ops.set("person", "huwei", 1000, TimeUnit.SECONDS);

        System.out.println(ops.get("person"));

    }

    @Test
    public void testCode() {
        System.out.println(CrowdUtils.messageCode());
    }

}
