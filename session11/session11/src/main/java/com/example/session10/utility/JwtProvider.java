package com.example.session10.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    // tạo token
    public String generateToken(Authentication authentication){
        // lấy username
        String username = authentication.getName();

        // lây role
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        Date now = new Date();

        Date expDate = new Date(now.getTime()+EXPIRATION);

        return Jwts.builder()
                .setSubject(username) // sub
                .claim("role", role) // custom claim
                .setIssuedAt(now) // iat
                .setExpiration(expDate) // exp
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // lấy username từ token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // validate token
    public boolean validateToken(String token) {
        try {

            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
