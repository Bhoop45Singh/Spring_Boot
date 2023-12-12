package com.example.currencyConversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CurrencyConversionController {

	
	/*@Autowired
	private Environment environment;*/
	
	@Autowired
	CurrencyExchangeServiceProxy proxy; 
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@GetMapping("/currency-convertor/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
//		//CurrencyConversionBean exchangeValue = repo.findByFromAndTo(from,to);
//		//exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
//		Map<String,String> uriVariables = new HashMap<>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//		ResponseEntity<CurrencyConversionBean> currencyConversionBean = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}"
//				, CurrencyConversionBean.class,uriVariables);
//		CurrencyConversionBean CurrencyConversionBeanEdited = currencyConversionBean.getBody();
//		CurrencyConversionBeanEdited.setTotalCalculatedAmount(new BigDecimal(CurrencyConversionBeanEdited.getConversionMultiple().intValue()*quantity.intValue()));
//		logger.info("Hello friends old -> {} ", currencyConversionBean);
//		return CurrencyConversionBeanEdited;
//	}
//	
//	@GetMapping("/currency-convertor-feign/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversionBean convertCurrencyFeign(@PathVariable(name="from") String fromC, @PathVariable String to, @PathVariable BigDecimal quantity) {
//		//CurrencyConversionBean exchangeValue = repo.findByFromAndTo(from,to);
//		//exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
//		Map<String,String> uriVariables = new HashMap<>();
//		uriVariables.put("from", fromC);
//		uriVariables.put("to", to);
//		ResponseEntity<CurrencyConversionBean> currencyConversionBean = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}"
//				, CurrencyConversionBean.class,uriVariables);
//		CurrencyConversionBean CurrencyConversionBeanEdited = proxy.convertCurrency(fromC, to);
//				/*currencyConversionBean.getBody();*/
//		CurrencyConversionBeanEdited.setQuantity(quantity);
//		CurrencyConversionBeanEdited.setTotalCalculatedAmount(new BigDecimal(CurrencyConversionBeanEdited.getConversionMultiple().intValue()*quantity.intValue()));
//		logger.info("Hello friends -> {} ", currencyConversionBean);
//		return CurrencyConversionBeanEdited;
//	}
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// Feign - Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		logger.info("{}", response);
		
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

}
