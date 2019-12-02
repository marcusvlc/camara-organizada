package com.camara.organizada.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.camara.organizada.deputy.Deputy;
@Service
public class Producer {
private static final Logger logger = LoggerFactory.getLogger(Producer.class);
//private static final String TOPIC = "users";

@Autowired
private KafkaTemplate<String,Deputy> kafkaTemplate;

public void sendMessage(Deputy deputy, String topic){
	logger.info(String.format("$$ -> Producing a new deputy --> %s", deputy.getUser().getName()));
	this.kafkaTemplate.send(topic,deputy);
}

}