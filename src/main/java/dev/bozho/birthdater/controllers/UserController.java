package dev.bozho.birthdater.controllers;

import dev.bozho.birthdater.model.User;
import dev.bozho.birthdater.model.Friend;
import dev.bozho.birthdater.repository.UserRepository;
import dev.bozho.birthdater.service.impl.FriendServiceImpl;
import dev.bozho.birthdater.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "/users", tags = {"users"})
@SwaggerDefinition( tags = {
        @Tag(name = "Users")
})
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FriendServiceImpl friendService;

    @ApiOperation(
            value = "Get all users",
            response = List.class,
            produces = "application/json",
            httpMethod = "GET")
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get user by id",
            response = User.class,
            produces = "application/json",
            httpMethod = "GET")
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Sign up a new user",
            response = User.class,
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userService.signUpUser(new User(user.getFirstName(), user.getLastName(), user.getEmail(),user.getPassword(), user.getUserRole()));

        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Delete a user",
            response = HttpStatus.class,
            httpMethod = "DELETE")
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
