package com.dinomudrovcic.uniapp.repository;

import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.domain.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library findByName(String name);

    Boolean existsByName(String name);

}
