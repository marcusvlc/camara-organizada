package com.camara.cidada.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.camara.cidada.models.Deputy;

@Service
public class Consumer {
	
    @KafkaListener(topics = "deputy_topic", containerFactory = "userKafkaListenerFactory", groupId = "group_json")
    public void consumeJson(Deputy deputy) {
        System.out.println("-----------------------------");
    	System.out.println("Um novo deputado foi cadastrado na camara, confira suas informações:");
    	System.out.println("Nome: " + deputy.getName());
    	System.out.println("Partido: " + deputy.getParty());
    	System.out.println("Estado: " + deputy.getState());
    	System.out.println("DNI: " + deputy.getDni());
    	System.out.println("Quantidade de leis aprovadas: " + deputy.getApprovedLaws());
    }

}
