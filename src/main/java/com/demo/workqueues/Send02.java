package com.demo.workqueues;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author qwq
 * @date 2020/6/16.
 */
public class Send02 {
    private static final String QUEUE_NAME = "workqueue01";
    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //通过连接创建通道
        Channel channel = connection.createChannel();
        //通过通道创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息
        for (int i = 0; i <= 50; i++) {
            String message = "任务模型："+i;
            /**
             * 参数1:路由器
             * 参数2:队列名
             * 参数3:
             * 参数4:消息内容
             */
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(message);
        }
        //关闭通道
        channel.close();
        //关闭连接
        connection.close();
    }
}
