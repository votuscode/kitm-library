package com.kitm.library.backend;

import com.google.common.collect.ImmutableSet;
import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.author.dto.UpsertAuthorDto;
import com.kitm.library.api.book.dto.CreateBookDto;
import com.kitm.library.api.category.dto.CategoryDto;
import com.kitm.library.api.category.dto.UpsertCategoryDto;
import com.kitm.library.api.role.IRoleService;
import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.backend.admin.author.AuthorService;
import com.kitm.library.backend.domain.book.BookService;
import com.kitm.library.backend.admin.category.CategoryService;
import com.kitm.library.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

  private final IRoleService roleService;

  private final UserService userService;

  private final CategoryService categoryService;

  private final AuthorService authorService;

  private final BookService bookService;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

    roleService.createOne(CreateRoleDto.builder()
        .name("ADMIN")
        .build());

    userService.createOne(CreateUserDto.builder()
        .username("admin")
        .name("John Doe")
        .email("john.doe@mail.com")
        .passwordOriginal("secret")
        .passwordConfirmation("secret")
        .roles(ImmutableSet.of("ADMIN"))
        .build());

    final CategoryDto categoryDto = categoryService.createOne(UpsertCategoryDto.builder()
        .name("Programming")
        .description("Books about programming")
        .build());

    final AuthorDto authorDto = authorService.createOne(UpsertAuthorDto.builder()
        .name("Robert C. Martin")
        .description("Colloquially called Uncle Bob, is an American software engineer, instructor, and best-selling author. He is most recognized for developing many software design principles and for being a founder of the influential Agile Manifesto.")
        .build());

    bookService.createOne(CreateBookDto.builder()
        .name("Clean Code: A Handbook of Agile Software Craftsmanship")
        .description("Even bad code can function. But if code isn't clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn't have to be that way.")
        .pages(464)
        .isbn("9780132350884")
        .image("clean_code.jpg")
        .categoryId(categoryDto.getId())
        .authorId(authorDto.getId())
        .build());

    bookService.createOne(CreateBookDto.builder()
        .name("Clean Architecture: A Craftsman's Guide to Software Structure and Design (Robert C. Martin Series)")
        .description("")
        .pages(123)
        .isbn("1231231231234")
        .image("clean_code.jpg")
        .categoryId(categoryDto.getId())
        .authorId(authorDto.getId())
        .build());

    bookService.createOne(CreateBookDto.builder()
        .name("Clean Craftsmanship: Disciplines, Standards, and Ethics (Robert C. Martin Series)")
        .description("")
        .pages(123)
        .isbn("1231231231234")
        .image("clean_code.jpg")
        .categoryId(categoryDto.getId())
        .authorId(authorDto.getId())
        .build());

    bookService.createOne(CreateBookDto.builder()
        .name("Code That Fits in Your Head : Heuristics for Software Engineering (Robert C. Martin Series)")
        .description("")
        .pages(123)
        .isbn("1231231231234")
        .image("clean_code.jpg")
        .categoryId(categoryDto.getId())
        .authorId(authorDto.getId())
        .build());
  }
}
