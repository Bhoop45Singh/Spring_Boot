package com.example.currencyConversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-services", url="http://localhost:8000")//when not using ribbon
//@FeignClient(name="currency-exchange-services") //when using feign client only
@FeignClient(name="netflix-zuul-api-geteway-server")   //when using zull need to zuul api name
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")        							 //when using feign client only
	@GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")			 //in initials add name first by which registered on eureka 
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from")String from, @PathVariable("to")String to);
	
}
