package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.domain.library.UserLibrary;
import com.dinomudrovcic.uniapp.repository.UserLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLibraryService {

    @Autowired
    private UserLibraryRepository userLibraryRepository;

    public List<UserLibrary> getAll() {
        return userLibraryRepository.findAll();
    }

}
