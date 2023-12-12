package com.example.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.limitsservice.beans.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RefreshScope
@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public @ResponseBody LimitConfiguration retrieveLimitsFromConfigurations() {
		LimitConfiguration limitConfiguration = new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
		return limitConfiguration;
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrivelConfig")
	public LimitConfiguration retriveConfiguration(){
		throw new RuntimeException("not available");
	}
	
	public LimitConfiguration fallbackRetrivelConfig(){
		return new LimitConfiguration(9,999);
	}
}
