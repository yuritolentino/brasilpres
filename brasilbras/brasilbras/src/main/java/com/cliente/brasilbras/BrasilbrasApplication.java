package com.cliente.brasilbras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BrasilbrasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrasilbrasApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
