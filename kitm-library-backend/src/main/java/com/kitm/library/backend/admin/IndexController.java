package com.kitm.library.backend.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
public class IndexController {

  @GetMapping("/")
  public String index() {
    return "layout";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
