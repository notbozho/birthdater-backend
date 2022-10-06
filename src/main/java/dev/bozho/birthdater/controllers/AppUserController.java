package dev.bozho.birthdater.controllers;


import dev.bozho.birthdater.domain.AppUser;
import dev.bozho.birthdater.domain.Friend;
import dev.bozho.birthdater.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/api/v1")
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        try {
            List<AppUser> users = new ArrayList<AppUser>();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("userId") long userId) {
        Optional<AppUser> appUser = appUserRepository.findById(userId);

        if(appUser.isPresent()) {
            return new ResponseEntity<>(appUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        try {
            AppUser _appUser = appUserRepository
                    .save(new AppUser(appUser.getFirstName(), appUser.getLastName(), appUser.getEmail(),appUser.getPassword(), appUser.getAppUserRole()));
            return new ResponseEntity<>(_appUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
