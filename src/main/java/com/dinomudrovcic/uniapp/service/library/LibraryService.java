package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.repository.LibraryRepository;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);

    private final LibraryRepository libraryRepository;

    private final UserRepository userRepository;

    public List<Library> getAll() {
        return libraryRepository.findAllByOrderByIdAsc();
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

    public String concatAuthorAndTitle(Library library) {
        return String.format("%s : %s", library.getAuthor(), library.getName());
    }


}
