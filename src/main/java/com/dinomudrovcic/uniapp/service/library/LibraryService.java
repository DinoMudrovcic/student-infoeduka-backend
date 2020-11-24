package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.repository.LibraryRepository;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    public Library getByName(String name) {
        return libraryRepository.findByName(name);
    }

    public List<Library> getByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;

        }
        List<Library> libraries = null;
        return libraries;
    }

    public boolean save(Library library) {
        try {
            libraryRepository.save(library);
            return true;
        } catch (Exception e) {
            logger.info("Error while saving user  with id: " + library.getId());
            return false;
        }
    }

    public boolean update(Library library) {
        try {
            libraryRepository.saveAndFlush(library);
            return true;
        } catch (Exception e) {
            logger.info("Error while updating user with id: " + library.getId());
            return false;
        }
    }

    public boolean delete(Long id) {
        try {
            libraryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.info("Error while deleting user with id: " + id);
            return false;
        }
    }

    public boolean exists(String name) {
        return libraryRepository.existsByName(name);
    }

    public boolean exists(Long id) {
        return libraryRepository.existsById(id);
    }


}
