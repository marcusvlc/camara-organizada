//package com.camara.organizada.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import com.camara.organizada.deputy.Deputy;
//@Service
//public class Consumer {
//private final Logger logger = LoggerFactory.getLogger(Consumer.class);
//@KafkaListener(topics = "deputy_topic", groupId = "group_id")
//public void consume(Deputy deputy){
//logger.info(String.format("$$ -> Consumed Message -> %s",deputy));
//}
//}
