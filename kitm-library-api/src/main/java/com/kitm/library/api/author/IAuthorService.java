package com.kitm.library.api.author;

import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.author.dto.CreateAuthorDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface IAuthorService {

  Collection<AuthorDto> findAll();

  AuthorDto getOne(UUID id);

  AuthorDto createOne(CreateAuthorDto createAuthorDto);
}
