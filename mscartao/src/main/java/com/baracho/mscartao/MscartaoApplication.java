package com.baracho.mscartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MscartaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscartaoApplication.class, args);
	}

}
