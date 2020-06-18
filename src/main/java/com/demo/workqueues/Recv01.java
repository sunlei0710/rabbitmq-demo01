package com.demo.workqueues;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


/**
 * @author qwq
 * @date 2020/6/16.
 */
public class Recv01 {

    private static final String QUEUE_NAME = "workqueue01";

    public static void main(String[] args)throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //通过连接创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //开启能者多劳
        channel.basicQos(1);
        //创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("消1："+message);
                //模拟任务耗时500毫秒
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
