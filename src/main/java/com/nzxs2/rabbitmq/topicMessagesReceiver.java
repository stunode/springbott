package com.nzxs2.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Ryan
 * @Date 2017/9/22 12:31
 * @Function
 */
@Component
@RabbitListener(queues = "topic.messages")
public class topicMessagesReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessagesReceiver  : " +msg);
    }

}
