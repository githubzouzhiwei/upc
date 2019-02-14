package com.zzw.upc;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// 设置时区
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		SpringApplication.run(Application.class, args);
	}

}
