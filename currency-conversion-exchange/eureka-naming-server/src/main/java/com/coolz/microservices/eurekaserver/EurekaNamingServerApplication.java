package com.coolz.microservices.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Mahir
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaNamingServerApplication.class, args);
	}

}
