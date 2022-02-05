package com.dinomudrovcic.uniapp.service.user;

import com.dinomudrovcic.uniapp.domain.auth.ERole;
import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.repository.LibraryRepository;
import com.dinomudrovcic.uniapp.repository.UserRepository;
import com.dinomudrovcic.uniapp.service.library.impl.LibraryServiceImpl;
import com.dinomudrovcic.uniapp.service.user.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void init() {
        Role role = new Role();
        role.setId(-99);
        role.setName(ERole.ROLE_PROFESSOR);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setId(-99);
        user.setName("Name");
        user.setSurname("Surname");
        user.setUsername("Username");
        user.setPassword("Password");
        user.setEmail("Email");
        user.setRoles(roles);

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);
    }

    @Test
    public void isAdminTest() {
        String username = "Username";
        assertTrue(userService.isAdmin(username));
    }

    @Test
    public void getByIdTest() {
        Long id = -99L;
        User foundUser = userService.getById(id);
        assertNotNull(foundUser);
    }

    @Test
    public void concatNameAndSurname() {
        String expectedResult = "Name Surname";
        String actualResult = userService.concatNameAndSurname(userService.getByUsername("Name"));
        assertEquals(expectedResult, actualResult);
    }

    @After
    public void finish() {
        Mockito.reset();
    }

}