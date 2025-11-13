package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.adapter;

import com.veterinaria.authentication.domain.model.Role;
import com.veterinaria.authentication.domain.model.Token;
import com.veterinaria.authentication.domain.model.gateway.JwtGateway;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.mapper.TokenAdapterMapper;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.model.TokenModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtService implements JwtGateway {
    private final TokenAdapterMapper tokenAdapterMapper;
    private final PrivateKey privateKey;
    private final String issuer;
    private final long expirationSeconds;
    private final long refreshExpirationSeconds;

    public JwtService(
            TokenAdapterMapper tokenAdapterMapper,
            PrivateKey privateKey,
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.expirationSeconds}") long expirationSeconds,
            @Value("${jwt.refreshExpirationSeconds}") long refreshExpirationSeconds
    ) {
        this.tokenAdapterMapper = tokenAdapterMapper;
        this.privateKey = privateKey;
        this.issuer = issuer;
        this.expirationSeconds = expirationSeconds;
        this.refreshExpirationSeconds = refreshExpirationSeconds;
    }

    @Override
    public Token generateJwt(String username, Role role) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationSeconds);

        String token = Jwts.builder()
                .subject(username)
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("roles", List.of(role))
                .signWith(privateKey, SignatureAlgorithm.RS256) // 🔑 firma con privada
                .compact();

        return tokenAdapterMapper.toDomain(new TokenModel(token, exp, now));
    }

    @Override
    public Token refreshToken(String username, Role role) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(refreshExpirationSeconds);

        String token = Jwts.builder()
                .subject(username)
                .issuer(issuer + ":refresh")
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("type", "refresh")
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();

        return tokenAdapterMapper.toDomain(new TokenModel(token, exp, now));
    }

}

