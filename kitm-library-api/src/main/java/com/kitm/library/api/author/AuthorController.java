package com.kitm.library.api.author;

import com.kitm.library.api.author.dto.AuthorDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Api(value = "Author")
@RestController
@RequestMapping(path = "/api/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final IAuthorService authorService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<AuthorDto> getAuthors() {
    return authorService.findAll().stream().toList();
  }
}
