package com.kitm.library.backend.authentication.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
@Builder
public class AuthenticatedDto {
  private Date expires;
}
