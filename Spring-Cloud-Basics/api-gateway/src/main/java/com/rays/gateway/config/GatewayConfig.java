package com.rays.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * It defines microservice routers. It routes requests to respective
 * microservice using service name regsitered at discovery server. For example
 * service01, service02, and ORS10 are names registered with discovery server.
 * 
 */

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("service3", r -> r.path("/service3/**").uri("lb://service3"))
				.route("service05", r -> r.path("/service05/**").uri("lb://service05"))
				.route("service04", r -> r.path("/service04/**").uri("lb://service04"))
				.route("ORS10", r -> r.path("/ORS10/**").uri("lb://ORS10")).build();

	}
}
