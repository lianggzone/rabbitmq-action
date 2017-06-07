package com.lianggzone.rabbitmq.demo.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <h3>概要:</h3><p>NewTask</p>
 * <h3>功能:</h3><p>发送端/生产者</p>
 * <h3>履历:</h3>
 * <li>2017年6月6日  v0.1 版本内容: 新建</li>
 * @author 粱桂钊
 * @since 0.1
 */
public class NewTask {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接 
        Connection connection = factory.newConnection();
        // 创建一个通道 
        Channel channel = connection.createChannel();    
        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息 
        for (int i = 0; i < 10; i++) {  
            String message = "Liang:" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
            System.out.println(" [x] Sent '" + message + "'");  
        }  
        // 关闭频道和连接  
        channel.close();
        connection.close();
    }
}
