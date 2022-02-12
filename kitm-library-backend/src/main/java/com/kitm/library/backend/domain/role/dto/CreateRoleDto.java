package com.kitm.library.backend.domain.role.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
public class CreateRoleDto {
  @NotBlank
  String name;
}
