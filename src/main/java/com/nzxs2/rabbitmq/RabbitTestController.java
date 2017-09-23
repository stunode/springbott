package com.nzxs2.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ryan
 * @Date 2017/9/22 1:24
 * @Function
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitTestController {

    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private UserSender userSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private CallbackSender callbackSender;


    @PostMapping("/hello")
    public void hello() {
        helloSender1.send("hello");
    }

    /**
     * 单生产者-多消费者
     */
    @PostMapping("/oneToMany")
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
        }

    }

    /**
     * 实体类传输测试
     */
    @PostMapping("/userTest")
    public void userTest() {
        userSender.send();
    }
    /**
     * topic exchange类型rabbitmq测试
     */
    @PostMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    @PostMapping("/callback")
    public void callbak() {
        callbackSender.send("callback");
    }
}
