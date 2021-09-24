package com.kitm.library.backend.domain.user;

import com.kitm.library.backend.domain.role.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
@RestController()
@RequestMapping("/api/user")
public class UserController {
  record UserDto(
      UUID id,
      String name,
      String email,
      String username,
      Collection<String> roles,
      Date createdAt,
      Date updatedAt
  ) {
    public static UserDto from(UserEntity userEntity) {
      return new UserDto(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getUsername(),
          userEntity.getRoles().stream().map(RoleEntity::getName).toList(),
          userEntity.getCreatedAt(), userEntity.getUpdatedAt()
      );
    }
  }

  public static record CreateUserDto(
      String name,
      String email,
      String username,
      Collection<String> roles
  ) {
  }

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public Collection<UserDto> getAll() {
    return userService.findAll().stream().map(UserDto::from).toList();
  }

  @PostMapping
  public ResponseEntity<UserDto> createOne(@RequestBody CreateUserDto createUserDto) {
    final UserEntity userEntity = userService.createOne(createUserDto.name, createUserDto.email, createUserDto.username, createUserDto.roles);
    return ResponseEntity.ok(UserDto.from(userEntity));
  }
}
