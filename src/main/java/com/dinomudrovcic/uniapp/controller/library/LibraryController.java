package com.dinomudrovcic.uniapp.controller.library;

import com.dinomudrovcic.uniapp.domain.library.Library;
import com.dinomudrovcic.uniapp.service.library.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 180000)
@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(libraryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getByUserId(@Valid @PathVariable Long userId, Locale locale) {
        return libraryService.exists(userId) ?
                new ResponseEntity<>(libraryService.getByUser(userId), HttpStatus.OK) :
                new ResponseEntity<>(messageSource.getMessage("library.notFound", null, locale), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@Valid @PathVariable String name) {
        return libraryService.exists(name) ?
                new ResponseEntity<>(libraryService.getByName(name), HttpStatus.OK):
                new ResponseEntity<>("Library does not exist.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Library library) {
        return libraryService.exists(library.getName()) ?
                new ResponseEntity<>("Library already exist.", HttpStatus.BAD_REQUEST) :
                libraryService.save(library) ?
                    new ResponseEntity<>(HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Library library) {
        return !libraryService.exists(library.getId()) ?
                new ResponseEntity<>("Library does not exist.", HttpStatus.BAD_REQUEST) :
                libraryService.update(library) ?
                    new ResponseEntity<>(HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Long id) {
        return libraryService.exists(id) ?
                libraryService.delete(id) ?
                    new ResponseEntity<>(HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR):
                new ResponseEntity<>("Library does not exist.", HttpStatus.BAD_REQUEST);
    }

}
