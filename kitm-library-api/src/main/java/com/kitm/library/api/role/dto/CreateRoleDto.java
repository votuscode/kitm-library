package com.kitm.library.api.role.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
@Builder
public class CreateRoleDto {
  @NotBlank
  String name;
}
