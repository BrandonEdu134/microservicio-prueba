package com.usuario.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsUsuarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUsuarioServiceApplication.class, args);
	}

}
