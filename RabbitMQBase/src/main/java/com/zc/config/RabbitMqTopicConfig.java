package com.zc.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage(){
        return new Queue(RabbitMqTopicConfig.message);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(RabbitMqTopicConfig.messages);
    }



    @Bean  //配置exchange,并且设置其ExchangeType为topic,ExchangeName:exchange
    TopicExchange exchange(){
        return new TopicExchange("exchange",true,false);
    }

    //创建交换机和消息队列之间的绑定，并设置路由规则
    @Bean //如果参数类型所对应的实例在spring容器中只有一个，则默认选择这个实例。
          // 如果有多个，则需要根据参数名称来选择（参数名称就相当于是spring的配置文件中的bean的id）
          //@Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同；
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
    //topic支持模糊匹配,order.*支持order.abc不支持order.a.abc,但是order.#支持以order.开头的所有.
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.*");
    }
}
