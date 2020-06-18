package com.demo.fanout;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author qwq
 * @date 2020/6/16.
 */
public class Rec02 {

    private static final String EXCHANGE_NAME="exchange_test";
    private static final String QUEUE_NAME = "exchange_test_2";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("消2："+message);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
