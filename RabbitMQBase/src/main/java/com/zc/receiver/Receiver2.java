package com.zc.receiver;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestQueue2")
public class Receiver2 {

    @RabbitHandler
    public void receiver(String msg){
        System.out.println("Test Receiver2:" + msg);
    }
}
