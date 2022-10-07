package dev.bozho.birthdater.controllers;

import dev.bozho.birthdater.model.User;
import dev.bozho.birthdater.model.Friend;
import dev.bozho.birthdater.repository.UserRepository;
import dev.bozho.birthdater.service.impl.FriendServiceImpl;
import dev.bozho.birthdater.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FriendServiceImpl friendService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userService.signUpUser(new User(user.getFirstName(), user.getLastName(), user.getEmail(),user.getPassword(), user.getUserRole()));

        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("userId") long userId) {
        HttpStatus response = userService.deleteUserById(userId);

        return new ResponseEntity<>(response);
    }

    @GetMapping("/get/{userId}/friends")
    public ResponseEntity<List<Friend>> getFriendsOfUserId(@PathVariable("userId") long userId) throws Exception {
        List<Friend> friends = friendService.getFriendsOfUserId(userId);

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }
}
