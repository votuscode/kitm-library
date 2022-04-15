package com.kitm.library.api.book.dto;

import com.kitm.library.api.common.IGenericDto;
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
public class BookDto implements IGenericDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Integer pages;

  @NotNull
  private String isbn;

  @NotNull
  private String image;

  @NotNull
  private UUID authorId;

  @NotNull
  private UUID categoryId;
}
