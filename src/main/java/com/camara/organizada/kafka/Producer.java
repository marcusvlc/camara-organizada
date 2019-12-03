package com.camara.organizada.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.camara.organizada.deputy.Deputy;
import com.camara.organizada.deputy.DeputyKafkaDTO;
@Service
public class Producer {
private static final Logger logger = LoggerFactory.getLogger(Producer.class);
//private static final String TOPIC = "users";

@Autowired
private KafkaTemplate<String,DeputyKafkaDTO> kafkaTemplate;

public void sendMessage(DeputyKafkaDTO deputy, String topic){
	logger.info(String.format("Producing a new deputy --> %s", deputy.getName()));
	this.kafkaTemplate.send(topic,deputy);
}

}