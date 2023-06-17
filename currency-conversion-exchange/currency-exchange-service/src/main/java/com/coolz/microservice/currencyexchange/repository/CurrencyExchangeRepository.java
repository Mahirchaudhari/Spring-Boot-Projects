package com.coolz.microservice.currencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolz.microservice.currencyexchange.bean.CurrencyExchange;

/**
 * @author Mahir
 *
 */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	CurrencyExchange findByFromAndTo(String from, String to);
}
