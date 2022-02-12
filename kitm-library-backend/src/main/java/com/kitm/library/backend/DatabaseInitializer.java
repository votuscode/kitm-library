package com.kitm.library.backend;

import com.google.inject.internal.util.ImmutableSet;
import com.kitm.library.backend.domain.role.RoleService;
import com.kitm.library.backend.domain.user.UserService;
import com.kitm.library.backend.domain.user.dto.CreateUserDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Component
@AllArgsConstructor
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private final RoleService roleService;

  private final UserService userService;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    roleService.createOne("ADMIN");

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
