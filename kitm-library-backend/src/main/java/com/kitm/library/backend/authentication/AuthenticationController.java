package com.kitm.library.backend.authentication;

import com.kitm.library.backend.authentication.dto.AuthenticatedDto;
import com.kitm.library.backend.authentication.dto.LoginDto;
import com.kitm.library.backend.domain.user.UserEntity;
import com.kitm.library.backend.spring.web.config.security.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@RestController
@RequestMapping(path = "api/public")
@AllArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenUtil jwtTokenUtil;

  @PostMapping("login")
  public ResponseEntity<AuthenticatedDto> login(@RequestBody LoginDto request) {
    try {
      Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

      UserEntity userEntity = (UserEntity) authenticate.getPrincipal();

      String token = jwtTokenUtil.generateAccessToken(userEntity);
      Date expires = jwtTokenUtil.getExpirationDate(token);

      return ResponseEntity.ok()
          .header(HttpHeaders.AUTHORIZATION, token)
          .body(AuthenticatedDto.builder()
              .expires(expires)
              .build());
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
