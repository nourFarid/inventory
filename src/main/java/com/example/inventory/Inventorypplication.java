package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Inventorypplication {

	public static void main(String[] args) {
		SpringApplication.run(Inventorypplication.class, args);


		System.setProperty("java.awt.headless", "false");
	}


}
