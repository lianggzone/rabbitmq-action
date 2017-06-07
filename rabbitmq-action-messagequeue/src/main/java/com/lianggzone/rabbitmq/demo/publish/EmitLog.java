package com.lianggzone.rabbitmq.demo.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <h3>概要:</h3><p>EmitLog</p>
 * <h3>功能:</h3><p>发送端/生产者</p>
 * <h3>履历:</h3>
 * <li>2017年6月7日  v0.1 版本内容: 新建</li>
 * @author 粱桂钊
 * @since 0.1
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接 
        Connection connection = factory.newConnection();
        // 创建一个通道 
        Channel channel = connection.createChannel();    
        // 指定一个交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 发送消息  
        String message = "Liang-MSG log.";  
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());  
        System.out.println(" [x] Sent '" + message + "'");  
        
        // 关闭频道和连接  
        channel.close();
        connection.close();
    }
}
