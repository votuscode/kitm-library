package com.kitm.library.api.user;

import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.api.user.dto.UserDto;

import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
public interface IUserService {

  Collection<UserDto> findAll();

  UserDto createOne(CreateUserDto createUserDto);
}
