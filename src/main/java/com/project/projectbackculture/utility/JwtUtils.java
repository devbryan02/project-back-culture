package com.project.projectbackculture.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.projectbackculture.exception.JWTValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtils {

    //variables en entorno del sistema 🔒
    @Value("${jwt.key}")
    private String privateKey;
    @Value("${jwt.user.generator}")
    private String userGenarator;

    //created token
    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        String username = authentication.getPrincipal().toString();

        String authorities = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT
                .create()
                .withIssuer(this.userGenarator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    // validaded token
    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .withIssuer(this.userGenarator)
                    .build();
            return verifier.verify(token);
        } catch (AlgorithmMismatchException e) {
            log.error("Algorithm mismatch in JWT validation: {}", e.getMessage());
            throw new JWTValidationException("El algoritmo del token no coincide");
        } catch (SignatureVerificationException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            throw new JWTValidationException("La firma del token es inválida");
        } catch (TokenExpiredException e) {
            log.error("JWT token expired: {}", e.getMessage());
            throw new JWTValidationException("El token ha expirado");
        } catch (InvalidClaimException e) {
            log.error("Invalid claim in JWT: {}", e.getMessage());
            throw new JWTValidationException("El token contiene claims inválidos");
        } catch (JWTVerificationException e) {
            log.error("JWT verification failed: {}", e.getMessage());
            throw new JWTValidationException("Token inválido");
        }
    }

    public String extracUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }


}