package org.nespolino.dhis2integrationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@RequiredArgsConstructor
public class Dhis2IntegrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dhis2IntegrationServiceApplication.class, args);
	}

}
