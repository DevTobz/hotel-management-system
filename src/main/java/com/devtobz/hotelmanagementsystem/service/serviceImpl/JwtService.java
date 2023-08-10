package com.devtobz.hotelmanagementsystem.service.serviceImpl;

import com.devtobz.hotelmanagementsystem.config.EmployeeUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service 
public class JwtService {

    private final static String secret_key = "655368566D597133743677397A24432646294A404E635166546A576E5A723475";

    public String extractUsername(String token) {
        return extractClaims(token,claims -> claims.getSubject());
    }

    private Claims extractAllClaims(String token){
       return  Jwts.parserBuilder()
                        .setSigningKey(getSignInKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean tokenIsValid(EmployeeUserDetails employeeUserDetails, String token) {
        String username = employeeUserDetails.getUsername();
        return extractUsername(token).equals(username) &&  !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,claims -> claims.getExpiration());
    }

    public String generateToken(Map<String, Objects> extraClaims, EmployeeUserDetails employeeUserDetails) {
       return  Jwts.builder().setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(employeeUserDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(EmployeeUserDetails employeeUserDetails){
        return generateToken(new HashMap<>(),employeeUserDetails);
    }
}
