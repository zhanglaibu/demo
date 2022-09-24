package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig {
    //交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirmExchange";

    //队列
    public static final String CONFIRM_QUEUE_NAME = "confirmQueue";

    //routingKey
    public static final String CONFIRM_ROUTING_KEY = "routingKey";

    //备份交换机
    public static final String BACKUP_EXCHANGE_NAME = "backupExchange";

    //备份队列
    public static final String BACKUP_QUEUE_NAME = "backupQueue";

    //声明备份交换机 基于插件
    @Bean
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    //声明交换机 基于插件
    @Bean
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true)
                .withArgument("alternate-exchange",BACKUP_EXCHANGE_NAME).build();
    }

    //声明备份队列
    @Bean
    public Queue backupQueue(){
        return new Queue(BACKUP_QUEUE_NAME);
    }

    //绑定
    @Bean
    public Binding backupQueueBindingBackupExchange(){
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }

    //声明队列
    @Bean
    public Queue confirmQueue(){
        return new Queue(CONFIRM_QUEUE_NAME);
    }

    //绑定
    @Bean
    public Binding confirmQueueBindingConfirmExchange(){
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with(CONFIRM_ROUTING_KEY);
    }
}
