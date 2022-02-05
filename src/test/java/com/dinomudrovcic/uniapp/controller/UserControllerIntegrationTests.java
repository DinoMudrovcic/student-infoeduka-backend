package com.dinomudrovcic.uniapp.controller;

import com.dinomudrovcic.uniapp.UniAppApplication;
import com.dinomudrovcic.uniapp.domain.auth.ERole;
import com.dinomudrovcic.uniapp.domain.auth.Role;
import com.dinomudrovcic.uniapp.domain.auth.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = UniAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testAllUsers() {
        String url = "http://localhost:" + port + "/api/user";
        ResponseEntity<Object[]> responseEntity = this.restTemplate.getForEntity(url, Object[].class);
        Object[] responseBody = responseEntity.getBody();
        assertTrue(responseBody.length == 3);
    }

    @Test
    public void testAddUser() {
        String url = "http://localhost:" + port + "/api/user";
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().id(1L).name(ERole.ROLE_USER).build());
        User user = new User();
        user.setId(-99);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRoles(roles);

        ResponseEntity<Object[]> responseEntity = this.restTemplate
                .postForEntity(url, user, Object[].class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddUserExisting() {
        String url = "http://localhost:" + port + "/api/user";
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().id(1L).name(ERole.ROLE_USER).build());
        User user = new User();
        user.setId(-99);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRoles(roles);

        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(url, user, String.class);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

}
