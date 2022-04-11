package com.kitm.library.backend.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
