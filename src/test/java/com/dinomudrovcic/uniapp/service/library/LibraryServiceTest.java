package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.UniAppApplication;
import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.repository.LibraryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniAppApplication.class)
public class LibraryServiceTest {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryService libraryService;


    @Test
    public void existsTestById() {

        Library library = new Library();
        library.setId(1);
        library.setName("Test");
        library.setAuthor("Test");
        library.setAmount(2);

        libraryRepository.save(library);

        boolean exists = libraryService.exists(library.getId());
        assertTrue(exists);
    }

    @Test
    public void existsTestByName() {

        Library library = new Library();
        library.setId(1);
        library.setName("Test");
        library.setAuthor("Test");
        library.setAmount(2);

        libraryRepository.save(library);

        boolean exists = libraryService.exists(library.getName());
        assertTrue(exists);
    }

    @Test
    public void concatAuthorAndTitleTest() {
        Library library = new Library();
        library.setId(1);
        library.setName("Test");
        library.setAuthor("Test");
        library.setAmount(2);

        String expectedResult = library.getAuthor() + " : " + library.getName();
        String actualResult = libraryService.concatAuthorAndTitle(library);

        assertEquals(expectedResult, actualResult);
    }

}