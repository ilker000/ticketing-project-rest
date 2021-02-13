package com.spring.util;

import com.spring.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {
    @Value("${security.jwt.secret-key}")
    private String secret = "spring";

    public String generateToken(User user){

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getFirstName());
        claims.put("id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        return createToken(claims, user.getUserName());

    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*60*10)) //10 hours token validity
                .signWith(SignatureAlgorithm.ES256, secret).compact();
    }

    private Claims extraAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extraClaim(String token, Function<Claims , T> claimsResolver){
        final Claims claims = extraAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extraClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extraClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
