package com.nzxs2;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot 应用启动类
 */
@SpringBootApplication
@MapperScan("com.nzxs2.dao")
public class Application {
    final static String queueName = "hello";

    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("userQueue");
    }
//
//    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }
//    //===============以上是验证topic Exchange的队列==========
//
//
//    //===============以下是验证Fanout Exchange的队列==========
//    @Bean
//    public Queue AMessage() {
//        return new Queue("fanout.A");
//    }
//
//    @Bean
//    public Queue BMessage() {
//        return new Queue("fanout.B");
//    }
//
//    @Bean
//    public Queue CMessage() {
//        return new Queue("fanout.C");
//    }
//    //===============以上是验证Fanout Exchange的队列==========
//
//
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanoutExchange");
//    }
//
    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
//
//    @Bean
//    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(AMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(BMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(CMessage).to(fanoutExchange);
//    }
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class, args);
    }

    //    @Bean
//    CommandLineRunner demo(ArticleDao articleDao) {
//        return args -> {
//
//            List<Article> articles = Arrays.asList(new Article("zsadasd","http:dsadsadadsdasd"),new Article("zsadasdsa","http:dsadsadadsdasd"));
//            articles.forEach(article -> {
//                articleDao.insert(article);
//                System.err.println("插入数据:" + article.toString());
//            });
//        };
//    }

}