package com.dinomudrovcic.uniapp.service.library;

import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.repository.LibraryRepository;
import com.dinomudrovcic.uniapp.service.library.impl.LibraryServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Before
    public void init() {
        Library library = new Library();
        library.setId(1);
        library.setName("Test");
        library.setAuthor("Test");
        library.setAmount(2);

        Mockito.when(libraryRepository.findByName(library.getName()))
                .thenReturn(library);

    }

    @Test
    public void existsTestByName() {
        String name = "Test";
        Library foundLibrary = libraryService.getByName(name);
        assertEquals(foundLibrary.getName(), name);
    }

    @Test
    public void concatAuthorAndTitleTest() {
        String expectedResult = "Test : Test";
        String actualResult = libraryService.concatAuthorAndTitle(libraryService.getByName("Test"));
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void finish() {
        Mockito.reset();
    }

}