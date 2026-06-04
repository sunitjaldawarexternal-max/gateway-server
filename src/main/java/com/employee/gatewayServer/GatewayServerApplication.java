package com.employee.gatewayServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
    @Bean
    RouteLocator routesForEmployeeManage(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("employee-route", r -> r
                        .path("/api/employees/**", "/api/get/**")
                        .uri("http://localhost:8080"))
                .build();
    }
    @Bean
    RouteLocator routesForReference(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("reference-route", r -> r
                        .path("/api/references/**", "/api/reference/**")
                        .uri("http://localhost:8081"))
                .build();
    }
    @Bean
    RouteLocator routesForH2ConsoleOfEmployeeManage(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("employee-h2-console", r -> r
                        .path("/employee-h2")
                        .filters(f -> f.rewritePath("/employee-h2", "/h2-console"))
                        .uri("http://localhost:8080"))
                .build();
    }

    @Bean
    RouteLocator routesForH2ConsoleOfReference(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("reference-h2-console", r -> r
                        .path("/reference-h2")
                        .filters(f -> f.rewritePath("/reference-h2", "/h2-console"))
                        .uri("http://localhost:8081"))
                .build();
    }


}
