package com.heavydelay.BandsSync.Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.heavydelay.BandsSync.Api.model.entity")
public class BandsSyncApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandsSyncApiApplication.class, args);
	}

}
