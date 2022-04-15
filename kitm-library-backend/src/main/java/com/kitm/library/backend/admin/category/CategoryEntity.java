package com.kitm.library.backend.admin.category;

import com.kitm.library.backend.domain.book.BookEntity;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.*;

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
