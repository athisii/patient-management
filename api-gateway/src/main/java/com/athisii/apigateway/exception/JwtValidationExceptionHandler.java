package com.athisii.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/7/25
 */

@RestControllerAdvice
public class JwtValidationExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public Mono<Void> handleWebClientResponseException(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
