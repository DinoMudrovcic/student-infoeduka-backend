package com.dinomudrovcic.uniapp.controller.library;

import com.dinomudrovcic.uniapp.service.library.UserLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/borrow")
@RequiredArgsConstructor
public class UserLibraryController {

    private final UserLibraryService service;

    @GetMapping
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
