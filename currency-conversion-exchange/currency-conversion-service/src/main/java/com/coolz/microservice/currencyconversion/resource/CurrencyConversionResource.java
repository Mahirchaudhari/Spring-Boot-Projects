package com.coolz.microservice.currencyconversion.resource;

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

import com.coolz.microservice.currencyconversion.bean.CurrencyConversion;
import com.coolz.microservice.currencyconversion.proxy.CurrencyExchangeProxy;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

/**
 * @CircuitBreaker Use of the Circuit Breaker pattern can allow a microservice
 *                 to continue operating when a related service fails,
 *                 preventing the failure from cascading and giving the failing
 *                 service time to recover.
 * 
 * @RateLimiter Rate limiting is a strategy to limit access to APIs. It
 *              restricts the number of API calls that a client can make within
 *              a certain time frame. This helps defend the API against overuse,
 *              both unintentional and malicious.
 *
 * @Bulkhead Bulk head can be used to limit the number of concurrent execution
 * 
 * @author Mahir
 *
 */
@RestController
public class CurrencyConversionResource {

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@Autowired
    private RestTemplate restTemplate;

	/**
	 * Method used to calculate and currency conversion
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return CurrencyConversion
	 */
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	@CircuitBreaker(name = "currency-conversion", fallbackMethod = "returnDefaultResponse")
	@RateLimiter(name = "currency-conversion")
	@Bulkhead(name = "currency-conversion")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		_log.info("Calling Currency Conversion Feign MicroService");

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseCurrencyConversion = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

		CurrencyConversion conversion = responseCurrencyConversion.getBody();

		return new CurrencyConversion(conversion.getId(), from, to, conversion.getConversionMultiple(), quantity,
				quantity.multiply(conversion.getConversionMultiple()), conversion.getEnvironment());
	}

	/**
	 * Method used to calculate and currency conversion using feign approach.
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 */
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	@CircuitBreaker(name = "currency-conversion", fallbackMethod = "returnDefaultResponse")
	@RateLimiter(name = "currency-conversion")
	@Bulkhead(name = "currency-conversion")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		_log.info("Calling Currency Conversion Feign MicroService");

		CurrencyConversion conversion = currencyExchangeProxy.currencyExchangeFromUSDtoINR(from, to);
		
		_log.info("Response From Currency Exchange Service : " + conversion);

		return new CurrencyConversion(conversion.getId(), from, to, conversion.getConversionMultiple(), quantity,
				quantity.multiply(conversion.getConversionMultiple()), conversion.getEnvironment());
	}

	public CurrencyConversion returnDefaultResponse(String from, String to, BigDecimal quantity, Exception ex) {
		_log.info("Returning Default Response For Currency Conversion Service");
		return new CurrencyConversion();
	}

	private Logger _log = LoggerFactory.getLogger(CurrencyConversionResource.class);
}
