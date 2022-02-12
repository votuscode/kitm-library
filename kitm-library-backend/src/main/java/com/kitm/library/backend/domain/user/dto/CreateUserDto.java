package com.kitm.library.backend.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
public class CreateUserDto {
  @NotBlank
  String name;

  @NotBlank @Email
  String email;

  @NotBlank
  String username;

  Collection<String> roles;
}
