package com.acelera.ti.stock.infrastructure.helpers.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().get("name", String.class);
    }

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            String bodyToken = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().toString();
            return true;
        } catch (MalformedJwtException e) {
            log.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            log.error("token no soportado");
        } catch (ExpiredJwtException e) {
            log.error("token expirado");
        } catch (IllegalArgumentException e) {
            log.error("token vacío");
        } finally {
            log.error("no se pudo validar el token");
        }
        return false;
    }
}

