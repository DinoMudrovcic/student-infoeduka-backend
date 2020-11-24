package com.dinomudrovcic.uniapp.controller.user;

import com.dinomudrovcic.uniapp.domain.auth.User;
import com.dinomudrovcic.uniapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getByUsername(@Valid @PathVariable String username) {
        return userService.exists(username) ?
                new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK) :
                new ResponseEntity<>("User not exists", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@Valid @PathVariable Long id) {
        return userService.exists(id) ?
                new ResponseEntity<>(userService.getUserRoles(id), HttpStatus.OK) :
                new ResponseEntity<>("User not exists", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/role/{user}")
    public ResponseEntity<?> isAdmin(@Valid @PathVariable String user) {
        return userService.isAdmin(user) ?
                new ResponseEntity<>("true", HttpStatus.OK) :
                new ResponseEntity<>("false", HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents() {
        return new ResponseEntity<>(userService.getStudents(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return userService.exists(user.getUsername()) ?
                new ResponseEntity<>("User with username " + user.getUsername() + " already exists", HttpStatus.BAD_REQUEST) :
                userService.save(user) ?
                        new ResponseEntity<>(HttpStatus.OK) :
                        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        return userService.exists(user.getUsername()) ?
                userService.update(user) ?
                        new ResponseEntity<>(HttpStatus.OK) :
                        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>("User not exists", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Long id) {
        return userService.exists(id) ?
                userService.delete(id) ?
                        new ResponseEntity<>(HttpStatus.OK) :
                        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>("User not exists", HttpStatus.BAD_REQUEST);
    }

}
