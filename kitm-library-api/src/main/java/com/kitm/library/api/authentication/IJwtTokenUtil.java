package com.kitm.library.api.authentication;

import com.kitm.library.api.user.IUserEntity;

import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
public interface IJwtTokenUtil {
  String generateAccessToken(IUserEntity userEntity);

  String getUserId(String token);

  String getUsername(String token);

  Date getExpirationDate(String token);

  boolean validate(String token);
}
