package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class ConfirmCallBackConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback{

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }
    /**
     * 发消息 交换机收到了 回调
     * @param correlationData 保存回调消息的ID及相关内容
     * @param b 交换机收到消息 true
     * @param s 失败原因 null
     *
     * 发消息 交换机未收到 回调
     * correlationData 保存回调消息的ID及相关内容
     * b 交换机收到消息 false
     * s 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b){
            log.info("交换机收到id为{}的消息",correlationData.getId());
        }else {
            log.info("交换机未收到id为{}的消息",correlationData.getId());
        }
    }

    //消息不可达到队列，回退生产者
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息{},被交换机{}回退,回退原因:{},路由key:{}",returnedMessage.getMessage(),
                returnedMessage.getExchange(),returnedMessage.getReplyText(),returnedMessage.getRoutingKey());
    }
}
