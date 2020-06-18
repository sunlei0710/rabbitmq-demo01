package com.demo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author qwq
 * @date 2020/6/15.
 */
public class ConnectionUtil {
    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务器地址
        connectionFactory.setHost("localhost");
        //端口5672
        connectionFactory.setPort(5672);
        //设置登录的用户名和密码及虚拟访问路径virtual host
        connectionFactory.setUsername("czxy");
        connectionFactory.setPassword("czxy");
        connectionFactory.setVirtualHost("/czxy");
        //5.通过连接工厂获取连接
        Connection connection = connectionFactory.newConnection();
        //返回连接
        return connection;
    }
}
