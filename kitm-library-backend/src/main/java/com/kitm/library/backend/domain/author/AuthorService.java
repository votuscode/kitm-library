package com.kitm.library.backend.domain.author;

import com.kitm.library.api.author.IAuthorService;
import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.author.dto.CreateAuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Collection<AuthorDto> findAll() {

    return authorRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public AuthorDto createOne(CreateAuthorDto createAuthorDto) {

    final AuthorEntity authorEntity = AuthorEntity.builder()
        .name(createAuthorDto.getName())
        .description(createAuthorDto.getDescription())
        .build();

    return convert(
        authorRepository.save(authorEntity)
    );
  }

  private AuthorDto convert(AuthorEntity authorEntity) {

    final Integer books = Optional.ofNullable(authorEntity.getBookEntitySet())
        .map(Set::size)
        .orElse(0);

    return AuthorDto.builder()
        .id(authorEntity.getId())
        .name(authorEntity.getName())
        .description(authorEntity.getDescription())
        .books(books)
        .build();
  }
}
