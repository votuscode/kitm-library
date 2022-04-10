package com.kitm.library.backend.domain.category;

import com.kitm.library.api.category.ICategoryService;
import com.kitm.library.api.category.dto.CategoryDto;
import com.kitm.library.api.category.dto.CreateCategoryDto;
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
public class CategoryService implements ICategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Collection<CategoryDto> findAll() {

    return categoryRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public CategoryDto createOne(CreateCategoryDto createCategoryDto) {

    final CategoryEntity categoryEntity = CategoryEntity.builder()
        .name(createCategoryDto.getName())
        .description(createCategoryDto.getDescription())
        .build();

    return convert(
        categoryRepository.save(categoryEntity)
    );
  }

  private CategoryDto convert(CategoryEntity categoryEntity) {

    final Integer books = Optional.ofNullable(categoryEntity.getBookEntitySet())
        .map(Set::size)
        .orElse(0);

    return CategoryDto.builder()
        .id(categoryEntity.getId())
        .name(categoryEntity.getName())
        .description(categoryEntity.getDescription())
        .books(books)
        .build();
  }
}
