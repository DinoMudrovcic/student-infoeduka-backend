package com.dinomudrovcic.uniapp.repository;

import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.domain.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library findByName(String name);

    List<Library> findAllByOrderByIdAsc();

    Boolean existsByName(String name);

    @Query(value = "INSERT INTO user_library VALUES (:libraryId, :userId)",
            nativeQuery = true)
    void borrowLibrary(Long libraryId, Long userId);

    @Query(value = "INSERT INTO user_library VALUES (:libraryId, :userId)",
            nativeQuery = true)
    void returnLibrary(Long libraryId, Long userId);


}
