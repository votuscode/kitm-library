package com.kitm.library.api.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
@Data
@Builder
public class UserDto {
  private UUID id;

  private String name;
  private String email;
  private String username;

  private Set<String> roles;

  private Date createdAt;
  private Date updatedAt;
}
