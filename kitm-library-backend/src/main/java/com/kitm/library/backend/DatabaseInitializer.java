package com.kitm.library.backend;

import com.google.common.collect.ImmutableSet;
import com.kitm.library.api.role.IRoleService;
import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private final IRoleService roleService;

  private final UserService userService;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    roleService.createOne(CreateRoleDto.builder()
        .name("ADMIN")
        .build());

    userService.createOne(CreateUserDto.builder()
        .username("admin")
        .name("John Doe")
        .email("john.doe@mail.com")
        .passwordOriginal("secret")
        .passwordConfirmation("secret")
        .roles(ImmutableSet.of("ADMIN"))
        .build());
  }
}
