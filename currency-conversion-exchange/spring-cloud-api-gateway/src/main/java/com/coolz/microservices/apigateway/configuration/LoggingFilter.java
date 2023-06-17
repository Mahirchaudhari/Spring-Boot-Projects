package com.coolz.microservices.apigateway.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author Mahir
 *
 */
@Component
public class LoggingFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		_log.info("Path of the request is : " + exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

	private Logger _log = LoggerFactory.getLogger(LoggingFilter.class);

}
