package com.nzxs2.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @Author Ryan
 * @Date 2017/9/22 12:50
 * @Function
 */
@Component
public class CallbackSender implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.setConfirmCallback(this);
        String sendMsg = msg + new Date();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Callbacksender : " + sendMsg);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // TODO Auto-generated method stub
        System.out.println("callbakck confirm: " + correlationData.getId());
    }
}
