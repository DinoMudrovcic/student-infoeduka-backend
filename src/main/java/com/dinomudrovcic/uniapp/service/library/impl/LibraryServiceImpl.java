package com.dinomudrovcic.uniapp.service.library.impl;

import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.repository.LibraryRepository;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import com.dinomudrovcic.uniapp.service.library.LibraryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private final LibraryRepository libraryRepository;

    private final UserRepository userRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, UserRepository userRepository) {
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Library> getAll() {
        return libraryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Library getByName(String name) {
        return libraryRepository.findByName(name);
    }

    @Override
    public List<Library> getByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        List<Library> libraries = null;
        return libraries;
    }

    @Override
    public boolean save(Library library) {
        try {
            libraryRepository.save(library);
            return true;
        } catch (Exception e) {
            logger.info("Error while saving user  with id: " + library.getId());
            return false;
        }
    }

    @Override
    public boolean update(Library library) {
        try {
            Library dbLibrary = libraryRepository.findById(library.getId()).orElseThrow(null);
            dbLibrary.setAmount(library.getAmount());
            dbLibrary.setAuthor(library.getAuthor());
            dbLibrary.setName(library.getName());
            libraryRepository.save(library);
            return true;
        } catch (Exception e) {
            logger.info("Error while updating user with id: " + library.getId());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            libraryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.info("Error while deleting user with id: " + id);
            return false;
        }
    }

    @Override
    public boolean exists(String name) {
        return libraryRepository.existsByName(name);
    }

    @Override
    public boolean exists(Long id) {
        return libraryRepository.existsById(id);
    }

    @Override
    public String concatAuthorAndTitle(Library library) {
        return String.format("%s : %s", library.getAuthor(), library.getName());
    }

    @Override
    public boolean onStock(Library library) {
        return library.getAmount() == 0;
    }


}
