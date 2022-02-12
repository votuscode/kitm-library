package com.kitm.library.backend.domain.role;

import com.kitm.library.backend.domain.role.dto.CreateRoleDto;
import com.kitm.library.backend.domain.role.dto.RoleDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 24.09.21
 */
@Tag(name = "Role")
@RestController
@RequestMapping(path ="api/role")
public class RoleController {
  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public List<RoleDto> findAll() {
    return roleService.findAll().stream()
        .map(RoleDto::from)
        .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<RoleDto> createOne(@RequestBody CreateRoleDto createRoleDto) {
    final RoleEntity roleEntity = roleService.createOne(createRoleDto.getName());
    return ResponseEntity.ok(RoleDto.from(roleEntity));
  }
}
