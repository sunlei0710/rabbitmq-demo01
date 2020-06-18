package com.demo.fanout;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * @author qwq
 * @date 2020/6/16.
 */
public class Send03 {

    private static final String EXCHANGE_NAME="exchange_test";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true);

        //消息内容
        String message = "hello 嗷嗷";

        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME,"", MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        System.out.println("生产者生产的消息:"+message);

        channel.close();
        connection.close();
    }
}
