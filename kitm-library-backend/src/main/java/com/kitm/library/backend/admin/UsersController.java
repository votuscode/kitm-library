package com.kitm.library.backend.admin;

import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.backend.domain.role.RoleService;
import com.kitm.library.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class UsersController {

  private final RoleService roleService;

  private final UserService userService;

  @GetMapping("/admin/users")
  public String rolesList(Model model) {
    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("users", userService.findAll());
    model.addAttribute("createUserDto", new CreateUserDto());

    return "users";
  }

  @PostMapping("/admin/users")
  public String addRole(@ModelAttribute CreateUserDto createUserDto, Model model) {
    userService.createOne(createUserDto);

    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("users", userService.findAll());
    model.addAttribute("createUserDto", new CreateUserDto());

    return "users";
  }
}
