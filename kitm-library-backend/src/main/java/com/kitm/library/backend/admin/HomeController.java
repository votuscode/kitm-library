package com.kitm.library.backend.admin;

import com.kitm.library.backend.admin.common.models.Layout;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
public class HomeController {

  @GetMapping("/admin")
  public String home(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Home));

    return "home";
  }
}
