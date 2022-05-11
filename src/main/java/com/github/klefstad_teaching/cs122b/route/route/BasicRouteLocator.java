package com.github.klefstad_teaching.cs122b.route.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BasicRouteLocator
{
    private static final String JSON_PLACEHOLDER_POSTS = "https://jsonplaceholder.typicode.com/posts";

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder)
    {
        return builder.routes()
                      .route("JSON Placeholder posts",
                             r -> r.path("/**").uri(JSON_PLACEHOLDER_POSTS))
                      .build();
    }
}
