package com.kitm.library.api.common;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 15.04.22
 */
public interface ICrudService<Dto, UpdateDto> {

  Collection<Dto> findAll();

  Dto getOne(UUID id);

  Dto createOne(UpdateDto updateDto);

  Dto updateOne(UUID id, UpdateDto updateDto);

  void deleteOne(UUID id);
}
