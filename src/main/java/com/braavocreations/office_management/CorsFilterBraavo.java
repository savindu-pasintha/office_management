package com.braavocreations.office_management;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CorsFilterBraavo implements WebFilter {
    @Override
    public reactor.core.publisher.Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange != null){
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin","http://localhost:3000");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin","GET,PUT,POST,DELETE,OPTIONS");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin","DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS){
                exchange.getResponse().getHeaders().add("Access-Control-Max-Age","1728000");
                exchange.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
                return Mono.empty();
            }else {
                exchange.getResponse().getHeaders().add("Access-Control-Expose-Headers","DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
                if (chain != null){
                    return chain.filter(exchange);
                }else {
                    return Mono.empty();
                }
            }
        }else {
            if (chain != null){
                return chain.filter(exchange);
            }else {
                return Mono.empty();
            }

        }

    }
}
