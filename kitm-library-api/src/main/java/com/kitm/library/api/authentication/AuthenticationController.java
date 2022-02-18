package com.kitm.library.api.authentication;

import com.kitm.library.api.authentication.dto.AuthenticatedDto;
import com.kitm.library.api.authentication.dto.LoginDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Api(value = "Authentication")
@RestController
@RequestMapping(path = "/api/public")
@AllArgsConstructor
public class AuthenticationController {
  private final IAuthenticationService authenticationService;

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticatedDto> login(@RequestBody @Valid LoginDto loginDto) {
    AuthenticatedDto authenticatedDto = authenticationService.login(loginDto);

    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, authenticatedDto.getToken())
        .body(authenticatedDto);
  }
}
