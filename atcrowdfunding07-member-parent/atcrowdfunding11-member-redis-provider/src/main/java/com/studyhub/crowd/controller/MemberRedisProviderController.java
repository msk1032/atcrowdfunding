package com.studyhub.crowd.controller;

import com.studyhub.crowd.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author haoren
 * @create 2020-12-18 21:36
 */

@RestController
public class MemberRedisProviderController {

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value){
        ValueOperations<String, String> ops = template.opsForValue();

        try {
            ops.set(key, value);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    @RequestMapping("/set/redis/key/value/remote/with/timeout")
    public ResultEntity<String> setRedisKeyValueRemoteWithTimeout(@RequestParam("key") String key,
                                                                  @RequestParam("value") String value,
                                                                  @RequestParam("time") long time,
                                                                  @RequestParam("timeUnit") TimeUnit timeUnit){
        ValueOperations<String, String> ops = template.opsForValue();

        try {
            ops.set(key, value, time, timeUnit);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/redis/string/value/by/key")
    public ResultEntity<String> getRedisStringValueByKeyRemote(@RequestParam("key") String key) {

        ValueOperations<String, String> ops = template.opsForValue();


        try {
            String value = ops.get(key);

            return ResultEntity.successWithData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


    @RequestMapping("/remove/redis/key/remote")
    public ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key){

        try {
            template.delete(key);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
}
