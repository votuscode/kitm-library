package com.kitm.library.backend.admin;

import com.kitm.library.api.authentication.dto.AuthenticatedDto;
import com.kitm.library.backend.domain.authentication.AuthenticationService;
import com.kitm.library.backend.domain.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

  private final AuthenticationService authenticationService;

  @GetMapping("/login")
  public String login() {

    log.info("login");

    return "login";
  }

  @GetMapping("/")
  public String root(Authentication authentication) {

    log.info("root");

    UserEntity userEntity = (UserEntity) authentication.getPrincipal();

    if (userEntity.hasRole("ADMIN")) {
      return "redirect:/admin";
    }

    if (userEntity.hasRole("USER")) {
//      return "redirect:/ui/redirect?token=" + authenticationService.convert(authentication).getToken();
      return "redirect:http://localhost:4200/redirect?token=" + authenticationService.convert(authentication).getToken();
    }

    return "redirect:/login";
  }

  @GetMapping("/sw-debug.js")
  public String unknown(Authentication authentication) {

    log.info("root");

    return "redirect:/";
  }
}
