package com.dinomudrovcic.uniapp.service.user;

import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.domain.auth.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User getByUsername(String username);

    User getById(Long id);

    Set<Role> getUserRoles(Long id);

    List<User> getStudents();

    boolean isAdmin(String username);

    boolean save(User user);

    boolean update(User user);

    boolean delete(Long id);

    boolean exists(String username);

    boolean exists(Long id);

    String concatNameAndSurname(User user);
}

