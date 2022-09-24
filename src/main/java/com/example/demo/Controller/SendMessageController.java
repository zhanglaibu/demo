package com.example.demo.Controller;

import com.example.demo.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMessageController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendExpirationMsg/{message}/{delayedTime}")
    public void sedMsg(@PathVariable String message,@PathVariable Integer delayedTime){
        log.info("发送消息时间{},延迟时间为{}ms，消息为{}",new Date().toString(),delayedTime,message);
        /**
         * 1.交换机
         * 2.routingKey
         * 3.消息
         * 4.其他配置
         */
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,
                DelayedQueueConfig.DELAYED_ROUTING_KEY,message,msg->{
            //发送消息的延迟时长 单位:ms
            msg.getMessageProperties().setDelay(delayedTime);
            return msg;
        });
    }
}
