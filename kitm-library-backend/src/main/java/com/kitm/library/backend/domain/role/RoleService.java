package com.kitm.library.backend.domain.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 24.09.21
 */
@Service
@Transactional
public class RoleService {
  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Collection<RoleEntity> findAll() {
    return roleRepository.findAll();
  }

  public RoleEntity createOne(String name) {
    final RoleEntity roleEntity = RoleEntity.builder()
        .name(name)
        .build();

    return roleRepository.save(roleEntity);
  }
}
