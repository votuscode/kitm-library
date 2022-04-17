package com.kitm.library.api.order.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
public class CreateOrderDto {

  @NotNull
  private UUID userId;

  @NotNull
  private UUID bookId;
}
