package com.kitm.library.backend.domain.book;

import com.kitm.library.backend.domain.author.AuthorEntity;
import com.kitm.library.backend.domain.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BookEntity {

  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer pages;

  @Column(nullable = false, length = 13)
  private String isbn;

  @Column
  private String image;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private AuthorEntity authorEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity categoryEntity;
}
