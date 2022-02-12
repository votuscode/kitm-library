package com.kitm.library.backend.domain.user.dto;

import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
public record CreateUserDto(
    String name,
    String email,
    String username,
    Collection<String> roles
) {
}
