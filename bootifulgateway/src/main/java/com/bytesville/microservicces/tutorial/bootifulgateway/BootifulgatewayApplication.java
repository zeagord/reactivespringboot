package com.bytesville.microservicces.tutorial.bootifulgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.cloud.gateway.filter.factory.SecureHeadersGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.host;
import static org.springframework.tuple.TupleBuilder.tuple;
import static org.springframework.cloud.gateway.handler.predicate.RoutePredicates.path;


@SpringBootApplication
public class BootifulgatewayApplication implements ApplicationRunner {

//	@Autowired
//	LoadBalancerClientFilter loadBalancerClient;

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Configuration
	@EnableDiscoveryClient
	protected static class GatewayDiscoveryConfiguration {

		@Bean
		public DiscoveryClientRouteDefinitionLocator discoveryClientRouteLocator(DiscoveryClient discoveryClient) {
			return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BootifulgatewayApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ServiceInstance serviceInstance =  loadBalancerClient.choose("REACTIVE-SEC-DEMO");
		System.out.println("################## ---> " + serviceInstance.getUri());
	}

	public RouteLocator customRouteLocator (SecureHeadersGatewayFilterFactory secureFilter) {
		return Routes.locator()
				.route("gateway")
				.predicate(path("/bookservice"))
				.uri(loadBalancerClient.choose("REACTIVE-SEC-DEMO").getUri())
				.build();
	}

}
