package com.atguigu.daijia.order.testLock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public synchronized void testLock() {
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