package com.demo.helloworld;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author qwq
 * @date 2020/6/15.
 * 生产者
 */
public class Send01 {
    /**
     * 常量名
     */
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception{

        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.从连接中创建通道Channel，只有使用通道才可以完成消息的相关操作
        Channel channel = connection.createChannel();
        //3.通过通道声明(创建)队列
        /**
         * 参数1：队列名
         * 参数2：
         * 参数3：
         * 参数4：
         * 参数5：
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.发送消息
        String message = "hello world!";
        /**
         * 参数1：exchange路由器
         * 参数2：队列名字
         * 参数3：路由的其他配置信息
         * 参数4：消息
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        //5.关闭通道
        channel.close();
        System.out.println("生产者发送消息："+message);
        //6.关闭连接
        connection.close();
    }
}
