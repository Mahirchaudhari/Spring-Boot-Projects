package com.coolz.microservice.currencyconversion.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coolz.microservice.currencyconversion.bean.CurrencyConversion;

/**
 * CurrencyExchangeProxy used to call microservice API without defining URL.
 * 
 * put same name which is defined in currency exchange application properties
 * 
 * @author Mahir
 *
 */
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion currencyExchangeFromUSDtoINR(@PathVariable String from, @PathVariable String to);

}
