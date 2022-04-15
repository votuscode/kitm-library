package com.kitm.library.backend.admin.category;

import com.kitm.library.backend.admin.book.BookEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryEntity {

  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column(nullable = false, unique = true, length = 16)
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "categoryEntity")
  private Set<BookEntity> bookEntitySet = new HashSet<>();
}
