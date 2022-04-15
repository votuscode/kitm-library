package com.kitm.library.backend.domain.author;

import com.kitm.library.backend.domain.book.BookEntity;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorEntity {

  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "authorEntity", orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<BookEntity> bookEntitySet = new HashSet<>();
}
