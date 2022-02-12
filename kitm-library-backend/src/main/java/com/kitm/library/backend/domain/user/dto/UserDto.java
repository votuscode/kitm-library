package com.kitm.library.backend.domain.user.dto;

import com.kitm.library.backend.domain.role.RoleEntity;
import com.kitm.library.backend.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
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

  public static UserDto from(UserEntity userEntity) {
    return UserDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .username(userEntity.getUsername())
        .roles(userEntity.getRoles().stream()
            .map(RoleEntity::getName)
            .collect(Collectors.toUnmodifiableSet()))
        .createdAt(userEntity.getCreatedAt())
        .updatedAt(userEntity.getUpdatedAt())
        .build();
  }
}
