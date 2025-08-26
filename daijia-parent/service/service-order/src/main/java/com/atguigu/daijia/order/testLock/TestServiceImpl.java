package com.atguigu.daijia.order.testLock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {
    
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public synchronized void testLock() {
        //从redis里面获取数据
        //1 获取当前锁  setnx
        //1 获取当前锁  setnx  + 设置过期时间
//        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent("lock", "lock");
        Boolean ifAbsent =
                redisTemplate.opsForValue()
                        .setIfAbsent("lock", "lock",10, TimeUnit.SECONDS);

        //2 如果获取到锁，从redis获取数据 数据+1 放回redis里面
        if(ifAbsent) {
            //获取锁成功，执行业务代码
            //1.先从redis中通过key num获取值  key提前手动设置 num 初始值：0
            String value = redisTemplate.opsForValue().get("num");
            //2.如果值为空则非法直接返回即可
            if (StringUtils.isBlank(value)) {
                return;
            }
            //3.对num值进行自增加一
            int num = Integer.parseInt(value);
            redisTemplate.opsForValue().set("num", String.valueOf(++num));

            //3 释放锁
            redisTemplate.delete("lock");
        } else {
            try {
                Thread.sleep(100);
                this.testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void testLock1() {
        //从redis里面获取数据
        String value = redisTemplate.opsForValue().get("num");

        if(StringUtils.isBlank(value)) {
            return;
        }
        
        //把从redis获取数据+1
        int num = Integer.parseInt(value);
        
        //数据+1之后放回到redis里面
        redisTemplate.opsForValue().set("num",String.valueOf(++num));
    }
}