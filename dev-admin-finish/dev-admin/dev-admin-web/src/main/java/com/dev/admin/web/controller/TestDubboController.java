package com.dev.admin.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.service.TestDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述
 * Created by yuboon on 2019/03/25
 */
@RestController
public class TestDubboController {

    @Reference
    private TestDubboService testDubboService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/dubbo")
    public String hello(String str){

        /**
         * redisTemplate.opsForValue();//操作字符串
         redisTemplate.opsForHash();//操作hash
         redisTemplate.opsForList();//操作list
         redisTemplate.opsForSet();//操作set
         redisTemplate.opsForZSet();//操作有序set
         */

        redisTemplate.opsForValue().set("demo",123);

        Object value = redisTemplate.opsForValue().get("demo");
        System.err.println(value);
        return testDubboService.sayHello(str);
    }

}
