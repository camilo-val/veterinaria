package com.gateway.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.PublicKey;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final PublicKey publicKey;

    public JwtAuthenticationFilter(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // ✅ Validación con JJWT 0.13.0
                Claims claims = Jwts.parser()
                        .verifyWith(publicKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                String username = claims.getSubject();
                String role = claims.get("roles", List.class).get(0).toString();

                // Crear Authentication reactivo
                Authentication authentication = new AbstractAuthenticationToken(
                        List.of(new SimpleGrantedAuthority(role))) {
                    @Override
                    public Object getCredentials() {
                        return null;
                    }

                    @Override
                    public Object getPrincipal() {
                        return username;
                    }
                };
                authentication.setAuthenticated(true);

                // Insertar en el contexto de seguridad reactivo
                SecurityContext context = new SecurityContextImpl(authentication);

                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }
}