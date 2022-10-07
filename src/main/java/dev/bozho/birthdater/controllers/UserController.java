package dev.bozho.birthdater.controllers;

import dev.bozho.birthdater.model.User;
import dev.bozho.birthdater.model.Friend;
import dev.bozho.birthdater.repository.UserRepository;
import dev.bozho.birthdater.service.impl.FriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private FriendServiceImpl friendService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();

            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userRepository
                    .save(new User(user.getFirstName(), user.getLastName(), user.getEmail(),user.getPassword(), user.getUserRole()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("userId") long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);

            if(user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/{userId}/friends")
    public ResponseEntity<List<Friend>> getFriendsByUserId(@PathVariable("userId") long userId) throws Exception {
        List<Friend> friends = friendService.getFriendsOfUserByUserId(userId);

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }
}
