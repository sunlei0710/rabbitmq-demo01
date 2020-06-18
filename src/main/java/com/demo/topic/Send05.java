package com.demo.topic;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author qwq
 * @date 2020/6/17.
 */
public class Send05 {

    private static final String EXCHANGE_NAME="topic_exchange_test01";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //消息内容一个商品
        String message = "新增";
//        String message = "修改一个商品";
//        String message = "删除一个商品";

        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME,"item.delete",null,message.getBytes());
        System.out.println("生产者生产的消息:"+message);

        channel.close();
        connection.close();
    }
}
