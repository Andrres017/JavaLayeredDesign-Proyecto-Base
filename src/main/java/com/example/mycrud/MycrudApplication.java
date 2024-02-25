package com.example.mycrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MycrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycrudApplication.class, args);
	}
}
