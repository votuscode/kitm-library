package com.kitm.library.backend.domain.user;

import com.kitm.library.backend.domain.user.dto.CreateUserDto;
import com.kitm.library.backend.domain.user.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
@Tag(name = "User")
@RestController()
@RequestMapping(path = "api/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> getAll() {
    return userService.findAll().stream()
        .map(UserDto::from)
        .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<UserDto> createOne(@RequestBody CreateUserDto createUserDto) {
    final UserEntity userEntity = userService.createOne(createUserDto);
    return ResponseEntity.ok(UserDto.from(userEntity));
  }
}
