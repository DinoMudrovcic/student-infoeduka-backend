package com.dinomudrovcic.uniapp.repository;

import com.dinomudrovcic.uniapp.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM users u, user_roles ur WHERE u.id = ur.user_id AND ur.role_id = (SELECT id FROM roles WHERE name = 'ROLE_STUDENT')",
        nativeQuery = true)
    List<User> getStudents();

    @Query(value = "SELECT * FROM users u, user_roles ur WHERE u.username = :username AND u.id = ur.user_id AND ur.role_id = (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')",
            nativeQuery = true)
    User isAdmin(@Param("username") String username);

}
