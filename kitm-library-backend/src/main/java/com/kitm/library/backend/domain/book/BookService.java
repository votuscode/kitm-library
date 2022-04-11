package com.kitm.library.backend.domain.book;

import com.kitm.library.api.book.IBookService;
import com.kitm.library.api.book.dto.BookDto;
import com.kitm.library.api.book.dto.CreateBookDto;
import com.kitm.library.backend.domain.author.AuthorEntity;
import com.kitm.library.backend.domain.author.AuthorRepository;
import com.kitm.library.backend.domain.category.CategoryEntity;
import com.kitm.library.backend.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BookService implements IBookService {

  private final BookRepository bookRepository;

  private final CategoryRepository categoryRepository;

  private final AuthorRepository authorRepository;

  @Override
  public Collection<BookDto> findAll() {

    return bookRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public BookDto getOne(UUID id) {

    final BookEntity bookEntity = bookRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find book"));

    return convert(bookEntity);
  }

  @Override
  public BookDto createOne(CreateBookDto createBookDto) {

    final CategoryEntity categoryEntity = categoryRepository
        .findById(createBookDto.getCategoryId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find category"));

    final AuthorEntity authorEntity = authorRepository
        .findById(createBookDto.getAuthorId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find author"));

    final BookEntity bookEntity = BookEntity.builder()
        .name(createBookDto.getName())
        .description(createBookDto.getDescription())
        .pages(createBookDto.getPages())
        .isbn(createBookDto.getIsbn())
        .image(createBookDto.getImage())
        .categoryEntity(categoryEntity)
        .authorEntity(authorEntity)
        .build();

    return convert(
        bookRepository.save(bookEntity)
    );
  }

  private BookDto convert(BookEntity bookEntity) {

    return BookDto.builder()
        .id(bookEntity.getId())
        .name(bookEntity.getName())
        .description(bookEntity.getDescription())
        .pages(bookEntity.getPages())
        .isbn(bookEntity.getIsbn())
        .image(bookEntity.getImage())
        .authorId(bookEntity.getAuthorEntity().getId())
        .categoryId(bookEntity.getCategoryEntity().getId())
        .build();
  }
}
