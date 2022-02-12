package com.kitm.library.backend.domain.user.dto;

import com.kitm.library.backend.domain.role.RoleEntity;
import com.kitm.library.backend.domain.user.UserEntity;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
public record UserDto(
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
