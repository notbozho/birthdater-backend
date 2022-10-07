package dev.bozho.birthdater.controllers;

import dev.bozho.birthdater.model.Friend;
import dev.bozho.birthdater.service.impl.FriendServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
@Api(value = "/friends", tags = {"friends"})
@SwaggerDefinition( tags = {
        @Tag(name = "Friends")
})
public class FriendController {

    @Autowired
    public FriendServiceImpl friendService;

    @ApiOperation(
            value = "Add a new friend",
            response = Friend.class,
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping("/new")
    public ResponseEntity<Friend> createFriend(@RequestBody Friend friend) {
        Friend f = friendService.createFriend(friend);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

}
