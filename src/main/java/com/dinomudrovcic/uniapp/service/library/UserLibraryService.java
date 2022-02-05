package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.domain.library.UserLibrary;
import com.dinomudrovcic.uniapp.repository.UserLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLibraryService {

    private final UserLibraryRepository userLibraryRepository;

    public List<UserLibrary> getAll() {
        return userLibraryRepository.findAll();
    }

}
