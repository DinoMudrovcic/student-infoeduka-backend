package com.dinomudrovcic.uniapp.service.user;

import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? user : null;
    }

    public Set<Role> getUserRoles(Long id) {
        User user = getById(id);
        return user != null ? user.getRoles() : null;
    }

    public List<User> getStudents() {
        List<User> users = userRepository.getStudents();
        return users;
    }

    public boolean isAdmin(String username) {
        return userRepository.isAdmin(username) != null ? true : false;
    }

    public boolean save(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.info("Error while saving user with id: " + user.getId());
            return false;
        }
    }

    public boolean update(User user) {
        try {
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            logger.info("Error while updating user with id: " + user.getId());
            return false;
        }
    }

    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.info("Error while deleting user with id: " + id);
            return false;
        }
    }

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }
}
