package com.kitm.library.backend.domain.user;

import com.kitm.library.backend.domain.role.RoleEntity;
import com.kitm.library.backend.domain.role.RoleRepository;
import com.kitm.library.backend.domain.user.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
@Service
@Transactional
public class UserService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  @Autowired
  public UserService(RoleRepository roleRepository, UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }

  public Collection<UserEntity> findAll() {
    return userRepository.findAll();
  }

  public UserEntity createOne(CreateUserDto createUserDto) {
    final Set<RoleEntity> roleEntities = createUserDto.getRoles().stream()
        .map(roleRepository::findRoleEntityByName)
        .collect(Collectors.toUnmodifiableSet());

    final UserEntity userEntity = UserEntity.builder()
        .name(createUserDto.getName())
        .email(createUserDto.getEmail())
        .username(createUserDto.getUsername())
        .roles(roleEntities)
        .build();

    return userRepository.save(userEntity);
  }
}
