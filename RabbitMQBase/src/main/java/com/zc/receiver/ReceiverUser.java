package com.zc.receiver;


import com.zc.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestQueueUser")
public class ReceiverUser {


    @RabbitHandler
    public void receiver(User user){
        System.out.println("TestQueueUser" + user);
    }


}
