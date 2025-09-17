package com.atguigu.daijia.common.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息
    public boolean sendMessage(String exchange, String routingkey, Object message) {
        rabbitTemplate.convertAndSend(exchange,routingkey,message);
        return true;
    }


    public boolean sendDealyMessage(String exchange, String routingkey, String message, int delayMillis) {

        rabbitTemplate.convertAndSend(exchange, routingkey,
                message, messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties()
                            .setHeader("x-delay", delayMillis); // 设置延迟时间（毫秒）
                    return messagePostProcessor;
                });
        return true;

    }
}
