package com.coolz.microservice.currencyexchange.resouce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coolz.microservice.currencyexchange.bean.CurrencyExchange;
import com.coolz.microservice.currencyexchange.repository.CurrencyExchangeRepository;

/**
 * @author Mahir
 *
 */
@RestController
public class CurrencyExchangeResource {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange currencyExchangeFromUSDtoINR(@PathVariable String from, @PathVariable String to) {
		_log.info("Currency Exchange From USD to INR called with {} to {} ", from , to);
		
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for " + from + " to " + to);
		}

		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

		return currencyExchange;
	}
	
	private Logger _log = LoggerFactory.getLogger(CurrencyExchangeResource.class);
}
