package net.javaguides.springdemo.publisher;

import net.javaguides.springdemo.dto.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqjsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json.key}")
    private String jsonroutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqjsonProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitmqjsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendjsonMessage(Payment payment)
    {
        LOGGER.info(String.format("Json Message sent -> %s",payment.toString()));
        rabbitTemplate.convertAndSend(exchange, jsonroutingKey, payment);
    }
}
