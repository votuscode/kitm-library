package com.kitm.library.api.order.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
@Data
@Builder
public class OrderDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;

  @NotNull
  private UUID bookId;
}
