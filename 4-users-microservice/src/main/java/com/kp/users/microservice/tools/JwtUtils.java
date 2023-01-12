package com.kp.users.microservice.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kp.users.microservice.model.AuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.issuer}")
    private String tokenInssuer;

    public static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;


    public String generateJWToken(Authentication user, Date date, Algorithm algorithm) {
        List<String> claimsFromUser = getClaimsFromUser(user);
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(date)
                .withIssuer(tokenInssuer)
                .withClaim("roles", claimsFromUser)
                .sign(algorithm);
    }

    String generateJWTRefreshToken(Authentication user, Date date, Algorithm algorithm) {
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000))
                .withIssuer(tokenInssuer)
                .sign(algorithm);
    }

    private List<String> getClaimsFromUser(Authentication user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }

    public boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            jwtVerifier = JWT.require(algorithm).withIssuer(tokenInssuer).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Bad token");
        }
        return jwtVerifier;
    }

    public AuthenticationResponse setHttpHeaders(Authentication user, HttpServletResponse response) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        Date date = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String accessToken = generateJWToken(user, date, algorithm);
        String refreshToken = generateJWTRefreshToken(user, date, algorithm);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.addHeader("token", accessToken);
        response.addHeader("userId", user.getName());
        return new AuthenticationResponse(user.getName(), accessToken);
    }
}
