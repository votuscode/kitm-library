package com.kitm.library.api.category;

import com.kitm.library.api.category.dto.CategoryDto;
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
@Api(value = "Category")
@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final ICategoryService categoryService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<CategoryDto> getCategories() {
    return categoryService.findAll().stream().toList();
  }
}