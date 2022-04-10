package com.kitm.library.api.book;

import com.kitm.library.api.book.dto.BookDto;
import com.kitm.library.api.book.dto.CreateBookDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface IBookService {

  Collection<BookDto> findAll();

  BookDto getOne(UUID id);

  BookDto createOne(CreateBookDto createBookDto);
}
