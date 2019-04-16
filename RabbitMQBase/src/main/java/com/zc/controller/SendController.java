package com.zc.controller;


import com.zc.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class SendController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("send")
    public String send(){
        String content = "Date:" + new Date();
        amqpTemplate.convertAndSend("TestQueue1",content);
        return content;
    }

    @RequestMapping("multiSend")
    public String multiSend(){
        StringBuilder times = new StringBuilder();
        for (int i = 0; i < 10; i++){
            long time = System.nanoTime();
            amqpTemplate.convertAndSend("TestQueue1","第"+i+"次发送的时间："+time);
            amqpTemplate.convertAndSend("TestQueue2","第"+i+"次发送的时间："+time);
            times.append(time+"<br>");
        }
        return times.toString();
    }

    @RequestMapping("sendUser")
    public String multiSendUser(){
        User user = new User("张三","20");
        amqpTemplate.convertAndSend("TestQueueUser",user);
        return user.toString();
    }


    @RequestMapping("topSend1")
    public String topSend1(){
        String context = "my topic 1";
        System.out.println("生产者message说" + context);
        this.amqpTemplate.convertAndSend("exchange","topic.message",context);
        return context;
    }

    @RequestMapping("topSend2")
    public String topSend2(){
        String context = "my topic 2";
        System.out.println("生产者messages说:" + context);
        this.amqpTemplate.convertAndSend("exchange","topic.messages",context);
        return context;
    }


}
