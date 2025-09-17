package com.atguigu.daijia.payment.config;

import com.atguigu.daijia.common.constant.MqConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: action
 * @create: 2025/9/17 15:23
 **/
@Configuration
public class ProfitsharingMqConfig {

    @Bean
    public Queue profitsharingQueue() {
        return new Queue(MqConst.QUEUE_PROFITSHARING,true);
    }

    @Bean
    public CustomExchange profitsharingExchange() {

        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(MqConst.EXCHANGE_PROFITSHARING, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding profitsharingBinding() {
        return BindingBuilder.bind(profitsharingQueue()).to(profitsharingExchange()).with(MqConst.ROUTING_PROFITSHARING).noargs();
     }

}
