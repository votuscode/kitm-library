package com.kitm.library.backend.admin.book;

import com.kitm.library.backend.admin.author.AuthorEntity;
import com.kitm.library.backend.admin.category.CategoryEntity;
import lombok.*;

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
@Setter
@Builder
public class BookEntity {

  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column()
  private String description;

  @Column()
  private Integer pages;

  @Column(length = 32)
  private String isbn;

  @Lob
  @Column
  private String image;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private AuthorEntity authorEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity categoryEntity;
}
