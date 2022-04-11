package com.kitm.library.api.author.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Data
@Builder
public class AuthorDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Integer books;
}
