package com.kitm.library.backend.admin;

import com.kitm.library.backend.admin.models.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
public class IndexController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
