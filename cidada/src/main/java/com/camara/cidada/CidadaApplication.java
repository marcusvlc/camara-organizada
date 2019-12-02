package com.camara.cidada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class CidadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CidadaApplication.class, args);
	}

}
