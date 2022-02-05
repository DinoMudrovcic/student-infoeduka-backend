package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.domain.library.Library;

import java.util.List;

public interface LibraryService {
    List<Library> getAll();

    Library getByName(String name);

    List<Library> getByUser(Long userId);

    boolean save(Library library);

    boolean update(Library library);

    boolean delete(Long id);

    boolean exists(String name);

    boolean exists(Long id);

    String concatAuthorAndTitle(Library library);

    boolean onStock(Library library);
}
