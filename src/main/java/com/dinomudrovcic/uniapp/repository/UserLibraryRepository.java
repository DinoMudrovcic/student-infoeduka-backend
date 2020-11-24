package com.dinomudrovcic.uniapp.repository;

import com.dinomudrovcic.uniapp.domain.library.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLibraryRepository extends JpaRepository<UserLibrary, Long> {



}
