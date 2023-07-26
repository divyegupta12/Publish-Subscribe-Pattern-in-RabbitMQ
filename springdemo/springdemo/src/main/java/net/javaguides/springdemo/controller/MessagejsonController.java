package net.javaguides.springdemo.controller;

import net.javaguides.springdemo.dto.Payment;
import net.javaguides.springdemo.publisher.RabbitmqProducer;
import net.javaguides.springdemo.publisher.RabbitmqjsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessagejsonController {
    private RabbitmqjsonProducer jsonproducer;

    public MessagejsonController(RabbitmqjsonProducer jsonproducer) {
        this.jsonproducer = jsonproducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendjsonMessage(@RequestBody Payment payment){
        jsonproducer.sendjsonMessage(payment);
        return ResponseEntity.ok("Json Message sent to RabbitMQ....");
    }
}
