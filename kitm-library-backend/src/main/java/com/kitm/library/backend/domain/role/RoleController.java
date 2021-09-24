package com.kitm.library.backend.domain.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 24.09.21
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
  record RoleDto(
      UUID id,
      String name
  ) {
    public static RoleDto from(RoleEntity roleEntity) {
      return new RoleDto(roleEntity.getId(), roleEntity.getName());
    }
  }

  public static record CreateRoleDto(String name) {
  }

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public Collection<RoleDto> findAll() {
    return roleService.findAll().stream().map(RoleDto::from).toList();
  }

  @PostMapping
  public ResponseEntity<RoleDto> createOne(@RequestBody CreateRoleDto createRoleDto) {
    final RoleEntity roleEntity = roleService.createOne(createRoleDto.name);
    return ResponseEntity.ok(RoleDto.from(roleEntity));
  }
}
