package com.kitm.library.backend.domain.user;

import com.kitm.library.backend.domain.role.RoleEntity;
import com.kitm.library.backend.domain.role.RoleRepository;
import com.kitm.library.backend.domain.user.dto.CreateUserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
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
@AllArgsConstructor
public class UserService {

  private final RoleRepository roleRepository;

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public Collection<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @Transactional
  public UserEntity createOne(CreateUserDto createUserDto) {
    if (userRepository.findUserEntityByUsername(createUserDto.getUsername()).isPresent()) {
      throw new ValidationException("Username already registered.");
    }

    if (!createUserDto.getPasswordOriginal().equals(createUserDto.getPasswordConfirmation())) {
      throw new ValidationException("Password doesn't match.");
    }

    if (createUserDto.getRoles() == null) {
      throw new ValidationException("Roles are not provided.");
    }

    final Set<RoleEntity> roleEntities = createUserDto.getRoles().stream()
        .map(roleRepository::findRoleEntityByName)
        .collect(Collectors.toUnmodifiableSet());

    if (roleEntities.size() == 0) {
      throw new ValidationException("Invalid roles.");
    }

    final UserEntity userEntity = UserEntity.builder()
        .name(createUserDto.getName())
        .email(createUserDto.getEmail())
        .username(createUserDto.getUsername())
        .roles(roleEntities)
        .password(passwordEncoder.encode(createUserDto.getPasswordOriginal()))
        .build();

    return userRepository.save(userEntity);
  }
}
