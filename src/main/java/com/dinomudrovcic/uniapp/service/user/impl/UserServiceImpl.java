package com.dinomudrovcic.uniapp.service.user.impl;

import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import com.dinomudrovcic.uniapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    public Set<Role> getUserRoles(Long id) {
        User user = getById(id);
        return user != null ? user.getRoles() : null;
    }

    @Override
    public List<User> getStudents() {
        List<User> users = userRepository.getStudents();
        return users;
    }

    @Override
    public boolean isAdmin(String username) {
        return userRepository.isAdmin(username) != null;
    }

    @Override
    public boolean save(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.info("Error while saving user with id: " + user.getId());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            logger.info("Error while updating user with id: " + user.getId());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.info("Error while deleting user with id: " + id);
            return false;
        }
    }

    @Override
    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public String concatNameAndSurname(User user) {
        return String.format("%s %s", user.getName(), user.getSurname());
    }
}
