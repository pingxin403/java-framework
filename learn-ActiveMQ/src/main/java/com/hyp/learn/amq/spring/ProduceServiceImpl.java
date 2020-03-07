package com.hyp.learn.amq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.amq.spring
 * hyp create at 20-2-9
 **/
@Component("topicSender")
public class ProduceServiceImpl implements ProduceService {
    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
    @Resource(name = "queueDestination")
    private Destination destination;

    /**
     * 发送消息
     * @param msg
     */
    @Override
    public void sendMessage(final String msg) {
        jmsTemplate.send(destination , new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(msg);
                return textMessage;
            }
        });
        System.out.println("现在发送的消息为： " + msg);
    }
}
