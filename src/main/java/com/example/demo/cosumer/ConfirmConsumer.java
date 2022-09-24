package com.example.demo.cosumer;

import com.example.demo.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody());
        log.info("收到的消息为:{}",msg);
    }

    @RabbitListener(queues = ConfirmConfig.BACKUP_QUEUE_NAME)
    public void receiveBackupMessage(Message message){
        String msg = new String(message.getBody());
        log.info("备份收到的消息为:{}",msg);
    }
}
