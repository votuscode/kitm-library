package com.kitm.library.backend.domain.user;

import com.kitm.library.backend.domain.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 20.09.21
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserEntity implements Serializable {
  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column
  private String name;

  @Column
  private String email;

  @Column(nullable = false, unique = true, length = 32)
  private String username;

  @Column
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<RoleEntity> roles;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;
}
