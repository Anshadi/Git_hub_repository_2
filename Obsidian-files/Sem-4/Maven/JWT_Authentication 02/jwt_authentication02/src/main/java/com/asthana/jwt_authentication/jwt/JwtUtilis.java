package com.asthana.jwt_authentication.jwt;


import java.security.Key;
import java.util.Date;


import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;


public class JwtUtilis {

    Logger logger = LoggerFactory.getLogger(JwtUtilis.class);

    @Value("${jwt.Secret}")
    private String secret;

    @Value("${jwt.Expiration}")
    private int jwtExpiration;

    public String getJwtFromHeader(HttpServletRequest request){
        String BearerToken = request.getHeader("Authorization");
        //Bearer Token
        logger.debug("Bearer Token: {}", BearerToken);
        if(BearerToken != null && BearerToken.startsWith("Bearer ")){
            return BearerToken.substring(7);
        }
        return null;
    }
    
    public String getJwtTokenFromUsesrname(UserDetails userDetails){
        String username = userDetails.getUsername();
        return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(new Date().getTime() + jwtExpiration ))
        .signWith(key())
        .compact() ;
    }


    public String getUsernameFromToken(String Token){
        return Jwts.parser()
        .verifyWith((SecretKey) key())
        .build()
        .parseSignedClaims(Token)
        .getPayload()
        .getSubject();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean validateToken(String token){
        try {
            System.out.println("Validate");
            Jwts.parser()
            .verifyWith((SecretKey) key())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT Token");
        }
        catch(ExpiredJwtException e){
            logger.error("Expired JWT Token");
        }
        catch(UnsupportedJwtException e){
            logger.error("Unsupported JWT Token");
        }
        catch(IllegalArgumentException e){
            logger.error("JWT Claims String is empty");
        }
        return false;
    }
}



