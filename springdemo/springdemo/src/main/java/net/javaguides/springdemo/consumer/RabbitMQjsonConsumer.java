package net.javaguides.springdemo.consumer;

import com.rabbitmq.client.*;
import jakarta.annotation.PostConstruct;
import net.javaguides.springdemo.dto.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQjsonConsumer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json.key}")
    private String jsonroutingKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQjsonConsumer.class);
    private RabbitTemplate rabbitTemplate;
    public RabbitMQjsonConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    private SimpleMessageListenerContainer  container;
    @Autowired
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consume(Payment message){
        if(message.getid()==2) {
            LOGGER.info(String.format("Received message -> %s",message.toString()));
        }
        else {
            if(!message.isread())
            {
                LOGGER.info(String.format("Message Rejected"));
                message.setread();
                rabbitTemplate.convertAndSend(exchange, jsonroutingKey, message);
            }
            else {
                container.stop();
                LOGGER.info(String.format("Consumer stopped"));
            }
        }
    }
    public void setContainer(SimpleMessageListenerContainer container) {
        this.container = container;
    }
}
