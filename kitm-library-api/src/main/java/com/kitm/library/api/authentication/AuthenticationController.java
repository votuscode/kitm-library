package com.kitm.library.api.authentication;

import com.kitm.library.api.authentication.dto.AuthenticatedDto;
import com.kitm.library.api.authentication.dto.LoginDto;
import com.kitm.library.api.user.IUserEntity;
import io.swagger.annotations.Api;
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

import javax.validation.Valid;
import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Api(value = "Authentication")
@RestController
@RequestMapping(path = "api/public")
@AllArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final IJwtTokenUtil jwtTokenUtil;

  @PostMapping("login")
  public ResponseEntity<AuthenticatedDto> login(@RequestBody @Valid LoginDto request) {
    try {
      Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

      IUserEntity userEntity = (IUserEntity) authenticate.getPrincipal();

      String token = jwtTokenUtil.generateAccessToken(userEntity);
      Date expires = jwtTokenUtil.getExpirationDate(token);

      return ResponseEntity.ok()
          .header(HttpHeaders.AUTHORIZATION, token)
          .body(AuthenticatedDto.builder()
              .expires(expires)
              .build());
    } catch (BadCredentialsException exception) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
