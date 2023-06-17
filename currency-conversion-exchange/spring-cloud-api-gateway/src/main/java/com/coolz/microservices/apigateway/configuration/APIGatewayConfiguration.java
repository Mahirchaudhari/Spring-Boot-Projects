package com.coolz.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mahir
 *
 */
@Configuration
public class APIGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder.routes()
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion-v2/**")
						.filters(f -> f.rewritePath("/currency-conversion-v2/", "/currency-conversion-feign/"))
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion/**") 
						.uri("lb://currency-conversion"))
				.build();
	}
}
