package com.kitm.library.backend.domain.role;

import com.kitm.library.api.role.IRoleService;
import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.api.role.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 24.09.21
 */
@Service
@Transactional
public class RoleService implements IRoleService {
  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public List<RoleDto> findAll() {
    return roleRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Transactional
  @Override
  public RoleDto createOne(CreateRoleDto createRoleDto) {
    final RoleEntity roleEntity = RoleEntity.builder()
        .name(createRoleDto.getName())
        .build();

    return convert(
        roleRepository.save(roleEntity)
    );
  }

  private RoleDto convert(RoleEntity roleEntity) {
    return RoleDto.builder()
        .id(roleEntity.getId())
        .name(roleEntity.getName())
        .build();
  }
}
