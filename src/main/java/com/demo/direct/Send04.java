package com.demo.direct;

import com.demo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author qwq
 * @date 2020/6/17.
 */
public class Send04 {

    private static final String EXCHANGE_NAME="direct_exchange_test01";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        //消息内容
        String message = "新增一个商品";
//        String message = "修改一个商品";
//        String message = "删除一个商品";

        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        System.out.println("生产者生产的消息:"+message);

        channel.close();
        connection.close();
    }
}
