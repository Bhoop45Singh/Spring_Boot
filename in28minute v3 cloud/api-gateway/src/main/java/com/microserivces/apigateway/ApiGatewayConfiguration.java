package com.microserivces.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
//	@Bean
//	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//		//Simple redirection one 
////		Function<PredicateSpec, Buildable<Route>> routeFunction
////		=p->p.path("/get")
////			.uri("http://httpbin.org:80");
////		return builder.routes().route(routeFunction).build();
//		
//		//adding Header andparam parameters
////		return builder.routes().route(p->p.path("/get")
////			.filters(f->f
////					.addRequestHeader("MyHeader", "MyURI")
////					.addRequestParameter("MyParam", "MyValue"))
////			.uri("http://httpbin.org:80")).build();
//		
//		//Adding custome route
//		return builder.routes().route(p->p.path("/get")
//				.filters(f->f
//						.addRequestHeader("MyHeader", "MyURI")
//						.addRequestParameter("MyParam", "MyValue"))
//				.uri("http://httpbin.org:80"))
//				.route(p->p.path("/currency-exchange/**")		  //starting with and followed by any
//						.uri("lb://currency-exchange"))			//lb for load balancing and registered name n eureka 
//				.route(p->p.path("/currency-conversion/**")		  //starting with and followed by any
//						.uri("lb://currency-conversion"))			//lb for load balancing and registered name n eureka 
//				.route(p->p.path("/currency-conversion-feign/**")		  //starting with and followed by any
//						.uri("lb://currency-conversion"))			//lb for load balancing and registered name n eureka 
//				
//				.build();  
//		
//		
//		//without additional api
////		return builder.routes()
////				.route(p->p.path("/currency-exchange/**")		  //starting with and followed by any
////						.uri("lb://currency-exchange"))			//lb for load balancing and registered name n eureka 
////				.route(p->p.path("/currency-conversion/**")		  //starting with and followed by any
////						.uri("lb://currency-conversion"))			//lb for load balancing and registered name n eureka 
////				.route(p->p.path("/currency-conversion-feign/**")		  //starting with and followed by any
////						.uri("lb://currency-conversion"))			//lb for load balancing and registered name n eureka 
////				
////				.build();  
//		
//		
//	}
//		
//	
//		
////		return builder.routes()
////				.route(p -> p
////						.path("/get")
////						.filters(f -> f
////								.addRequestHeader("MyHeader", "MyURI")
////								.addRequestParameter("Param", "MyValue"))
////						.uri("http://httpbin.org:80"))
////				.route(p -> p.path("/currency-exchange/**")
////						.uri("lb://currency-exchange"))
////				.route(p -> p.path("/currency-conversion/**")
////						.uri("lb://currency-conversion"))
////				.route(p -> p.path("/currency-conversion-feign/**")
////						.uri("lb://currency-conversion"))
////				.route(p -> p.path("/currency-conversion-new/**")
////						.filters(f -> f.rewritePath(
////								"/currency-conversion-new/(?<segment>.*)", 
////								"/currency-conversion-feign/${segment}"))
////						.uri("lb://currency-conversion"))
////				.build();
////	}

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}
}
