package com.kitm.library.backend.spring.web.config.security;

import com.kitm.library.api.authentication.IJwtTokenUtil;
import com.kitm.library.api.user.IUserEntity;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtil implements IJwtTokenUtil {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.issuer}")
  private String jwtIssuer;

  @Value("${jwt.expiration}")
  private String jwtExpiration;

  @Override
  public String generateAccessToken(IUserEntity userEntity) {
    return Jwts.builder()
        .setSubject(String.format("%s", userEntity.getUsername()))
        .setIssuer(jwtIssuer)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  @Override
  public String getUserId(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject().split(",")[0];
  }

  public String getUsername(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject().split(",")[1];
  }

  @Override
  public Date getExpirationDate(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    return claims.getExpiration();
  }

  @Override
  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
//      log.error("Invalid JWT signature - {}", ex.getMessage());
    } catch (MalformedJwtException ex) {
//      log.error("Invalid JWT token - {}", ex.getMessage());
    } catch (ExpiredJwtException ex) {
//      log.error("Expired JWT token - {}", ex.getMessage());
    } catch (UnsupportedJwtException ex) {
//      log.error("Unsupported JWT token - {}", ex.getMessage());
    } catch (IllegalArgumentException ex) {
//      log.error("JWT claims string is empty - {}", ex.getMessage());
    }
    return false;
  }

}
