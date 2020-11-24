package com.dinomudrovcic.uniapp.controller.library;

import com.dinomudrovcic.uniapp.service.library.UserLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("api/borrow")
public class UserLibraryController {

    @Autowired
    private UserLibraryService service;

    @GetMapping
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
