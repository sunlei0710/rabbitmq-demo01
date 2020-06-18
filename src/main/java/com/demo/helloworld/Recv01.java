package com.demo.helloworld;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author qwq
 * @date 2020/6/15.
 * 消费者
 */
public class Recv01 {
    private static final String QUEUE_NAME = "simple_queue";
    public static void main(String[] args) throws Exception{
        //1.创建连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.创建消费者--消费消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //使用这个方法消费消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("消费者消费消息："+message);
            }
        };
        //5.监听队列是否成功消费
        /**
         * 第一个参数：队列名字
         * 第二个参数：是否自动确认消息消费，true，自动确认，false，手动确认
         * 第三个参数：消费者
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
