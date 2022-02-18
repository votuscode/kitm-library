package com.kitm.library.api.user;

import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.api.user.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
@Api(value = "User")
@RestController()
@RequestMapping(path = "api/user")
@AllArgsConstructor
public class UserController {
  private final IUserService userService;

  @GetMapping
  public Collection<UserDto> getUsers() {
    return userService.findAll().stream().toList();
  }

  @PostMapping
  public UserDto createUser(@RequestBody @Valid CreateUserDto createUserDto) {
    return userService.createOne(createUserDto);
  }
}
