package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayedQueueConfig {
    //延迟交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayedExchange";

    //延迟队列
    public static final String DELAYED_QUEUE_NAME = "delayedQueue";

    //routingKey
    public static final String DELAYED_ROUTING_KEY = "routingKey";

    //声明交换机 基于插件
    @Bean
    public CustomExchange delayedExchange(){
        Map<String,Object> argument = new HashMap<>();
        argument.put("x-delayed-type","direct");
        /**
         * 1.交换机名字
         * 2.交换机类型
         * 3.是否需要持久化
         * 4.是否需要自动删除
         * 5.其他参数
         */
        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",true,false,argument);
    }

    //声明队列
    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    //绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(){
        return BindingBuilder.bind(delayedQueue()).to(delayedExchange()).with(DELAYED_ROUTING_KEY).noargs();
    }
}
