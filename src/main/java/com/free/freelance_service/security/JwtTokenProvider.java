package com.free.freelance_service.security;

import com.free.freelance_service.exeption.JwtCustomException;
import com.free.freelance_service.repo.UserRepo;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private final UserRepo userRepo;

    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.expiration}")
    private long validateMillisec;

    public JwtTokenProvider(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userName, String role, String userId) {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("role", role);
        claims.put("id", userId);
        Date now = new Date();
        Date validate = new Date(now.getTime() + validateMillisec);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validate).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claimsJws.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtCustomException("token is invalid or expired", HttpStatus.FORBIDDEN);
        }
    }

    public CustomAuth getAuthentication(String token) {
        return new CustomAuth(userRepo.findFirstByLogin(getUserName(token)));
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(header);
    }
}
