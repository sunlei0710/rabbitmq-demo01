package com.demo;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "spring.test.queue",durable = "true"),
            exchange = @Exchange(
                    value = "spring.text.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"a.b"}
    ))
    public void listen(String msg){
        System.out.println("接到消息"+msg);
    }
}
