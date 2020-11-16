package com.dinomudrovcic.uniapp.repository;

import com.dinomudrovcic.uniapp.domain.auth.ERole;
import com.dinomudrovcic.uniapp.domain.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole roleName);

}
