package com.example.netflixzuulapigetewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@EnableEurekaClient
@EnableZuulProxy
//@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGetewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGetewayServerApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
}
