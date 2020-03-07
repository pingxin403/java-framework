package com.hyp.learn.rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.rmq
 * hyp create at 20-1-22
 **/
public class NewTask {
    private static final String TASK_QUEUE_NAME = "task_queue";

    private static String[] msgs =
            {
                    "First message.",
                    "Second message..",
                    "Third message...",
                    "Fourth message....",
                    "Fifth message....."
            };

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost("my_vhost");
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            for (String message : msgs) {
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }

        }
    }
}
