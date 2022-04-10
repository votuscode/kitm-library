package com.kitm.library.api.category;

import com.kitm.library.api.category.dto.CategoryDto;
import com.kitm.library.api.category.dto.CreateCategoryDto;

import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface ICategoryService {

  Collection<CategoryDto> findAll();

  CategoryDto createOne(CreateCategoryDto createCategoryDto);
}
