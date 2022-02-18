package com.kitm.library.api.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
public interface IUserEntity extends Serializable, UserDetails {
}
