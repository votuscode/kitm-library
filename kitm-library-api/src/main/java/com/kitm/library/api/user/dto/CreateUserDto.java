package com.kitm.library.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
  @NotBlank
  @JsonProperty("name")
  String name;

  @NotBlank @Email
  @JsonProperty("email")
  String email;

  @NotBlank
  @JsonProperty("username")
  String username;

  @NotBlank
  @JsonProperty("passwordOriginal")
  String passwordOriginal;

  @NotBlank
  @JsonProperty("passwordConfirmation")
  String passwordConfirmation;

  @JsonProperty("roles")
  Collection<String> roles;
}
