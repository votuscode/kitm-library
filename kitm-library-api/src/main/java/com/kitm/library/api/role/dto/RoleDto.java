package com.kitm.library.api.role.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
@Builder
public class RoleDto {
  @NotBlank
  private UUID id;

  @NotBlank
  private String name;
}
